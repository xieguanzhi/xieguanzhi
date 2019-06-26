package com.xieguanzhi.dao;

import com.xieguanzhi.domain.Role;
import com.xieguanzhi.domain.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("SELECT * FROM users WHERE username = #{username}")
    @Results({
            @Result(
                    id = true,
                    column = "ID",
                    property = "id"
            ),
            @Result(
                    column = "ID",
                    property = "roles",
                    javaType = List.class,
                    many = @Many(
                            select = "com.xieguanzhi.dao.UserDao.findByUserID",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    User findByUserName(String username);


    @Select("select r.* from users_role ur,role r where ur.roleid=r.id and ur.userid=#{id}")
    Role findByUserID(String id);
}
