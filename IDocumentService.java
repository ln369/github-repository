package com.cn.ln.Service;

import com.cn.ln.Beans.Document;
import com.cn.ln.Beans.PageModel;

import java.util.List;

public interface IDocumentService {
    int addDocument(Document document);

    int findDocumentCount(Document document);

    List<Document> findDocument(PageModel pageModel, Document document);

    Document findDocumentById(Integer id);

    int updateDocument(Document document);

    int removeDocument(Integer id);
}
