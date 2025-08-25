package com.zain.user.exception;

import com.zain.user.pojo.dto.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 统一处理
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    // 接收所有异常类型
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseMessage HandleException(Exception e, HttpServletRequest request, HttpServletResponse response)
    {

        e.printStackTrace();
        // 日志
        Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);
        logger.error("统一异常");

        return new ResponseMessage(500,e.getMessage(),null);
    }


}
