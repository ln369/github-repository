package com.cn.ln.Service;


import com.cn.ln.Beans.PageModel;
import com.cn.ln.Beans.User;
import com.cn.ln.Dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: ASUS
 * Create Data: 2019/3/11
 */
@Service
public class UserServiceI implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public int removeUserById(Integer[] ids) {
        return userDao.deleteUserById(ids);

    }

    @Override
    public int modifyUser(User user) {
        return userDao.updateUser(user);

    }

    @Override
    public User findUserById(int id) {
        return userDao.selectUserById(id);

    }

    @Override
    public User selectLoginUser(User user){
        return userDao.findLoginUser(user);
    }

    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        Map map=new HashMap<>();
        map.put("username",user.getUsername());
        map.put("status",user.getStatus());
        map.put("start",pageModel.getFirstLimitParam());
        map.put("pageSize",pageModel.getPageSize());

         List<User> users = userDao.selectUser(map);
         return users;
    }

    @Override
    public int findUserCount(User user) {

        return userDao.selectUserCount(user);
    }

    @Override
    public int addUser(User user) {

        return userDao.insertUser(user);
    }



}
