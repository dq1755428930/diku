package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface AdminServie {
    //登录
    public Admin login(String username, String password);

    HashMap<String,Object> queryUserPage(Integer page, Integer rows);

    String add(Admin admin);


    void edit(Admin admin);

    void del(Admin admin);
}
