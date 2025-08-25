package com.zain.user.controller;

import com.zain.user.MybatisService.EmployeeService;
import com.zain.user.entity.Employee;
import com.zain.user.pojo.dto.ResponseMessage;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @GetMapping("/getAll")
    public ResponseMessage<List<Employee>> getAll()
    {
       List<Employee> list = employeeService.getAll();

       return ResponseMessage.success(list);
    }

    @GetMapping("/getById/{id}")
    public ResponseMessage<Employee> getById(@PathVariable Integer id)
    {
        Employee employee = employeeService.getById(id);
        return ResponseMessage.success(employee);
    }

    @GetMapping("/getOne")
    public ResponseMessage<Employee> getOne(@RequestParam Integer id,@RequestParam(required = false) String other)
    {
        Employee employee = employeeService.getById(id);
        return ResponseMessage.success(employee);
    }

    // 使用getList？id=1&name=str 的方式请求
    @GetMapping("/getList")
    public ResponseMessage<Employee> getList(Employee employee)
    {
        return  ResponseMessage.success(employee);

    }

    // 分页查询
    @GetMapping("/getPage")
    public ResponseMessage getPage(Employee employee,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize ) {
            var pageInfo = employeeService.getPage(employee,pageNum, pageSize);
            return ResponseMessage.success(pageInfo);
    }

    @PostMapping("add")
    public ResponseMessage add(@RequestBody Employee employee)
    {
       employeeService.add(employee);
       return ResponseMessage.success(null);
    }

    @PutMapping("/update")
    public ResponseMessage update(@RequestBody Employee employee)
    {
        employeeService.update(employee);
        return ResponseMessage.success(null);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseMessage update(@PathVariable Integer id)
    {
        employeeService.delete(id);
        return ResponseMessage.success(null);
    }

}
