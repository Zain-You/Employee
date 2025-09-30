package com.zain.user.MybatisService;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zain.user.entity.Account;
import com.zain.user.entity.Admin;
import com.zain.user.entity.Employee;
import com.zain.user.mapper.AdminMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// 标注为springboot里面的一个bean
@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    public List<Admin> getAll() {

        List<Admin> list = adminMapper.selectAll(new Admin());
        return list;
    }

    public Admin getById(Integer id) {
        return adminMapper.selectById(id);
    }

    public PageInfo<Admin> getPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }

    public void add(@RequestBody Admin admin) {

        String username = admin.getUsername();
        Admin dbAdmin = adminMapper.selectByUsername(username);;
        if(dbAdmin!=null)
        {
            // 账号已经存在
            throw new IllegalArgumentException("账号已经存在");
        }

        if(StrUtil.isBlank(admin.getPassword())){//密码没有填
            admin.setPassword("admin");
        }

        admin.setPassword("123456");
        admin.setRole("ADMIN");

        adminMapper.add(admin);
    }

    public void update(Admin admin) {
        adminMapper.updateById(admin);

    }

    public void delete(Integer id) {
        adminMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.delete(id);
        }
    }

    public Admin login(Account account) {
        String username = account.getUsername();

        Admin dbEmployee = adminMapper.selectByUsername(username);

        if (dbEmployee == null) {
            throw new IllegalArgumentException("账号不存在");
        }
        String password = account.getPassword();

        if(!dbEmployee.getPassword().equals(password)){
            throw new IllegalArgumentException("账号或密码错误");
        }

        return dbEmployee;
    }

    public void updatePassword(Account account) {
        Integer id = account.getId();
        Admin admin = this.getById(id);
        if(!admin.getPassword().equals(account.getPassword()))
        {
            throw new IllegalArgumentException("密码错误");
        }

        admin.setPassword(account.getNewPassword());
        this.update(admin);
    }
}
