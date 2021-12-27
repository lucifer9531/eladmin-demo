package com.google.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.modules.system.domain.Menu;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author iris
 */
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("SELECT m.* FROM sys_menu m, sys_roles_menus rm WHERE " +
            "m.menu_id = rm.menu_id AND rm.role_id = #{id}")

    @Results({
            @Result(property = "id", column = "menu_id")
    })
    Set<Menu> findMenuByRoleId(@Param("id") Long id);
}
