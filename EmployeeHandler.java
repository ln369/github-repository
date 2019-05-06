package com.cn.ln.Handler;


import com.cn.ln.Beans.Dept;
import com.cn.ln.Beans.Employee;
import com.cn.ln.Beans.Job;
import com.cn.ln.Beans.PageModel;
import com.cn.ln.Service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeHandler {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/findEmployee.do")
    public String findEmployee(@RequestParam(defaultValue = "1") int pageIndex, Integer job_id, Integer dept_id, Employee employee, Model model){

        this.assocation(job_id,dept_id,employee);
        System.out.println(employee);

        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(1);

        int count = employeeService.findEmployeeCount(employee);
        pageModel.setRecordCount(count);

        List<Dept> depts = employeeService.findAllDept();
        List<Job> jobs = employeeService.findAllJob();

        List<Employee> employees = employeeService.findEmployee(pageModel,employee);
        model.addAttribute("depts",depts);
        model.addAttribute("jobs",jobs);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("employee",employee);
        model.addAttribute("employees",employees);

        return "/jsp/employee/employee.jsp";

    }

    @RequestMapping("/modifyEmployee.do")
    public String modifyEmployee(String flag,Integer job_id,Integer dept_id,Employee employee,Model model){
        if (flag == null){
            List<Job> jobs =  employeeService.findAllJob();
            List<Dept> depts = employeeService.findAllDept();
            employee = employeeService.findEmployeeById(employee.getId());
            model.addAttribute("jobs",jobs);
            model.addAttribute("depts",depts);
            model.addAttribute("employee",employee);
            return "/jsp/employee/showUpdateEmployee.jsp";
        }else {
            this.assocation(job_id,dept_id,employee);
            System.out.println(employee+"修改");
            int rows = employeeService.modifyEmployee(employee);
            if (rows > 0){
                return "redirect:/employee/findEmployee.do";
            }else {
                model.addAttribute("fail","员工信息修改失败！");
                return "/jsp/fail.jsp";
            }
        }
    }

    @RequestMapping("/removeEmployee.do")
    public String removeEmployee(Integer[] ids,Model model){
        int rows = employeeService.removeEmployee(ids);
        if (rows == ids.length){
            return "redirect:/employee/findEmployee.do";
        }else {
            model.addAttribute("fail","员工信息删除失败！");
            return "/jsp/fail.jsp";
        }
    }

    @RequestMapping("/addEmployee.do")
    public String addEmployee(String flag,Employee employee,Model model){
        if (flag == null){
            List<Dept> depts = employeeService.findAllDept();
            List<Job> jobs = employeeService.findAllJob();
            model.addAttribute("jobs",jobs);
            model.addAttribute("depts",depts);
            return "/jsp/employee/showAddEmployee.jsp";
        }else {
            int rows = employeeService.addEmployee(employee);
            if (rows > 0){
                return "redirect:/employee/findEmployee.do";
            }else {
                model.addAttribute("fail","员工添加失败！");
                return "/jsp/fail.jsp";
            }
        }
    }

    public void assocation(Integer job_id,Integer dept_id,Employee employee){
        if (job_id != null){
            Job job = new Job();
            job.setId(job_id);
            employee.setJob(job);
        }
        if (dept_id != null){
            Dept dept = new Dept();
            dept.setId(dept_id);
            employee.setDept(dept);
        }
    }

}
