package com.google.modules.system.service;

import com.google.modules.system.service.dto.UserDTO;

/**
 * @author iris
 */
public interface UserService {

    /**
     * 根据用户名查询
     * @param userName /
     * @return /
     */
    UserDTO findByName(String userName);
}
