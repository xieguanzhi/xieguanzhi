package com.xieguanzhi.dao;

import com.xieguanzhi.domain.Permission;
import com.xieguanzhi.domain.Role;
import com.xieguanzhi.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    @Select("SELECT * FROM users WHERE username = #{username}")
    @Results(
            id = "user",
            value = {
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
                                    select = "com.xieguanzhi.dao.UserDao.findRoleByUserID",
                                    fetchType = FetchType.LAZY
                            )
                    )
            })
    User findByUserName(String username);


    @Select("select r.* from users_role ur,role r where ur.roleid=r.id and ur.userid=#{id}")
    @Results({
            @Result(
                    id = true,
                    column = "ID",
                    property = "id"
            ),
            @Result(
                    column = "ID",
                    property = "permissions",
                    javaType = List.class,
                    many = @Many(
                            select = "com.xieguanzhi.dao.PermissionDao.findPermissionByRoleID",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    Role findRoleByUserID(String id);


    @Select("select * from users")
    @ResultMap("user")
    List<User> findAll();

    @Insert("insert into users (USERNAME,PASSWORD,EMAIL,PHONENUM,STATUS) values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(User user);

    @Select("select * from users where id = #{id}")
    @ResultMap("user")
    User findById(String id);

    @Delete("delete users_role where userid=#{id}")
    void deleteAllUsers_RoleInUserId(String id);

    @Insert("insert into users_role values(#{userid},#{roleid})")
    void addUser_Role(Map<String,String> userAndRole);
}
