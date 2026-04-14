package wanghui.airagproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wanghui.airagproject.dao.DocDao;
import wanghui.airagproject.pojo.Doc;

@Service
public class DocService {

    @Autowired
    private DocDao docDao;

    public void save(String content) {
        Doc doc = new Doc();
        doc.setContent( content);
        docDao.insert(doc);
    }
}