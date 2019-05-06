package com.cn.ln.Handler;

import com.cn.ln.Beans.Job;

import com.cn.ln.Beans.PageModel;

import com.cn.ln.Beans.User;
import com.cn.ln.Service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Job")
public class JobHandler {
    @Resource
    private IJobService jobService;

    @RequestMapping("/findJob.do")
    public String jobFind(@RequestParam(defaultValue = "1") int pageIndex, Job job, Model model){
        PageModel pageModel=new PageModel();
        pageModel.setPageIndex(pageIndex);

        int count=jobService.findUserCount(job);
        System.out.println(count);

        pageModel.setRecordCount(count);
        List<Job> jobs= jobService.findJob(job,pageModel);
        for (Job j:jobs){
            System.out.println(j);
        }
        model.addAttribute("job",job);
        model.addAttribute("jobs",jobs);
        model.addAttribute("pageModel",pageModel);
        return "/jsp/job/job.jsp";
    }
    @RequestMapping("/addJob.do")
    public String addUser(Job job, Model model){

        //System.out.println(user.getCreatedate());
        int rows=jobService.addJob(job);

        if (rows>0){
            //System.out.println(11111111);
            return "redirect:/Job/findJob.do";
        }else{
            model.addAttribute("message","添加失败，请重新填写");
            return "jsp/job/showAddJob.jsp";
        }
    }
    @RequestMapping("/updateJob.do")
    public String updateJob(String flag,Job job,Model model){

        if (flag == null) {
            job = jobService.findJobById(job.getId());
            model.addAttribute("job", job);
            return "/jsp/job/showUpdateJob.jsp";
        }else{
            System.out.println(flag);
            int rows = jobService.updateJob(job);
            System.out.println(rows);
            if (rows > 0){
                return "redirect:/Job/findJob.do";
            }else {
                model.addAttribute("fail","用户信息修改失败！");
                return "/jsp/fail.jsp";
            }
        }
    }


    @RequestMapping("/removeJob.do")
    public String removeJob(Integer ids[],Model model){
        try {
            int rows = jobService.removeJobById(ids);
            System.out.println(rows);
            if (rows == ids.length){
                return "redirect:/Job/findJob.do";
            }else {
                model.addAttribute("fail","用户信息删除失败！");
                return "/jsp/fail.jsp";
            }
        }catch (DataIntegrityViolationException e){
            model.addAttribute("fail","用户有发布公告或者文件，不能删除！");
            return "/jsp/fail.jsp";
        }
    }

}
