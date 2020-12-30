package com.baizhi.service;

import com.baizhi.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface VideoService {

    public HashMap<String, Object> queryUserPage(Integer page, Integer rows);
    String add(Video video);

    void update(Video video);

    //将视频数据上传至本地
    void uploadVdieos(MultipartFile videoPath, String id, HttpServletRequest request);

    void uploadVdieosAliyun(MultipartFile videoPath, String id, HttpServletRequest request);

    void uploadVdieosAliyuns(MultipartFile videoPath, String id, HttpServletRequest request);

    void delete(Video video);


    void deletes(Video video);
}
