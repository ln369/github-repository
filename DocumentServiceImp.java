package com.cn.ln.Service;

import com.cn.ln.Beans.Document;
import com.cn.ln.Beans.PageModel;
import com.cn.ln.Dao.IDocumentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceImp implements IDocumentService {
    @Resource
    private IDocumentDao documentDao;

    @Override
    public int addDocument(Document document) {
        return documentDao.insertDocument(document);
    }

    @Override
    public int findDocumentCount(Document document) {
        return documentDao.selectDocumentCount(document);
    }

    @Override
    public List<Document> findDocument(PageModel pageModel, Document document) {
        Map map=new HashMap();
        map.put("pageModel",pageModel);
        map.put("document",document);
        return documentDao.selectDocument(map);
    }

    @Override
    public Document findDocumentById(Integer id) {
        return documentDao.selectDocumentById(id);
    }

    @Override
    public int updateDocument(Document document) {
        return documentDao.modifyDocument(document);
    }

    @Override
    public int removeDocument(Integer id) {
        return documentDao.deleteDocument(id);
    }
}
