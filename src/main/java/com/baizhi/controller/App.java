package com.baizhi.controller;

/**
 * @Author: wangjiangwei
 * @Date 2020/4/6 21:47
 * @Version 1.0
 */

import com.baizhi.common.CommonResult;
import com.baizhi.po.CategoryPo;
import com.baizhi.service.CategoryService;
import com.baizhi.service.VideoService;
import com.baizhi.vo.VideoVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("app")
public class App {
    @Resource
    VideoService videoService;
    @Resource
    CategoryService categoryService;


    @RequestMapping("queryByReleaseTime")
    public CommonResult queryByReleaseTime(){

        try {
            List<VideoVo> videoVos = videoService.queryByReleaseTime();
            return new CommonResult().success(videoVos);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed();
        }
    }

    @RequestMapping("queryAllCategory")
    public CommonResult queryAllCategory(){

        try{
            List<CategoryPo> categoryPos = categoryService.queryAllCategory();
            return new CommonResult().success(categoryPos);
        }catch(Exception e){
            e.printStackTrace();
            return new CommonResult().failed();
        }
    }
}
