package com.baizhi.service;

import com.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-03-28 15:02:46
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    String insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     *  分页查询所有用户
     *
     */
    Map<String,Object> queryAllByPage(Integer rows,Integer page);

    /**
     * 上传头像
     * @param id
     * @param headImg
     * @param request
     */
    void fileUpload(String id, MultipartFile headImg, HttpServletRequest request);

    /**
     * 修改用户状态
     * @param user
     */
    void updateStatus(User user);

    /**
     *  发送手机验证码
     * @param phone
     * @return
     */
    String phoneCode(String phone);

    /**
     * 查询所有用户信息，并导出
     * @return
     */
    List<User> queryAllUser();

}