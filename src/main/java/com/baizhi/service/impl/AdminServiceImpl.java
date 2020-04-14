package com.baizhi.service.impl;


import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Admin queryByNameAndPwd(String name) {
        Admin byNameAndPwd = adminDAO.queryByNameAndPwd(name);
        return byNameAndPwd;
    }
}
