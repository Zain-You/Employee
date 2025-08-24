<template>
  <div>
     404 没有找到页面
    <el-button @click="goHome">返回首页</el-button>
  </div>
</template>
<script setup lang="ts">
import router from "@/router/index.js";

const  goHome=()=>{
  location.href = '/manager/home'

}
</script>