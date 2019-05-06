package com.cn.ln.Service;

import com.cn.ln.Beans.Job;
import com.cn.ln.Beans.PageModel;

import java.util.List;

public interface IJobService {
    int findUserCount(Job job);
    List<Job> findJob(Job job, PageModel pageModel);
    int addJob(Job job);
    Job findJobById(int id);
    int updateJob(Job job);
    int removeJobById(Integer[] ids);
}
