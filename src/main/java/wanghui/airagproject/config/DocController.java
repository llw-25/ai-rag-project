package wanghui.airagproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanghui.airagproject.service.DocService;

/**
 * 类描述：长文档处理
 *
 * @author LiuWangHui
 * @version 1.0.0
 * @date 2026/04/14 15:24:34
 */

@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private DocService docService;

    @PostMapping("/upload")
    public String upload(@RequestBody String content) {
        docService.save(content);
        return "ok";
    }
}

