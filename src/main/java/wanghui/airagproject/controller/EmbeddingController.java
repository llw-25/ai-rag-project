package wanghui.airagproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wanghui.airagproject.service.EmbeddingService;

import java.util.List;

@RestController
public class EmbeddingController {

    @Autowired
    private EmbeddingService embeddingService;

    @GetMapping("/embed")
    public List<Float> embed(String text) {
        return embeddingService.embed(text);
    }
}