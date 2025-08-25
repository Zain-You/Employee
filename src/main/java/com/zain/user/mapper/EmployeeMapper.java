package com.zain.user.mapper;

import com.zain.user.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper
{
    List<Employee> selectAll(Employee employee);

    @Select("select * from employee where id= #{id}")
    Employee selectById(Integer id);


    void add(Employee employee);

    void updateById(Employee employee);

    @Delete("delete from `employee` where id = #{id}")
    void deleteById(Integer id);
}
