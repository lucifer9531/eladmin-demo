package com.google.modules.security.service;

import com.google.modules.security.config.bean.SecurityProperties;
import com.google.modules.security.service.dto.JwtUserDTO;
import com.google.modules.security.service.dto.OnlineUserDTO;
import com.google.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author iris
 */
@Service
@Slf4j
public class OnlineUserService {

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;

    public OnlineUserService(SecurityProperties properties, RedisUtils redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }

    /**
     * 保存在线用户信息
     * @param jwtUserDto /
     * @param token      /
     * @param request    /
     */
    public void save(JwtUserDTO jwtUserDto, String token, HttpServletRequest request) {
        String ip = StringUtils.getIp(request);
        String browser = StringUtils.getBrowser(request);
        String address = StringUtils.getCityInfo(ip);
        OnlineUserDTO onlineUserDto = null;
        try {
            onlineUserDto = new OnlineUserDTO(jwtUserDto.getUsername(), jwtUserDto.getUser().getNickName(), browser, ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUserDto, properties.getTokenValidityInSeconds() / 1000);
    }

    /**
     * 查询全部数据，不分页
     * @param filter /
     * @return /
     */
    public List<OnlineUserDTO> getAll(String filter) {
        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
        Collections.reverse(keys);
        List<OnlineUserDTO> onlineUserDtos = new ArrayList<>();
        for (String key : keys) {
            OnlineUserDTO onlineUserDto = (OnlineUserDTO) redisUtils.get(key);
            if (StringUtils.isNotBlank(filter)) {
                if (onlineUserDto.toString().contains(filter)) {
                    onlineUserDtos.add(onlineUserDto);
                }
            } else {
                onlineUserDtos.add(onlineUserDto);
            }
        }
        onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUserDtos;
    }

    /**
     * 查询用户
     * @param key /
     * @return /
     */
    public OnlineUserDTO getOne(String key) {
        return (OnlineUserDTO) redisUtils.get(key);
    }

    /**
     * 踢出用户
     * @param key /
     */
    public void kickOut(String key) {
        key = properties.getOnlineKey() + key;
        redisUtils.del(key);
    }

    /**
     * 退出登录
     * @param token /
     */
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     * @param userName 用户名
     */
    public void checkLoginOnUser(String userName, String igoreToken) {
        List<OnlineUserDTO> onlineUserDtos = getAll(userName);
        if (onlineUserDtos == null || onlineUserDtos.isEmpty()) {
            return;
        }
        for (OnlineUserDTO onlineUserDto : onlineUserDtos) {
            if (onlineUserDto.getUserName().equals(userName)) {
                try {
                    String token = EncryptUtils.desDecrypt(onlineUserDto.getKey());
                    if (StringUtils.isNotBlank(igoreToken) && !igoreToken.equals(token)) {
                        this.kickOut(token);
                    } else if (StringUtils.isBlank(igoreToken)) {
                        this.kickOut(token);
                    }
                } catch (Exception e) {
                    log.error("checkUser is error", e);
                }
            }
        }
    }

    /**
     * 根据用户名强退用户
     * @param username /
     */
    @Async
    public void kickOutForUsername(String username) throws Exception {
        List<OnlineUserDTO> onlineUsers = getAll(username);
        for (OnlineUserDTO onlineUser : onlineUsers) {
            if (onlineUser.getUserName().equals(username)) {
                String token = EncryptUtils.desDecrypt(onlineUser.getKey());
                kickOut(token);
            }
        }
    }
}
