package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * @author UserController
 * @time 2020/12/21-19:36
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    UserService userService;


    @ResponseBody
    @RequestMapping("queryUserPage")
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows){
        return userService.queryUserPage(page,rows);
    }

    @ResponseBody
    @RequestMapping("edit")
    public Object edit(User user, String oper){
        String id=null;
        if(oper.equals("add")){
            id=userService.add(user);
        }
        if(oper.equals("edit")){
            id=userService.edit(user);
        }
        if(oper.equals("del")){
            userService.del(user);
        }
        return id;
    }
    @RequestMapping("uploadUserCover")
    public void uploadUserCover(MultipartFile headImg,String id,HttpServletRequest request) {
        userService.uploadUserCover(headImg,id,request);
    }
}
