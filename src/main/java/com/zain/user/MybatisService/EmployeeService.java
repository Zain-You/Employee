package com.zain.user.MybatisService;

import cn.hutool.core.util.StrUtil;
import com.zain.user.entity.Account;
import com.zain.user.entity.Employee;
import com.zain.user.exception.GlobalExceptionHandlerAdvice;
import com.zain.user.mapper.EmployeeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// 标注为springboot里面的一个bean
@Service
public class EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    public List<Employee> getAll() {

        List<Employee> list = employeeMapper.selectAll(new Employee());
        return list;
    }

    public Employee getById(Integer id) {
        return employeeMapper.selectById(id);
    }

    public PageInfo<Employee> getPage(Employee employee, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = employeeMapper.selectAll(employee);
        return PageInfo.of(list);
    }

    public void add(@RequestBody Employee employee) {
        employeeMapper.add(employee);
    }

    public void update(Employee employee) {
        employeeMapper.updateById(employee);

    }

    public void delete(Integer id) {
        employeeMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.delete(id);
        }
    }

    public Employee login(Account account) {
        String username = account.getUsername();

        Employee dbEmployee = employeeMapper.selectByUsername(username);

        if (dbEmployee == null) {
            throw new IllegalArgumentException("账号不存在");
        }
        String password = account.getPassword();

        if(!dbEmployee.getPassword().equals(password)){
            throw new IllegalArgumentException("账号或密码错误");
        }

        return dbEmployee;
    }

    public void register(Employee employee) {
        String username = employee.getUsername();
        Employee dbEmployee = employeeMapper.selectByUsername(username);;
        if(dbEmployee!=null)
        {
            // 账号已经存在
            throw new IllegalArgumentException("账号已经存在");
        }

        if(StrUtil.isBlank(employee.getPassword())){//密码没有填
              employee.setPassword("123");
        }

        employee.setRole("EMP");

        add(employee);
    }
}
