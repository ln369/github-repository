package com.cn.ln.Dao;

import com.cn.ln.Beans.Document;

import java.util.List;
import java.util.Map;

public interface IDocumentDao {
    int insertDocument(Document document);

    int selectDocumentCount(Document document);

    List<Document> selectDocument(Map map);

    Document selectDocumentById(Integer id);

    int modifyDocument(Document document);

    int deleteDocument(Integer id);
}
