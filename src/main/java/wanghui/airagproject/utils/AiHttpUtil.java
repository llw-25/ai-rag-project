package wanghui.airagproject.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 类描述：
 *
 * @author LiuWangHui
 * @version 1.0.0
 * @date 2026/04/14 15:11:27
 */
public class AiHttpUtil {

    private static String API_KEY;


    public static void setApiKey(String key) {
        API_KEY = key;
    }



    public static String chat(String msg) {
        try {
            URL url = new URL("https://api.deepseek.com/v1/chat/completions");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setDoOutput(true);

            // 🔥 Prompt（你可以改）
            String jsonInput = "{\n" +
                    "  \"model\": \"deepseek-chat\",\n" +
                    "  \"messages\": [\n" +
                    "    {\"role\": \"system\", \"content\": \"你是一个Java面试官，请专业回答问题\"},\n" +
                    "    {\"role\": \"user\", \"content\": \"" + msg + "\"}\n" +
                    "  ]\n" +
                    "}";

            OutputStream os = conn.getOutputStream();
            os.write(jsonInput.getBytes());
            os.flush();
            os.close();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }

            br.close();

//            return response.toString(); // 👉 先返回原始JSON
            String res = response.toString();

            // 👉 提取 content
            int start = res.indexOf("\"content\":\"") + 11;
            int end = res.indexOf("\"", start);

            if (start > 10 && end > start) {
                return res.substring(start, end)
                        .replace("\\n", "\n")
                        .replace("\\\"", "\"");
            }

            return "解析失败：" + res;

        } catch (Exception e) {
            e.printStackTrace();
            return "调用AI失败：" + e.getMessage();
        }
    }
}