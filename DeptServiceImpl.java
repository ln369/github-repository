package com.cn.ln.Service;

import com.cn.ln.Beans.Dept;
import com.cn.ln.Beans.PageModel;
import com.cn.ln.Dao.IDeptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeptServiceImpl implements IDeptService {

    @Resource
    private IDeptDao deptDao;

    @Override
    public List<Dept> findDept(PageModel pageModel, String name) {
        Map map = new HashMap();
        map.put("start",pageModel.getFirstLimitParam());
        map.put("pageSize",pageModel.getPageSize());
        map.put("name",name);
        return deptDao.selectDept(map);
    }

    @Override
    public int findDeptCount(String name) {
        return deptDao.selectDeptCount(name);
    }

    @Override
    public Dept findDeptById(Integer id) {
        return deptDao.selectDeptById(id);
    }

    @Override
    public int modifyDept(Dept dept) {
        return deptDao.updateDept(dept);
    }

    @Override
    public int removeDept(Integer[] ids) {
        Map map = new HashMap();
        map.put("ids",ids);
        return deptDao.deleteDept(map);
    }

    @Override
    public int addDept(Dept dept) {
        return deptDao.insertDept(dept);
    }
}
