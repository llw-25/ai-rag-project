package wanghui.airagproject.utils;

import java.util.List;

/**
 * 类描述：余弦相似度工具类
 *
 * @author LiuWangHui
 * @version 1.0.0
 * @date 2026/04/15 16:44:39
 */
public class CosineSimilarityUtil {

    public static double cosineSimilarity(List<Float> v1, List<Float> v2) {

        // 防御：避免 NPE
        if (v1 == null || v2 == null || v1.isEmpty() || v2.isEmpty()) {
            return 0.0;
        }

        double dot = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        // 防止长度不一致
        int size = Math.min(v1.size(), v2.size());

        for (int i = 0; i < size; i++) {
            float a = v1.get(i);
            float b = v2.get(i);

            dot += a * b;
            normA += a * a;
            normB += b * b;
        }

        // 防止除0
        if (normA == 0 || normB == 0) {
            return 0.0;
        }

        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}