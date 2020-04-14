package com.baizhi.service;

import com.baizhi.entity.Video;
import com.baizhi.vo.VideoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * (Video)表服务接口
 *
 * @author wangjiangwei
 * @since 2020-04-01 14:46:12
 */
public interface VideoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Video queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
     Map<String,Object> queryAllByLimit(Integer page, Integer rows);

    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    Video insert(Video video);

    /**
     * 异步视频上传
     * @param id
     * @param path
     */
     void videoUpload(String id, MultipartFile path);

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    Video update(Video video);

    /**
     * 通过主键删除数据
     *
     * @param video 实例对象
     * @return 是否成功
     */
    Map<String,Object> deleteById(Video video);

    /**
     * 根据上传时间排序
     * @return
     */
    List<VideoVo> queryByReleaseTime();
}