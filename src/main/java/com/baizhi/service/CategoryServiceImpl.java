package com.baizhi.service;

import com.baizhi.annotation.AddLog;
import com.baizhi.dao.CategoryMapper;
import com.baizhi.entity.*;
import com.baizhi.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.DoubleToIntFunction;

/**
 * @author CategoryServiceImpl
 * @time 2020/12/22-16:44
 */

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows) {

        //每页展示条数
        HashMap<String,Object> map=new HashMap<>();

        //设置当前页
        map.put("page",page);
        //创建条件对象
        CategoryExample example=new CategoryExample();
        //创建分页对象 第一个从第几条开始第二个展示几条
        example.createCriteria().andLevelsEqualTo(1);
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
        //查询数据
        List<Category> categories = categoryMapper.queryAllOne();
        //遍历集合
        map.put("rows",categories);

        //查询总条数
        int records = categoryMapper.selectCountByExample(example);
        map.put("records",records);

        //计算总页数
        Integer tolal=records%rows==0?records/rows:records/rows+1;
        map.put("tolal",tolal);
        return map;
    }
    @Override
    public HashMap<String, Object> queryUserPage2(Integer page, Integer rows,String parent_id) {

        //每页展示条数
        HashMap<String,Object> map=new HashMap<>();

        //设置当前页
        map.put("page",page);
        //创建条件对象
        CategoryExample example=new CategoryExample();
        //创建分页对象 第一个从第几条开始第二个展示几条
        example.createCriteria().andParentIdEqualTo(parent_id);
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);

        //查询数据
        List<Category> categories = categoryMapper.selectByExampleAndRowBounds(example, rowBounds);
        //遍历集合
        map.put("rows",categories);

        //查询总条数
        int records = categoryMapper.selectCountByExample(example);
        map.put("records",records);

        //计算总页数
        Integer tolal=records%rows==0?records/rows:records/rows+1;
        map.put("tolal",tolal);
        return map;
    }

    @Override
    @AddLog(value = "添加类别")
    public String add(Category category) {
        String uuid= UUIDUtil.getUUID();
        category.setId(uuid);
        category.setLevels(1);


        categoryMapper.insertSelective(category);
        System.out.println(category);
        //添加方法返回id
        return uuid;
    }
    @Override
    @AddLog(value = "修改类别")
    public void edit(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    @AddLog(value = "删除类别")
    public void del(Category category) {
        categoryMapper.deleteByPrimaryKey(category);
    }
}
