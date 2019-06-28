package com.xieguanzhi.web;

import com.xieguanzhi.domain.Permission;
import com.xieguanzhi.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionWeb {

    @Autowired
    PermissionService permissionServiceImpl;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Permission> all = permissionServiceImpl.findAll();
        mv.addObject("permissionList",all);
        mv.setViewName("permission-list");
        return mv;
    }
}
