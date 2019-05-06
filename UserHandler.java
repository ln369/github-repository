package com.cn.ln.Handler;


import com.cn.ln.Beans.Job;
import com.cn.ln.Beans.PageModel;
import com.cn.ln.Beans.User;
import com.cn.ln.Service.IJobService;
import com.cn.ln.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * company: www.abc.com
 * Author: ASUS
 * Create Data: 2019/3/11
 */
@Controller
// @RequestMapping("/user")
public class UserHandler {
    @Autowired
    private IUserService userService;
    @Resource
    private IJobService jobService;
    @RequestMapping("/login.do")
    public String login(User user, HttpSession session, Model model){

       // System.out.println(user.getLoginname());
        User login_user=userService.selectLoginUser(user);
       // System.out.println(login_user.getPassword());

        if (login_user!=null){
            //System.out.println(11111);
            session.setAttribute("login_user",login_user);
            //System.out.println(222222);
            return "jsp/main.jsp";
        }
        else{
            model.addAttribute("message","账号或密码错误，请重新登陆");
            return "/index.jsp";
        }
    }
    @RequestMapping("/logout.do")
    public String logout(HttpSession session,Model model){
        session.invalidate();
        model.addAttribute("massage","您已退出该程序");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/findUser.do")
     public String userFind(@RequestParam(defaultValue = "1") int pageIndex, User user, Model model){
       System.out.println("user="+user);
       PageModel pageModel=new PageModel();
       pageModel.setPageIndex(pageIndex);

         int count=userService.findUserCount(user);
       // System.out.println(count);

        pageModel.setRecordCount(count);
         List<User> users= userService.findUser(user,pageModel);
        System.out.println(users);
         model.addAttribute("user",user);
         model.addAttribute("users",users);
         model.addAttribute("pageModel",pageModel);
        return "/jsp/user/user.jsp";
     }
    @RequestMapping("/addUser.do")
    public String addUser(User user,Model model){
        Date data=new Date();
        user.setCreatedate(data);
        //System.out.println(user.getCreatedate());
        int rows=userService.addUser(user);

        if (rows>0){
            //System.out.println(11111111);
            return "redirect:/findUser.do";
        }else{
            model.addAttribute("message","添加失败，请重新填写");
            return "jsp/user/showAddUser.jsp";
        }

    }
    @RequestMapping("/modifyUser.do")
    public String modifyUser(int id,Model model,String flag,User user) {
        if (flag == null) {
            user = userService.findUserById(id);
            model.addAttribute("user", user);
            return "/jsp/user/showUpdateUser.jsp";
        }else{
            int rows = userService.modifyUser(user);
            if (rows > 0){
                return "redirect:/findUser.do";
            }else {
                model.addAttribute("fail","用户信息修改失败！");
                return "/jsp/fail.jsp";
            }
        }
    }
    @RequestMapping("/removeUser.do")
    public String removeUser(Integer ids[],Model model,HttpSession session){

        for (Integer id:ids){
            System.out.println(id);
        }
        User login_user = (User) session.getAttribute("login_user");
        System.out.println(login_user);
        for (Integer id:ids){
           // System.out.println(id);
            if (id==login_user.getId()){
                System.out.println("2222222222221111111");
                model.addAttribute("fail","不能删除当前登录用户！");
                return "/jsp/fail.jsp";
            }
        }
        try {
            System.out.println("1111111111111111");
            int rows = userService.removeUserById(ids);
            System.out.println(rows);
            if (rows == ids.length){
                return "redirect:/findUser.do";
            }else {
                model.addAttribute("fail","用户信息删除失败！");
                return "/jsp/fail.jsp";
            }
        }catch (DataIntegrityViolationException e){
            model.addAttribute("fail","用户有发布公告或者文件，不能删除！");
            return "/jsp/fail.jsp";
        }
    }



}
