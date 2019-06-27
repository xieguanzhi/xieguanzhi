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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        //创建security对应的User对象并存入username和password
        org.springframework.security.core.userdetails.User u
                = new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(),user.getStatus()==1,true,true,true,authorities(user));
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

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void modifyRoleByUserIdAndRoleIds(String userId, String[] ids) {
        userDao.deleteAllUsers_RoleInUserId(userId);
        for (String id : ids) {
            Map<String,String> map = new HashMap<>();
            map.put("userid",userId);
            map.put("roleid",id);
            userDao.addUser_Role(map);
        }
    }
}
