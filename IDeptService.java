package com.cn.ln.Service;



import com.cn.ln.Beans.Dept;
import com.cn.ln.Beans.PageModel;

import java.util.List;

public interface IDeptService {
    List<Dept> findDept(PageModel pageModel, String name);

    int findDeptCount(String name);

    Dept findDeptById(Integer id);

    int modifyDept(Dept dept);

    int removeDept(Integer[] ids);

    int addDept(Dept dept);
}
