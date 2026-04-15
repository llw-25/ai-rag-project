package wanghui.airagproject.rag.splitter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：文档切割,将大小写在配置文件中
 *
 * @author LiuWangHui
 * @version 1.0.0
 * @date 2026/04/14 10:46:31
 */
@Data
@Component
@ConfigurationProperties(prefix = "rag")
public class TextSplitter {

    private int chunkSize;
    private int overlap;

    public List<String> split(String content) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < content.length(); i += (chunkSize - overlap)) {
            int end = Math.min(i + chunkSize, content.length());
            list.add(content.substring(i, end));
        }

        return list;
    }

}
