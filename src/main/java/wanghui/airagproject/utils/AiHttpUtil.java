package wanghui.airagproject.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AiHttpUtil {

    public static String post(String urlStr, String apiKey, String body) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            conn.setDoOutput(true);

            // 发送请求
            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes("utf-8"));
            }

            int code = conn.getResponseCode();
            InputStream is = (code >= 200 && code < 300)
                    ? conn.getInputStream()
                    : conn.getErrorStream();

            if (is == null) {
                return "请求失败：stream为null，HTTP code=" + code;
            }

            // ✅ 核心修改点（防卡死）
            byte[] bytes = is.readAllBytes();
            String response = new String(bytes, "utf-8");

            System.out.println("HTTP状态码：" + code);
            System.out.println("返回内容：" + response);

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return "请求异常：" + e.getMessage();
        }
    }
}