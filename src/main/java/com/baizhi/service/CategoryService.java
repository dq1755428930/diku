package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Category;

import java.util.HashMap;

public interface CategoryService {
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows);

    public HashMap<String, Object> queryUserPage2(Integer page, Integer rows,String parent_id);

    String add(Category category);


    void edit(Category category);

    void del(Category category);
}
