package com.cn.ln.Service;

import com.cn.ln.Beans.Job;
import com.cn.ln.Beans.PageModel;

import com.cn.ln.Dao.IJobDao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class JobServiceI implements IJobService {
    @Resource
    private IJobDao jobDao;
    @Override
    public int findUserCount(Job job) {
        return jobDao.selectJobCount(job);
    }

    @Override
    public List<Job> findJob(Job job, PageModel pageModel) {
        Map map=new HashMap<>();
        map.put("name",job.getName());
        map.put("remark",job.getRemark());
        map.put("start",pageModel.getFirstLimitParam());
        map.put("pageSize",pageModel.getPageSize());

        List<Job> jobs = jobDao.selectJob(map);
        return jobs;
    }

    @Override
    public int addJob(Job job) {

        return jobDao.insertJob(job);
    }

    @Override
    public Job findJobById(int id) {

        return jobDao.selectJobById(id);
    }

    @Override
    public int updateJob(Job job) {
        return jobDao.modifyJob(job);
    }

    @Override
    public int removeJobById(Integer[] ids) {
        return jobDao.deleteJobById(ids);
    }
}
