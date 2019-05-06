package com.cn.ln.Dao;

import com.cn.ln.Beans.Job;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IJobDao {
    int selectJobCount(Job job);
    List<Job> selectJob(Map map);
    int insertJob(Job job);
    Job selectJobById(int id);
    int modifyJob(Job job);
    int deleteJobById(@Param("ids") Integer[] ids);
}
