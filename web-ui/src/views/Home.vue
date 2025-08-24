<template>
<div>
  这是首页
</div>
  <div>
    {{a}} {{data.b}}

    <el-button :icon="Delete" type="primary">Primary</el-button>
  </div>
  <div>
    <router-link to="/manager/test"> 跳转到test页面</router-link>
  </div>
</template>

<script setup lang="ts">
import {reactive,ref} from "vue";
import {Delete} from "@element-plus/icons-vue"

const a =ref(1)
const b =ref("ceshi")

const data = reactive(({
  a:12323,
  b:"测试一下嘛"

}))
</script>

<style scoped>

</style>