package com.google.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.exception.EntityNotFoundException;
import com.google.modules.system.domain.User;
import com.google.modules.system.mapper.UserMapper;
import com.google.modules.system.service.UserService;
import com.google.modules.system.service.dto.UserDTO;
import com.google.modules.system.service.mapstruct.UserConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author iris
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserConvert userConvert;

    @Override
    public UserDTO findByName(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, userName);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return userConvert.toDto(user);
        }
    }
}
