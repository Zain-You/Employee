<template>
  <div>
    <!-- 头部开始  -->
    <div style="height: 60px;background-color: #3c7fff;display: flex;align-items: center">
        <div style="width: 200px; display: flex;align-items: center;font-size: 20px;color: white;padding-left: 15px;">
          <img style="width:40px;margin-right: 5px;" src="@/assets/logo.png" alt="" />
          <span style="font-size:24px;color:white">后台管理系统</span>
        </div>
      <div style="flex: 1"></div>
      <div style="width: fit-content; display: flex; align-items: center; padding-right: 10px">
      <img :src="data.user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" alt="" style="width:40px;height:40px;border-radius: 50%;">
      <span style="color:white;margin-left:5px">{{data.user.name}}</span>
    </div>

    </div>
    <!-- 头部结束 -->

    <!-- 下部开始-->
    <div style="display: flex">

      <!--  左边导航开始--->
      <div style="width: 200px;border-right: 1px solid #ddd; min-height:calc(100vh - 60px)">
        <el-menu router :default-active="router.currentRoute.value.path"  :default-openeds="['1']" style="border: 0">
          <el-menu-item index="/manager/home">
            <el-icon>
              <House/>
            </el-icon>
            系统首页
          </el-menu-item>

          <el-menu-item index="/manager/data">
            <el-icon>
              <DataAnalysis/>
            </el-icon>
            数据统计
          </el-menu-item>

          <el-menu-item index="/manager/article">
            <el-icon>
              <Document />
            </el-icon>
            文章管理
          </el-menu-item>

          <el-sub-menu index="1">
            <template #title>
              <el-icon>
                <User/>
              </el-icon>
              <span>用户管理</span>
            </template>
            <el-menu-item index="/manager/admin">管理员信息</el-menu-item>
            <el-menu-item index="/manager/employee">员工信息</el-menu-item>
          </el-sub-menu>

          <el-menu-item index="/manager/person">
            <el-icon><UserFilled /></el-icon>
            个人信息
          </el-menu-item>

          <el-menu-item index="/manager/password">
            <el-icon><lock /></el-icon>
            修改密码
          </el-menu-item>

          <el-menu-item @click="logout">
            <el-icon><SwitchButton /></el-icon>
            退出登录
          </el-menu-item>

        </el-menu>

      </div>
      <!--  左边导航结束--->

      <!-- 右边主题区域开始--->
      <div style="flex: 1;width: 0;background-color:#f5f7ff">
        <!-- updateUser是子页面定义的事件，在此页面来接收，子页面使用emit来定义 的，可以去查看person -->
        <router-view @updateUser="updateUser">

        </router-view>
      </div>
      <!-- 右边主题区域结束--->

    </div>
    <!-- 下部结束 -->
  </div>


</template>
<script setup lang="ts">
import router from "@/router/index";

import {reactive} from "vue";

const data = reactive({
  user:JSON.parse(localStorage.getItem('xm-pro-user'))

})

const logout = () =>{
  localStorage.removeItem('xm-pro-user') // 清楚本地缓存用户数据
  location.href = "/login"
}
const updateUser = () =>{
    data.user = JSON.parse(localStorage.getItem('xm-pro-user'));
}

</script>

<style scoped>
.el-sub-menu .is-active {
  background-color: #e6ecf7;
}
.el-sub-menu__title {
  background-color: white !important;
}
</style>