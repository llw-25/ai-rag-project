package wanghui.airagproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wanghui.airagproject.service.AiService;


@RestController
public class Aicontroller {

    @Autowired
    private AiService aiService;

    @GetMapping("/chat")
    public String chat(String msg) {
        return aiService.chat(msg);
    }
}