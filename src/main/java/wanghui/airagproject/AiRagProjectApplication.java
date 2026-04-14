package wanghui.airagproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("wanghui.airagproject.dao")
public class AiRagProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiRagProjectApplication.class, args);
	}

}
