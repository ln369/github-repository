package com.cn.ln.Handler;


import com.cn.ln.Beans.Dept;
import com.cn.ln.Beans.PageModel;
import com.cn.ln.Service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptHandler {

    @Autowired
    private IDeptService deptService;

    @RequestMapping("/findDept.do")
    public String findDept(@RequestParam(defaultValue = "1") int pageIndex, String name, Model model){

        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);

        int count = deptService.findDeptCount(name);
        pageModel.setRecordCount(count);

        List<Dept> depts = deptService.findDept(pageModel,name);
        /*for (Dept d:depts){
            System.out.println(d);
        }*/
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("depts",depts);
        model.addAttribute("name",name);
        return "/jsp/dept/dept.jsp";

    }

    @RequestMapping("/findDeptById.do")
    public String findDeptById(Integer id,Model model){
        Dept dept = deptService.findDeptById(id);
        model.addAttribute("dept",dept);
        return "/jsp/dept/showUpdateDept.jsp";
    }

    @RequestMapping("/modifyDept.do")
    @ResponseBody
    public String modifyDept(Dept dept){
        //System.out.println(dept+"123");
        int rows = deptService.modifyDept(dept);
        if (rows > 0){
            return "OK";
        }else {
            return "FAIL";
        }
    }

    @RequestMapping("/removeDept.do")
    @ResponseBody
    public String removeDept(Integer[] ids){
        //System.out.println(ids[0]);
        try {
            int rows = deptService.removeDept(ids);
            if (rows == ids.length){
                return "OK";
            }else {
                return "FAIL";
            }
        }catch (DataIntegrityViolationException e){
            return "ERROR";
        }
    }

    @RequestMapping("/addDept.do")
    @ResponseBody
    public String addDept(Dept dept){
        int rows = deptService.addDept(dept);
        if (rows > 0){
            return "OK";
        }else {
            return "FAIL";
        }
    }

}
