package com.example.mytest.service;

import com.example.mytest.pojo.User;
import com.example.mytest.pojo.dto.UserDto;
import com.example.mytest.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // spring 的bean
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(UserDto user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user, userPojo);

        return userRepository.save(userPojo);
    }

    @Override
    public Object getUser(Integer userId) {

        return userRepository.findById(userId).orElseThrow(() ->
        {
            throw new IllegalArgumentException("用户不存在，参数异常");
        });
    }

    @Override
    public User eddit(UserDto user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user, userPojo);

        return userRepository.save(userPojo);
    }

    @Override
    public void delete(Integer userId) {
        userRepository.deleteById(userId);
    }

}
