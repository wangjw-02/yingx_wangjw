package com.baizhi.service.impl;


import com.baizhi.dao.VideoDao;
import com.baizhi.entity.Video;
import com.baizhi.po.VideoPo;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunOssUtil;
import com.baizhi.vo.VideoVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * (Video)表服务实现类
 *
 * @author wangjiangwei
 * @since 2020-04-01 14:46:12
 */
@Service("videoService")
@Transactional
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoDao videoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Video queryById(String id) {
        return this.videoDao.queryById(id);
    }

    /**
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Map<String,Object> queryAllByLimit(Integer page,Integer rows) {
        Map<String,Object> map = new HashMap<>();
        Integer offest=(page-1)*rows;
        // 分页查询
        List<Video> videos = videoDao.queryAllByLimit(offest, rows);
        Integer records = videoDao.queryCount();
        Integer total = records%rows==0?records/rows:records/rows+1;
        map.put("page",page);
        map.put("rows",videos);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    @Override
    public Video insert(Video video) {
        video.setId(UUID.randomUUID().toString().replace("-",""));
        video.setPublishDate(new Date());
        this.videoDao.insert(video);
        return video;
    }


    @Override
    public void videoUpload(String id, MultipartFile path){
        // 获取上传视频的文件名
        String originalFilename = path.getOriginalFilename();
        String newName = System.currentTimeMillis()+"-"+originalFilename;
        // 上传视频
        AliyunOssUtil.uploadFileBytes("yingx-wangjw",path,"video/"+newName);

        // 截取视频封面
            //获取视频路径 https://yingx-wangjw.oss-cn-beijing.aliyuncs.com/video/
        String newPath="https://yingx-wangjw.oss-cn-beijing.aliyuncs.com/video/"+newName;
        String[] split = newName.split("\\.");
        String interceptName = split[0];
         // 拼接视频封面名称
        String coverName = interceptName+".jpg";
        /*
         * 截取视频第一帧做封面
         * 视频截取  并上传至阿里云
         * 参数：
         *   bucker: 存储空间名
         *   fileName:远程文件文件名    目录名/文件名
         *   coverName：截取的封面名
         * */
        AliyunOssUtil.videoCoverIntercept("yingx-wangjw","video/"+newName,"cover/"+coverName);
        // 修改数据库video信息
        Video video = new Video();
        video.setId(id);
        video.setPath(newPath);
        video.setCover("https://yingx-wangjw.oss-cn-beijing.aliyuncs.com/cover/"+coverName);
        System.out.println(video);
        videoDao.update(video);

    }

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    @Override
    public Video update(Video video) {
        this.videoDao.update(video);
        return this.queryById(video.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param
     * @return 是否成功
     */
    @Override
    public Map<String,Object> deleteById(Video video) {
        Map<String,Object> map = new HashMap<>();
        try {
            Video videos = videoDao.queryById(video.getId());
            String[] path = videos.getPath().split("/");
            String[] cover = videos.getCover().split("/");
            String videoName = path[path.length - 2] + "/" + path[path.length - 1];
            String coverName = cover[cover.length - 2] + "/" + cover[cover.length - 1];
            // 删除云空间的视频
            AliyunOssUtil.delete("yingx-wangjw",videoName);
            // 删除云空间的封面
            AliyunOssUtil.delete("yingx-wangjw",coverName);
            videoDao.deleteById(video.getId());
            map.put("status","200");
            map.put("message","删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status","400");
            map.put("message","删除失败");
        }
        return map;
    }

    @Override
    public List<VideoVo> queryByReleaseTime() {
        List<VideoPo> videoPos = videoDao.queryByReleaseTime();
        List<VideoVo> videoVos = new ArrayList<>();
        for (VideoPo vPo : videoPos) {
            String id = vPo.getId();
            // 根据id查询视频的点赞数
            Integer likeCount = 12;
            VideoVo videoVo = new VideoVo(vPo.getId(),vPo.getVTitle(),vPo.getVBrief(),vPo.getVPath(),vPo.getVCover(),vPo.getVPublishDate(),likeCount,vPo.getCateName(),vPo.getHeadImg());
            videoVos.add(videoVo);
        }
        return videoVos;
    }

}