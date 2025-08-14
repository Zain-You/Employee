package com.example.mytest.controller;


import com.example.mytest.pojo.User;
import com.example.mytest.pojo.dto.ResponseMessage;
import com.example.mytest.pojo.dto.UserDto;
import com.example.mytest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 接口方法返回对象 装换成json文本
@RequestMapping("/user") // 接口路由 /user
public class UserController {

   @Autowired
   IUserService  userService;

    @PostMapping
    public ResponseMessage<User> add(@Validated @RequestBody UserDto user)
    {
        var newUser = userService.add(user);
        return ResponseMessage.success(newUser);
    }



}
