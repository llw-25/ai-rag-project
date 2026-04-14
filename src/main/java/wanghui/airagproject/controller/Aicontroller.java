package wanghui.airagproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wanghui.airagproject.utils.AiHttpUtil;

@RestController
public class Aicontroller {


    @GetMapping("/ai/chat")
    public String chat(String msg) {
        return AiHttpUtil.chat(msg);
    }
}