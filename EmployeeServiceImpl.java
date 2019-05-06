package com.cn.ln.Service;


import com.cn.ln.Beans.Dept;
import com.cn.ln.Beans.Employee;
import com.cn.ln.Beans.Job;
import com.cn.ln.Beans.PageModel;
import com.cn.ln.Dao.IEmployeeDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Resource
    private IEmployeeDao employeeDao;

    @Override
    public List<Dept> findAllDept() {
        return employeeDao.selectAllDept();
    }

    @Override
    public List<Job> findAllJob() {
        return employeeDao.selectAllJob();
    }

    @Override
    public List<Employee> findEmployee(PageModel pageModel, Employee employee) {
        Map map = new HashMap();
        map.put("pageModel",pageModel);
        map.put("employee",employee);
        return employeeDao.selectEmployee(map);
    }

    @Override
    public int findEmployeeCount(Employee employee) {
        return employeeDao.selectEmployeeCount(employee);
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeDao.selectEmployeeById(id);
    }

    @Override
    public int modifyEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public int removeEmployee(Integer[] ids) {
        return employeeDao.deleteEmployee(ids);
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeDao.insertEmployee(employee);
    }
}
