package com.zain.user.service;


import com.zain.user.pojo.User;
import com.zain.user.pojo.dto.UserDto;

public interface IUserService {
    User add(UserDto user);

    Object getUser(Integer userId);

    /**
     * 修改用户
     */
    User eddit(UserDto user);

    void delete(Integer userId);
}
