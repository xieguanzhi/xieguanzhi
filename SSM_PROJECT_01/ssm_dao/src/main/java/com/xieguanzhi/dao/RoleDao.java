package com.xieguanzhi.dao;

import com.xieguanzhi.domain.Role;
import com.xieguanzhi.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleDao {

    @Select("select * from role")
    @Results(
            id = "role",
            value = {
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
            ),
            @Result(
                    column = "ID",
                    property = "users",
                    many = @Many(
                            select = "com.xieguanzhi.dao.RoleDao.findUserByRoleId",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    List<Role>findAll();

    @Select("select u.* from users_role ur,users u where ur.userid=u.id and ur.roleid=#{id}")
    List<User> findUserByRoleId(String id);

    @Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id = #{id}")
    @ResultMap("role")
    Role findById(String id);

    @Delete("delete role_permission where roleId=#{id}")
    void deleteAllRole_PermissionInRoleId(String id);

    @Insert("insert into role_permission values (#{permissionId}),#{roleId}")
    void addRole_Permission(Map<String,String> map);
}
