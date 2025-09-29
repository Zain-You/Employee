package com.zain.user.controller;

import com.zain.user.MybatisService.AdminService;
import com.zain.user.entity.Admin;
import com.zain.user.entity.Admin;
import com.zain.user.pojo.dto.ResponseMessage;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @GetMapping("/getAll")
    public ResponseMessage<List<Admin>> getAll()
    {
        List<Admin> list = adminService.getAll();

        return ResponseMessage.success(list);
    }

    @GetMapping("/getById/{id}")
    public ResponseMessage<Admin> getById(@PathVariable Integer id)
    {
        Admin Admin = adminService.getById(id);
        return ResponseMessage.success(Admin);
    }

    @GetMapping("/getOne")
    public ResponseMessage<Admin> getOne(@RequestParam Integer id, @RequestParam(required = false) String other)
    {
        Admin Admin = adminService.getById(id);
        return ResponseMessage.success(Admin);
    }

    // 使用getList？id=1&name=str 的方式请求
    @GetMapping("/getList")
    public ResponseMessage<Admin> getList(Admin Admin)
    {
        return  ResponseMessage.success(Admin);

    }

    // 分页查询
    @GetMapping("/getPage")
    public ResponseMessage getPage(Admin Admin,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize ) {
        com.github.pagehelper.PageInfo<Admin> pageInfo = adminService.getPage(Admin,pageNum, pageSize);
        return ResponseMessage.success(pageInfo);
    }

    @PostMapping("/add")
    public ResponseMessage add(@RequestBody Admin Admin)
    {
        adminService.add(Admin);
        return ResponseMessage.success(null);
    }

    @PutMapping("/update")
    public ResponseMessage update(@RequestBody Admin Admin)
    {
        adminService.update(Admin);
        return ResponseMessage.success(null);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseMessage update(@PathVariable Integer id)
    {
        adminService.delete(id);
        return ResponseMessage.success(null);
    }

    @DeleteMapping("/deleteBatch")
    public ResponseMessage deleteBatch(@RequestBody List<Integer> ids) {
        adminService.deleteBatch(ids);
        return ResponseMessage.success(null);
    }

}
