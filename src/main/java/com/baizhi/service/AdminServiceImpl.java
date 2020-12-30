package com.baizhi.service;

import com.baizhi.dao.AdminMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.AdminExample;
import com.baizhi.entity.UserExample;
import com.baizhi.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * @author AdminServiceImpl
 * @time 2020/12/18-19:56
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminServie {
    @Resource
    AdminMapper adminMapper;


    @Override
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows) {

        //每页展示条数
        HashMap<String,Object> map=new HashMap<>();

        //设置当前页
        map.put("page",page);
        //创建条件对象
        AdminExample example=new AdminExample();
        //创建分页对象 第一个从第几条开始第二个展示几条
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
        //查询数据
        List<Admin> admins = adminMapper.selectByExampleAndRowBounds(example, rowBounds);
        //遍历集合
        map.put("rows",admins);

        //查询总条数
        int records = adminMapper.selectCountByExample(example);
        map.put("records",records);

        //计算总页数
        Integer tolal=records%rows==0?records/rows:records/rows+1;
        map.put("tolal",tolal);
        return map;
    }

    @Override
    public String add(Admin admin) {
        String uuid= UUIDUtil.getUUID();
        admin.setId(uuid);
        admin.setStatus("1");

        adminMapper.insertSelective(admin);
        //添加方法返回id
        return uuid;
    }


    @Override
    public void edit(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public void del(Admin admin) {
        adminMapper.deleteByPrimaryKey(admin);
    }

    @Override
    public Admin login(String username, String password) {
        Admin admin=adminMapper.queryByusername(username);
        if (admin==null) throw new RuntimeException("没有该管理员");
        if (admin.getPassword().equals(password)==false) throw new RuntimeException("密码错误");
        return admin;
    }

}
