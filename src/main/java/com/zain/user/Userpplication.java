package com.zain.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zain.user.mapper")
public class Userpplication {

    public static void main(String[] args) {
        SpringApplication.run(Userpplication.class, args);
    }

}
