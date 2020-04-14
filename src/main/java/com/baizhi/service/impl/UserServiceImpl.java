package com.baizhi.service.impl;


import com.baizhi.annotation.MyAnnotation;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.AliyunOssUtil;
import com.baizhi.util.AliyunSendPhoneUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-03-28 15:02:46
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @MyAnnotation(value = "查询单个用户")
    @Override
    public User queryById(String id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    @MyAnnotation(value = "查询所有用户")
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    @MyAnnotation(value = "添加用户")
    public String insert(User user) {
        String replace = UUID.randomUUID().toString().replace("-", "");
        user.setId(replace);
        user.setStatus("1");
        user.setCreateDate(new Date());
        this.userDao.insert(user);
        return replace;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    @MyAnnotation(value = "修改用户")
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @MyAnnotation(value = "删除用户")
    public boolean deleteById(String id) {
        User user = userDao.queryById(id);
        String headImg = user.getHeadImg();
        String[] split = headImg.split("/");
        String fileName= split[split.length-2]+"/"+split[split.length-1];
        AliyunOssUtil.delete("yingx-wangjw",fileName);
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    @MyAnnotation(value = "查询所有用户")
    public Map<String, Object> queryAllByPage(Integer rows, Integer page) {
        Map<String,Object> map = new HashMap<>();
        Integer offest=(page-1)*rows;
        List<User> users = userDao.queryAllByLimit(offest, rows);
        int records = userDao.queryAllSum();
        Integer total = records%rows==0?records/rows:records/rows+1;
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",users);
        return map;
    }

    @Override
    public void fileUpload(String id, MultipartFile headImg, HttpServletRequest request) {
       /* try {
            String realPath = request.getSession().getServletContext().getRealPath("/headImg");
            File file = new File(realPath);
            if (!file.exists()){
                file.mkdir();
            }
            String originalFilename = headImg.getOriginalFilename();
            String fileName = new Date().getTime()+originalFilename;
            headImg.transferTo(new File(realPath,fileName));
            User user = new User();
            user.setId(id);
            user.setHeadImg(fileName);
            userDao.update(user);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        // 获取文件名
        String filename = headImg.getOriginalFilename();
        // 设置上传文件名
        String newName = new Date().getTime()+"-"+filename;
        // 上传文件至阿里云oss
        AliyunOssUtil.uploadFileBytes("yingx-wangjw",headImg,"cover/"+newName);
        User u = new User();
        u.setId(id);
        u.setHeadImg("https://yingx-wangjw.oss-cn-beijing.aliyuncs.com/cover/"+newName);
        userDao.update(u);
    }

    @Override
    @MyAnnotation(value = "修改用户信息")
    public void updateStatus(User user) {
        if ("0".equals(user.getStatus())){
            user.setStatus("1");
        }else{
            user.setStatus("0");
        }
        userDao.update(user);
    }

    @Override
    public String phoneCode(String phone) {
        String random = AliyunSendPhoneUtil.getRandom(6);
        String message = AliyunSendPhoneUtil.sendCode(phone, random);
        return message;
    }

    @Override
    public List<User> queryAllUser() {
        List<User> users = userDao.queryAll(new User());
        return users;
    }


}