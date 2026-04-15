package wanghui.airagproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wanghui.airagproject.service.EmbeddingService;

import java.util.List;

/**
 * 类描述：向量化接口
 *
 * @author LiuWangHui
 * @version 1.0.0
 * @date 2026/04/15 14:25:05
 */
@RestController
public class EmbeddingController {

    @Autowired
    private EmbeddingService embeddingService;

    @GetMapping("/embed")
    public List<Float> embed(String text) {
        return embeddingService.embed(text);
    }
}