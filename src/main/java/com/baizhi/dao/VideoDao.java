package com.baizhi.dao;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Video)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-01 14:46:12
 */
public interface VideoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Video queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Video> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param video 实例对象
     * @return 对象列表
     */
    List<Video> queryAll(Video video);

    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 影响行数
     */
    int insert(Video video);

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 影响行数
     */
    int update(Video video);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     *  查询数据条数
     * @return
     */
    Integer queryCount();

    /**
     * 根据上传时间排序
     * @return
     */
    List<VideoPo>  queryByReleaseTime();

}