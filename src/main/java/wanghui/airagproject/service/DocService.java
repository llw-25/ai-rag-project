package wanghui.airagproject.service;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wanghui.airagproject.dao.DocDao;
import wanghui.airagproject.pojo.Doc;
import wanghui.airagproject.rag.splitter.TextSplitter;
import wanghui.airagproject.utils.CosineSimilarityUtil;

import java.util.List;

@Service
public class DocService {

    @Autowired
    private TextSplitter splitter;

    @Autowired
    private DocDao docDao;

    @Autowired
    private EmbeddingService embeddingService;

    /**
     * 保存文档（RAG索引流程）
     */
    public void save(String content) {
        // 1. 文本分割
        List<String> chunks = splitter.split(content);

        // 2. 向量化 + 存储
        for (String chunk : chunks) {
            // 2.1 生成向量
            List<Float> vector = embeddingService.embed(chunk);

            if (vector == null) {
                throw new RuntimeException("embedding 生成失败");
            }

            // 2.2 转换为JSON字符串
            String vectorJson = JSON.toJSONString(vector);

            // 2.3 创建Doc对象
            Doc doc = new Doc();
            doc.setContent(chunk);
            doc.setEmbedding(vectorJson);

            // 2.4 保存到数据库
            docDao.insert(doc);
        }
    }

    /**
     * 查询（RAG核心）
     */
    public List<Doc> search(String query) {

        // 1. 查询向量
        List<Float> queryVector = embeddingService.embed(query);

        if (queryVector == null) {
            throw new RuntimeException("query embedding 生成失败");
        }

        // 2. 查数据库
        List<Doc> docs = docDao.selectAll();

        // 3. 相似度计算 + 排序
        return docs.stream()

                // 过滤脏数据
                .filter(doc -> doc.getEmbedding() != null)

                .map(doc -> {
                    try {
                        List<Float> docVector =
                                JSON.parseArray(doc.getEmbedding(), Float.class);

                        if (docVector == null) return null;

                        double score = CosineSimilarityUtil
                                .cosineSimilarity(queryVector, docVector);

                        doc.setScore(score);
                        return doc;

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })

                .filter(doc -> doc != null)

                // 排序（倒序）
                .sorted((a, b) -> Double.compare(b.getScore(), a.getScore()))

                // TopK
                .limit(3)

                .toList();
    }
}