package com.cn.ln.Service;

import com.cn.ln.Beans.Notice;
import com.cn.ln.Beans.PageModel;

import java.util.List;

public interface INoticeService {
    int findNoticeCount(Notice notice);

    List<Notice> findNotice(PageModel pageModel, Notice notice);

    Notice findNoticeById(Integer id);

    int updateNotice(Notice notice);

    int addNotice(Notice notice);

    int removeNoticeById(Integer[] ids);
}
