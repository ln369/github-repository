package com.cn.ln.Dao;

import com.cn.ln.Beans.Dept;
import com.cn.ln.Beans.Employee;
import com.cn.ln.Beans.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IEmployeeDao {

    List<Dept> selectAllDept();

    List<Job> selectAllJob();

    List<Employee> selectEmployee(Map map);

    int selectEmployeeCount(Employee employee);

    Employee selectEmployeeById(Integer id);

    int updateEmployee(Employee employee);

    int deleteEmployee(@Param("ids") Integer[] ids);

    int insertEmployee(Employee employee);
}

