package com.xieguanzhi.web;

import com.xieguanzhi.domain.Permission;
import com.xieguanzhi.domain.Role;
import com.xieguanzhi.service.PermissionService;
import com.xieguanzhi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;
@Controller
@RequestMapping("/role")
public class RoleWeb {

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionServiceImpl;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> all = roleService.findAll();
        mv.addObject("roleList",all);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public ModelAndView save(Role role){
        roleService.save(role);
        return findAll();
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        List<Permission> all = permissionServiceImpl.findAll();
        mv.addObject("role",role);
        mv.addObject("permissionList",all);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public ModelAndView addPermissionToRole(String roleId,String[] ids){
        ModelAndView mv = new ModelAndView();
        roleService.addPermissionToRole(roleId,ids);
        return findAll();
    }
}
