package wanghui.airagproject.service;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wanghui.airagproject.dao.DocDao;
import wanghui.airagproject.pojo.Doc;
import wanghui.airagproject.rag.splitter.TextSplitter;

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
     * 文档入库（RAG完整流程）
     */
    public void save(String content) {
        // 生成一个文档ID（简单版）
        long docId = System.currentTimeMillis();

        // 1 文本切分
        List<String> chunks = splitter.split(content);

        System.out.println("切分块数：" + chunks.size());

        // 2遍历 chunk
        for (String chunk : chunks) {

            try {
                // 3 调 embedding
                List<Float> vector = embeddingService.embed(chunk);

                // 4 转 JSON（存数据库）
                String embeddingJson = JSON.toJSONString(vector);

                // 5 构造对象
                Doc doc = new Doc();
                doc.setContent(chunk);
                doc.setEmbedding(embeddingJson);

                //  如果你后面做多文档，可以加 docId
                // doc.setDocId(xxx);

                // 6 入库
                docDao.insert(doc);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(" 某个 chunk 处理失败：" + chunk);
            }
        }

        System.out.println(" 文档入库完成");
    }
}