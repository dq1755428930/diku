package com.baizhi.dao;

import com.baizhi.entity.Category;
import com.baizhi.entity.CategoryExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CategoryMapper extends Mapper<Category> {

    //查一级
    public List<Category> queryAllOne();
    //查二级
    public List<Category> queryAllTwo();
}