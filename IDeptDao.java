package com.cn.ln.Dao;


import com.cn.ln.Beans.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface IDeptDao {

    @SelectProvider(method = "selectDept",type = DeptSqlProvider.class )
    List<Dept> selectDept(Map map);

    @SelectProvider(method = "selectDeptCount",type = DeptSqlProvider.class )
    int selectDeptCount(String name);

    @Select("select * from dept_inf where id = #{id}")
    Dept selectDeptById(Integer id);

    @UpdateProvider(method = "updateDept",type = DeptSqlProvider.class)
    int updateDept(Dept dept);

    @DeleteProvider(method = "deleteDept",type = DeptSqlProvider.class)
    int deleteDept(Map map);

    @InsertProvider(method = "insertDept",type = DeptSqlProvider.class)
    int insertDept(Dept dept);
}
