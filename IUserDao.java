package com.cn.ln.Dao;

import com.cn.ln.Beans.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: ASUS
 * Create Data: 2019/3/11
 */
public interface IUserDao {
    int deleteUserById(@Param("ids") Integer[] ids);
    int updateUser(User user);
    User selectUserById(int id);
    User findLoginUser(User user);
   // List<User> selectUser(User user);
    List<User> selectUser(Map map);
    int selectUserCount(User user);
    int insertUser(User user);
}
