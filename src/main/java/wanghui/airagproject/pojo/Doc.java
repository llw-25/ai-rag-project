package wanghui.airagproject.pojo;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * 类描述：文档实体类
 *
 * @author LiuWangHui
 * @version 1.0.0
 * @date 2026/04/14 09:47:20
 */
@Data
public class Doc {
    private Long id;
    private String content;
    private String embedding;
    private Long docId;
    private LocalDateTime createTime;
    private Double score;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
