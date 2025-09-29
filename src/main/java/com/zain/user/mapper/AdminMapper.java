package com.zain.user.mapper;

import com.zain.user.entity.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper
{
    List<Admin> selectAll(Admin Admin);

    @Select("select * from Admin where id= #{id}")
    Admin selectById(Integer id);


    void add(Admin Admin);

    void updateById(Admin Admin);

    @Delete("delete from `Admin` where id = #{id}")
    void deleteById(Integer id);

    @Select("select * from Admin where username= #{username}")
    Admin selectByUsername(String username);
}
