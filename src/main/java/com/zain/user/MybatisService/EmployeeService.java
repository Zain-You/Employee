package com.zain.user.MybatisService;

import com.zain.user.entity.Employee;
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

    public PageInfo<Employee> getPage(Employee employee,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = employeeMapper.selectAll(employee);
        return PageInfo.of(list);
    }

    public void add(@RequestBody Employee employee)
    {
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
}
