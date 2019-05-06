package com.cn.ln.Service;

import com.cn.ln.Beans.PageModel;
import com.cn.ln.Beans.User;

import java.util.List;

/**
 * company: www.abc.com
 * Author: ASUS
 * Create Data: 2019/3/11
 */
public interface IUserService {
    int removeUserById(Integer[] ids);
    int  modifyUser(User user);
    User findUserById(int id);
    User selectLoginUser(User user);
    //List<User> findUser(User users);
    List<User> findUser(User user, PageModel pageModel);
    int findUserCount(User user);
    int addUser(User user);
}
