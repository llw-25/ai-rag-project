package wanghui.airagproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wanghui.airagproject.dao.DocDao;
import wanghui.airagproject.pojo.Doc;
import wanghui.airagproject.rag.splitter.TextSplitter;

import java.util.List;

@Service
public class DocService {

    @Autowired
    private TextSplitter splitter;
    @Autowired
    private DocDao docDao;

    public void save(String content) {
        List<String> chunks = splitter.split(content);
        for (String chunk : chunks) {
            Doc doc = new Doc();
            doc.setContent(chunk);
            docDao.insert(doc);
        }
    }
}