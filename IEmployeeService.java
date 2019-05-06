package com.cn.ln.Service;


import com.cn.ln.Beans.Dept;
import com.cn.ln.Beans.Employee;
import com.cn.ln.Beans.Job;
import com.cn.ln.Beans.PageModel;

import java.util.List;

public interface IEmployeeService {
    List<Dept> findAllDept();

    List<Job> findAllJob();

    List<Employee> findEmployee(PageModel pageModel, Employee employee);

    int findEmployeeCount(Employee employee);

    Employee findEmployeeById(Integer id);

    int modifyEmployee(Employee employee);

    int removeEmployee(Integer[] ids);

    int addEmployee(Employee employee);
}
