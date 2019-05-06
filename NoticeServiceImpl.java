package com.cn.ln.Service;

import com.cn.ln.Beans.Notice;
import com.cn.ln.Beans.PageModel;
import com.cn.ln.Dao.INoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements INoticeService {
    @Resource
    private INoticeDao noticeDao;

    @Override
    public int findNoticeCount(Notice notice) {
        return noticeDao.selectNoticeCount(notice);
    }

    @Override
    public List<Notice> findNotice(PageModel pageModel, Notice notice) {
        Map map=new HashMap();
        map.put("pageModel",pageModel);
        map.put("notice",notice);
        return noticeDao.selectNotice(map);
    }

    @Override
    public Notice findNoticeById(Integer id) {

        return noticeDao.findNoticeById(id);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeDao.modifyNotice(notice);
    }

    @Override
    public int addNotice(Notice notice) {
        return noticeDao.insertNotice(notice);
    }

    @Override
    public int removeNoticeById(Integer[] ids) {
        return noticeDao.deleteNoticeById(ids);
    }
}
