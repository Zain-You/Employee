<template>
  <div class="login-container">
    <el-card class="login-card">
      <div class="login-header">
        <el-icon class="login-icon" size="50">
          <svg viewBox="0 0 24 24">
            <path d="M12 2L2 7v10c0 5.55 3.84 10.74 9 12 5.16-1.26 9-6.45 9-12V7l-10-5z"/>
          </svg>
        </el-icon>
        <h2 class="login-title">欢迎回来</h2>
        <p class="login-subtitle">请登录您的账户</p>
      </div>

      <!--也可以写成 @submit.prevent="handleLogin" -->
      <el-form ref="formRef" :model="data.form" :rules="data.rules">
        <el-form-item prop="username">
          <el-input
              v-model="data.form.username"
              placeholder="请输入用户名"
              prefix-icon="User"
              size="large"
          />
        </el-form-item>

        <el-form-item prop="password">
          <el-input
              v-model="data.form.password"
              type="password"
              placeholder="请输入密码"
              prefix-icon="Lock"
              size="large"
              show-password
          />
        </el-form-item>

        <el-form-item prop="role">
          <el-select v-model="data.form.role" style="width:100%" size="large">
             <el-option value="ADMIN" label="管理员"></el-option>
             <el-option value="EMP" label="员工"></el-option>
          </el-select>
        </el-form-item>

        <div class="form-options">
          <el-checkbox v-model="data.form.remember">记住我</el-checkbox>
          <el-link type="primary" :underline="false">忘记密码？</el-link>
        </div>

        <el-button
            type="primary"
            size="large"
            @click="handleLogin"
            style="width: 100%"
        >
          登录
        </el-button>
      </el-form>

      <div class="login-footer">
        <span class="footer-text">还没有账户？</span>
        <el-link type="primary" href="/register" :underline="false">立即注册</el-link>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import {reactive, ref} from 'vue'
import {useRouter} from 'vue-router'
import {ElMessage} from 'element-plus'
import request from "@/utils/request.js"

const router = useRouter()
const formRef = ref()

const data = reactive({
  form: {role:'ADMIN'},
  rules: {
    username: [{required: true, message: '请输入账号', trigger: 'blur'}],
    password: [{required: true, message: '请输入密码', trigger: 'blur'}]
  }
})

const handleLogin = async () => {

  formRef.value.validate((valid) => {
    if (valid) {

      request.post('/account/login', data.form).then(res => {
        if (res.code === 200) {//登录成功

          // 存储用户信息
          localStorage.setItem('xm-pro-user',JSON.stringify(res.data))
          ElMessage.success('登录成功')
          setTimeout(()=>{
            location.href = '/manager/home';

          },500)

        } else {
          ElMessage.error(res.message)
        }
      })

    }
  })

}
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
}

.login-card {
  width: 380px;
  padding: 20px;
  border-radius: 16px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
}

.login-header {
  text-align: center;
  margin-bottom: 30px;
}

.login-icon {
  color: #667eea;
  margin-bottom: 15px;
}

.login-title {
  font-size: 24px;
  font-weight: 600;
  color: #2d3748;
  margin-bottom: 5px;
}

.login-subtitle {
  font-size: 14px;
  color: #718096;
  margin: 0;
}

.form-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.login-footer {
  text-align: center;
  margin-top: 20px;
  font-size: 14px;
  color: #718096;
}

@media (max-width: 480px) {
  .login-card {
    width: 90%;
    max-width: 350px;
  }

  .login-title {
    font-size: 22px;
  }
}
</style>