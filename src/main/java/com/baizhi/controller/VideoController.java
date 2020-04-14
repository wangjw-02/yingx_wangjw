package com.baizhi.controller;


import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (Video)表控制层
 *
 * @author wangjiangwei
 * @since 2020-04-01 14:46:12
 */
@RestController
@RequestMapping("video")
public class VideoController {
    /**
     * 服务对象
     */
    @Resource
    private VideoService videoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Video selectOne(String id) {
        return this.videoService.queryById(id);
    }


    /**
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("queryByPage")
    public Map<String,Object> queryByPage(Integer page, Integer rows){
        Map<String, Object> map = videoService.queryAllByLimit(page, rows);
        return map;
    }


    /**
     *      视频的增删改操作
     * @param video
     * @param oper
     * @return
     */
    @RequestMapping("dml")
    public Object dml(Video video,String oper){

        if ("add".equals(oper)){
            Video insert = videoService.insert(video);
            return insert.getId();
        }else if ("edit".equals(oper)){

        }else if ("del".equals(oper)){
            Map<String, Object> map = videoService.deleteById(video);
            return map;
        }

        return null;
    }

    @RequestMapping("videoUpload")
    public void videoUpload(String id, MultipartFile path){
        videoService.videoUpload(id,path);
    }


}