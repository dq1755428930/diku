package com.baizhi.dao;

import com.baizhi.entity.Admin;
import com.baizhi.entity.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AdminMapper extends Mapper<Admin> {


    //登录根据名字查一个
    public Admin queryByusername(String username);

}