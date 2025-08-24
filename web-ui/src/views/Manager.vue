<template>
  <div>
    <!-- 头部开始  -->
    <div style="height: 60px;background-color: #3c7fff;display: flex;align-items: center">
        <div style="width: 200px; display: flex;align-items: center;font-size: 20px;color: white;padding-left: 15px;">
          <img style="width:40px" src="@/assets/logo.png" alt="" />
          <span style="font-size:24px;color:white">后台管理系统</span>
        </div>
      <div style="flex: 1"></div>
      <div style="width: fit-content; display: flex; align-items: center; padding-right: 10px">
      <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" alt="" style="width:40px;height:40px">
      <span style="color:white;margin-left:5px">老表</span>
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
          <el-sub-menu index="1">
            <template #title>
              <el-icon>
                <User/>
              </el-icon>
              <span>用户管理</span>
            </template>
            <el-menu-item>管理员信息</el-menu-item>
            <el-menu-item>普通用户信息</el-menu-item>
          </el-sub-menu>

          <el-menu-item index="/manager/home">
            <el-icon>
              <User />
            </el-icon>
            个人信息
          </el-menu-item>

          <el-menu-item index="/manager/home">
            <el-icon>
              <House/>
            </el-icon>
            退出登录
          </el-menu-item>

        </el-menu>

      </div>
      <!--  左边导航结束--->

      <!-- 右边主题区域开始--->
      <div style="flex: 1;width: 0;background-color:#f5f7ff">
        <router-view>

        </router-view>
      </div>
      <!-- 右边主题区域结束--->

    </div>
    <!-- 下部结束 -->
  </div>


</template>
<script setup lang="ts">
import router from "@/router/index";
</script>

<style scoped>
.el-menu .is-active {
  background-color: #e6ecf7 !important;
}
</style>