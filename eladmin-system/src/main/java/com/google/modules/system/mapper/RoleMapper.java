package com.google.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.modules.system.domain.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author iris
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT r.* FROM sys_role r, sys_users_roles u WHERE " +
            "r.role_id = u.role_id AND u.user_id = #{id}")
    Set<Role> findByUserId(@Param("id") Long id);
}
