package wanghui.airagproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanghui.airagproject.service.DocService;

@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private DocService docService;

    @PostMapping("/add")
    public String add(String content) {
        docService.save(content);
        return "ok";
    }
}