package com.xieguanzhi.dao;

import com.xieguanzhi.domain.Permission;
import com.xieguanzhi.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface PermissionDao {

    @Select("select p.* from role_permission rp,permission p where rp.permissionid=p.id and rp.roleid=#{id}")
    List<Permission> findPermissionByRoleID(String id);

    @Select("select * from permission")
    @Results({
            @Result(
                    id = true,
                    column = "ID",
                    property = "id"
            ),
            @Result(
                    column = "ID",
                    property = "roles",
                    many = @Many(
                            select = "com.xieguanzhi.dao.PermissionDao.findRoleByPermissionId",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    List<Permission> findAll();

    @Select("select r.* from role_permission rp,role r where rp.roleid=r.id and rp.permissionid=#{id}")
    List<Role>findRoleByPermissionId(String id);
}
