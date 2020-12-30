package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author CategoryController
 * @time 2020/12/22-16:49
 */

@Controller
@RequestMapping("category")
public class CategoryController {
        @Resource
    CategoryService categoryService;

    @ResponseBody
    @RequestMapping("queryUserPage")
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows){
        return categoryService.queryUserPage(page,rows);
    }
    @ResponseBody
    @RequestMapping("queryUserPage2")
    public HashMap<String, Object> queryUserPage2(Integer page, Integer rows,String parent_id){
        return categoryService.queryUserPage2(page,rows,parent_id);
    }

    @ResponseBody
    @RequestMapping("edit")
    public void edit(Category category, String oper){
        if(oper.equals("add")){
            categoryService.add(category);
        }
        if(oper.equals("edit")){
            categoryService.edit(category);
        }
        if(oper.equals("del")){
            categoryService.del(category);
        }
    }
}
