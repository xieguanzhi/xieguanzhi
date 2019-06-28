package com.xieguanzhi.service.impl;

import com.xieguanzhi.dao.RoleDao;
import com.xieguanzhi.dao.UserDao;
import com.xieguanzhi.domain.Role;
import com.xieguanzhi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        roleDao.deleteAllRole_PermissionInRoleId(roleId);
        for (String id : ids) {
            Map<String,String> map = new HashMap<>();
            map.put("permissionId",id);
            map.put("roleId",roleId);
        }
    }
}
