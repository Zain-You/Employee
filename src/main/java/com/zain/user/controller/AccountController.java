package com.zain.user.controller;

import com.zain.user.MybatisService.AdminService;
import com.zain.user.MybatisService.EmployeeService;
import com.zain.user.entity.Account;
import com.zain.user.entity.Employee;
import com.zain.user.pojo.dto.ResponseMessage;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController // 接口方法返回对象 装换成json文本
@RequestMapping("/account") // 接口路由 /user
public class AccountController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseMessage login(@RequestBody Account account)
    {
        Account result = null;
        if("ADMIN".equals(account.getRole()))
        {
            //管理员登录
            result =  adminService.login(account);
        }
        else
        {
             // 员工登录
            result = employeeService.login(account);
        }

        return ResponseMessage.success(result);
    }

    @PostMapping("/register")
    public ResponseMessage register(@RequestBody Employee employee)
    {
        employeeService.register(employee);
        return ResponseMessage.success(null);
    }

    @PutMapping("/updatePassword")
    public ResponseMessage updatePassword(@RequestBody Account account)
    {
        if("ADMIN".equals(account.getRole()))
        {
            //管理员登录
            adminService.updatePassword(account);
        }
        else
        {
            // 员工登录
            employeeService.updatePassword(account);
        }

        return ResponseMessage.success(null);
    }
}
