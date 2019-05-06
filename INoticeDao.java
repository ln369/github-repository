package com.cn.ln.Dao;

import com.cn.ln.Beans.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface INoticeDao {
    int selectNoticeCount(Notice notice);

    List<Notice> selectNotice(Map map);

    Notice findNoticeById(Integer id);

    int modifyNotice(Notice notice);

    int insertNotice(Notice notice);

    int deleteNoticeById(@Param("ids") Integer[] ids);

}
