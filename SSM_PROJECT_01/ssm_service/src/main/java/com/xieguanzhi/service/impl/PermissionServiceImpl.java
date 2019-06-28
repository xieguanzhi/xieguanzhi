package com.xieguanzhi.service.impl;

import com.xieguanzhi.dao.PermissionDao;
import com.xieguanzhi.domain.Permission;
import com.xieguanzhi.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }
}
