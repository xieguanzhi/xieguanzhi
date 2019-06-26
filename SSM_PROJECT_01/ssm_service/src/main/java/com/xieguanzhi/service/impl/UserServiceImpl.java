package com.xieguanzhi.service.impl;

import com.xieguanzhi.dao.UserDao;

import com.xieguanzhi.domain.Role;
import com.xieguanzhi.domain.User;
import com.xieguanzhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        //创建security对应的User对象并存入username和password
        org.springframework.security.core.userdetails.User u
                = new org.springframework.security.core.userdetails.User
                (user.getUsername(),"{noop}" + user.getPassword(),user.getStatus()==1,true,true,true,authorities(user));
        return u;
    }

    private List<GrantedAuthority> authorities(User user){
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
            list.add(authority);
        }
        return list;
    }
}
