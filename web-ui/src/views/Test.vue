<template>
  <div>
    这是测试页面
    <el-button type="primary" @click="router.back()" >返回</el-button>
  </div>

</template>
<script setup lang="ts">
import router from "@/router/index"
</script>