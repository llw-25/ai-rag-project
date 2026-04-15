package wanghui.airagproject.service;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wanghui.airagproject.utils.AiHttpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类描述：将文本向量化
 *
 * @author LiuWangHui
 * @version 1.0.0
 * @date 2026/04/15 13:44:48
 */
@Component
public class EmbeddingService {

    @Value("${ai.embedding.api-key}")
    private String apiKey;

    @Value("${ai.embedding.base-url}")
    private String baseUrl;

    public List<Float> embed(String text) {

        String url = baseUrl + "/v1/embeddings";

        String body = JSON.toJSONString(Map.of(
                "model",  "BAAI/bge-m3",
                "input", text
        ));

        String response = AiHttpUtil.post(url, apiKey, body);

        // 防止错误响应解析崩溃
        if (response == null || !response.startsWith("{")) {
            throw new RuntimeException("embedding失败：" + response);
        }

        Map resMap = JSON.parseObject(response, Map.class);
        List data = (List) resMap.get("data");

        if (data == null || data.isEmpty()) {
            throw new RuntimeException("embedding返回异常：" + response);
        }

        Map item = (Map) data.get(0);
        List<?> raw = (List<?>) item.get("embedding");

        List<Float> result = new ArrayList<>();
        for (Object o : raw) {
            result.add(((Number) o).floatValue());
        }

        return result;
    }
}