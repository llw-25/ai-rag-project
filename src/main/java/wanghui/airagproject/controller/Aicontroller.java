package wanghui.airagproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wanghui.airagproject.service.AiService;


/**
 * 类描述：聊天接口
 *
 * @author LiuWangHui
 * @version 1.0.0
 * @date 2026/04/15 14:24:22
 */
@RestController
public class Aicontroller {

    @Autowired
    private AiService aiService;

    @GetMapping("/chat")
    public String chat(String msg) {
        return aiService.chat(msg);
    }
}