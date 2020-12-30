package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminServie;
import com.baizhi.util.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author AdminController
 * @time 2020/12/18-19:59
 */
@Controller
@RequestMapping("/px")
@Scope("prototype")
public class AdminController {

    @Autowired
    AdminServie adminServie;
    @RequestMapping("/yan")
    public String code(HttpSession session, HttpServletResponse response) throws IOException {
        CreateValidateCode vCode=new CreateValidateCode(100,30,4,10);
                session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
        return null;
    }
    @RequestMapping("/login")
    public String login(Admin admin, String ucode, HttpSession session){
        if(ucode.equals(session.getAttribute("code"))==false) {
            session.setAttribute("message", "验证码错误");
            return "error";
        }
        session.setAttribute("admin",admin);
        adminServie.login(admin.getUsername(),admin.getPassword());
        return "/main/main";
    }

    @RequestMapping("/logout")
    public String loginout(HttpSession session){
        session.removeAttribute("admin");
        return "/login/login";
    }

    @ResponseBody
    @RequestMapping("edit")
    public void edit(Admin admin, String oper){
        if(oper.equals("add")){
            adminServie.add(admin);
        }
        if(oper.equals("edit")){
            adminServie.edit(admin);
        }
        if(oper.equals("del")){
            adminServie.del(admin);
        }
    }

    @ResponseBody
    @RequestMapping("queryVideoPage")
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows){
        return adminServie.queryUserPage(page,rows);
    }

}
