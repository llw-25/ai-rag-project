package wanghui.airagproject.service;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wanghui.airagproject.utils.AiHttpUtil;

/**
 * 类描述：聊天
 *
 * @author LiuWangHui
 * @version 1.0.0
 * @date 2026/04/15 13:49:42
 */
@Service
    public class AiService {

    @Value("${ai.chat.api-key}")
    private String apiKey;

    @Value("${ai.chat.base-url}")
    private String baseUrl;

        public String chat(String msg) {

            String url = baseUrl + "/v1/chat/completions";

            String body = """
        {
          "model": "deepseek-chat",
          "messages": [
            {"role": "user", "content": "%s"}
          ]
        }
        """.formatted(msg);

            String response = AiHttpUtil.post(url, apiKey, body);

            return response; // 先直接返回
        }
    }

