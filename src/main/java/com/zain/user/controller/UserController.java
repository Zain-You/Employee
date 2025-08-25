package com.zain.user.controller;


import com.zain.user.pojo.User;
import com.zain.user.pojo.dto.ResponseMessage;
import com.zain.user.pojo.dto.UserDto;
import com.zain.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{userId}")
    public ResponseMessage getUserById(@PathVariable("userId") Integer userId)
    {
        var user = userService.getUser(userId);
        return ResponseMessage.success(user);
    }

    @PutMapping
    public ResponseMessage edit(@Validated @RequestBody UserDto user)
    {
        var result = userService.eddit(user);
        return ResponseMessage.success(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseMessage delete(@PathVariable("userId") Integer userId)
    {
        userService.delete(userId);
        return ResponseMessage.success(null);
    }
}
