package wanghui.airagproject.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wanghui.airagproject.utils.AiHttpUtil;

@Component
public class AiConfig {

    @Value("${ai.api-key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        AiHttpUtil.setApiKey(apiKey);
    }
}