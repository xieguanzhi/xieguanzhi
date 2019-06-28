package com.xieguanzhi.web;

import com.xieguanzhi.domain.Role;
import com.xieguanzhi.domain.User;
import com.xieguanzhi.service.RoleService;
import com.xieguanzhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserWeb {

    @Autowired
    UserService userServiceImpl;
    @Autowired
    RoleService roleServiceImpl;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<User> all = userServiceImpl.findAll();
        mv.addObject("userList",all);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public ModelAndView save(User user){
        userServiceImpl.save(user);
        return findAll();
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        User user = userServiceImpl.findById(id);
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv = new ModelAndView();
        User user = userServiceImpl.findById(id);
        mv.addObject("user",user);
        List<Role> all = roleServiceImpl.findAll();
        mv.addObject("roleList",all);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public ModelAndView addRoleToUser(String userId,String[] ids){
        userServiceImpl.modifyRoleByUserIdAndRoleIds(userId,ids);
        return findUserByIdAndAllRole(userId);
    }
}
