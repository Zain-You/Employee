package com.example.mytest.service;


import com.example.mytest.pojo.User;
import com.example.mytest.pojo.dto.UserDto;

public interface IUserService {
    User add(UserDto user);
}
