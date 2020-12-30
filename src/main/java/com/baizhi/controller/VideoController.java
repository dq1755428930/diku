package com.baizhi.controller;

import com.baizhi.dao.VideoMapper;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author VideoController
 * @time 2020/12/24-10:39
 */
@Controller
@RequestMapping("video")
public class VideoController {

    @Resource
    VideoService videoService;

    @Resource
    VideoMapper videoMapper;

    @ResponseBody
    @RequestMapping("queryAllPage")
    public HashMap<String, Object> queryAllPage(Integer page, Integer rows) {
        HashMap<String, Object> map = videoService.queryUserPage(page, rows);
        return map;
    }


    @ResponseBody
    @RequestMapping("edit")
    public Object edit(Video video, String oper) {

        if (oper.equals("add")) {
            String id = videoService.add(video);
            return id;
        }

        if (oper.equals("edit")) {
            videoService.update(video);
        }

        if (oper.equals("del")) {
            videoService.deletes(video);
        }

        return "";
    }

    @ResponseBody
    @RequestMapping("uploadVdieo")
    public void uploadVdieo(MultipartFile videoPath, String id, HttpServletRequest request) {
        videoService.uploadVdieosAliyun(videoPath, id, request);
    }



}

