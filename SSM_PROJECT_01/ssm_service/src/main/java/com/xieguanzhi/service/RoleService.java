package com.xieguanzhi.service;

import com.xieguanzhi.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void save(Role role);

    Role findById(String id);

    void addPermissionToRole(String roleId,String[] ids);
}
