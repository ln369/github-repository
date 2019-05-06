package com.cn.ln.Handler;

import com.cn.ln.Beans.Notice;
import com.cn.ln.Beans.PageModel;
import com.cn.ln.Beans.User;
import com.cn.ln.Service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeHandler {
    @Autowired
    private INoticeService noticeService;
    @RequestMapping("/findNotice.do")
    public String findNotice(@RequestParam(defaultValue = "1") int pageIndex, PageModel pageModel, Notice notice, Model model){

        pageModel.setPageIndex(pageIndex);
        int count=noticeService.findNoticeCount(notice);
        pageModel.setRecordCount(count);
        List<Notice>notices=noticeService.findNotice(pageModel,notice);
//        for (Notice n:notices){
//            System.out.println(n);
//        }
        model.addAttribute("notice",notice);
        model.addAttribute("notices",notices);
        model.addAttribute("pageModel",pageModel);
//        return null;
        return "/jsp/notice/notice.jsp";
    }
    @RequestMapping("/updateNotice.do")
    public String updateNotice(String flag,Notice notice,Model model){
       // System.out.println(flag);

        if(flag!=null){
            Notice n=noticeService.findNoticeById(notice.getId());
            model.addAttribute("notice",n);
            return "/jsp/notice/showUpdateNotice.jsp";
        }
        System.out.println(notice);
        int rows= noticeService.updateNotice(notice);
        System.out.println(rows);
        if(rows > 0) {
            return "redirect:/notice/findNotice.do";
        }else{
            model.addAttribute("fail", "修改信息失败");
            return "/jsp/fail.jsp";
        }

    }
    @RequestMapping("/previewNotice.do")
    public String previewNotice(int id,Model model){
        Notice n=noticeService.findNoticeById(id);
        model.addAttribute("notice",n);
        return "/jsp/notice/previewNotice.jsp";
    }
    @RequestMapping("/addNotice.do")
    private String addNotice(Notice notice,Model model,HttpSession session){
        User login_user = (User) session.getAttribute("login_user");
        notice.setUser(login_user);
        System.out.println(notice);
        int no=noticeService.addNotice(notice);
        if (no<=0){
            model.addAttribute("fail", "添加信息失败");
            return "/jsp/fail.jsp";
        }
        return "redirect:/notice/findNotice.do";
    }
    @RequestMapping("/removeNotice.do")
    public String removeNotice(Integer ids[], Model model) {

        try {
            int rows = noticeService.removeNoticeById(ids);
            System.out.println(rows);
            if (rows == ids.length) {
                return "redirect:/notice/findNotice.do";
            } else {
                model.addAttribute("fail", "信息删除失败！");
                return "/jsp/fail.jsp";
            }
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("fail", "有级联用户，不能删除！");
            return "/jsp/fail.jsp";
        }
    }
}
