<script setup lang="ts">
import { shallowReactive, ref } from "vue"
import { User, Key } from "@element-plus/icons-vue"
import { ElForm } from "element-plus"
import logo from "@/assets/logo.png"
import { rules } from "./rules"

const loginFormRef = ref<InstanceType<typeof ElForm> | null>(null);

const loginForm = shallowReactive({
  username:"",
  password:"",
  code:""
})

async function onLogin() {
  try{
    await loginFormRef.value?.validate();
    console.log("提交验证...")
  }catch(err){
    console.log(err)
  }
}

</script>

<template>
  <div class="login">
    <ElForm ref="loginFormRef" :rules="rules" :model="loginForm" label-width="0px" class="login-container">
     <div class="login-title">
      <img :src="logo" />
      <h3> Easy Admin</h3>
     </div>

      <ElFormItem prop="username">
       <ElInput v-model="loginForm.username" :prefix-icon="User" type="text" placeholder="请输入用户名" />
     </ElFormItem>

     <ElFormItem prop="password">
       <ElInput v-model="loginForm.password" :prefix-icon="Key" type="password" placeholder="请输入密码" />
     </ElFormItem>

    <ElButton type="primary" @click="onLogin">登录</ElButton>
  </ElForm>
  </div>
</template>

<style lang="scss" scoped>
.login {
  &-container {
    border-radius: 15px;
    box-sizing: border-box;
    margin: 180px auto;
    width:350px;
    padding: 15px 35px;
    background-color: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;

    button{
      width:100%;
      margin-top:20px;
    }
  }

  &-title{
    display: flex;
    color:#087af5;
    align-items: center;
    justify-content: center;
    flex-direction: column;

    h3{
      margin:20px 0px;
      font-size:22px;
      font-weight: 700;
    }

    img{
      width:40px;
      height:30px;
      margin-right:5px;
      margin-bottom: -10px;
    }
  }
}
</style>
