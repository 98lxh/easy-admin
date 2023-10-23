<script setup lang="ts">
import { shallowReactive, ref, shallowRef } from "vue"
import { User, Key } from "@element-plus/icons-vue"
import { login } from "@/service/modules/user"
import { PageMode } from "./config/enums"
import { ElMessage } from "element-plus"
import { useRouter } from "vue-router"
import { rules } from "./config/rules"
import { ElForm } from "element-plus"
import logo from "@/assets/logo.png"


const loading = shallowRef(false);
const pageMode = shallowRef(PageMode.login);
const loginFormRef = ref<InstanceType<typeof ElForm> | null>(null);



const loginForm = shallowReactive({
  username: "",
  password: "",
})

const router = useRouter();

async function onLogin() {
  loading.value = true;
  try {
    await loginFormRef.value?.validate();
    const { code } = await login(loginForm);
    if (code !== 200) return;
    ElMessage.success(`${pageMode.value === PageMode.login ? '登录' : '注册'}成功!`)
    router.push("/welcome/dashboard");
  } catch (err) {
    console.log(err)
  } finally {
    loading.value = false;
  }
}

const onCheckoutPageMode = () => pageMode.value = pageMode.value === PageMode.login ? PageMode.register : PageMode.login;

</script>

<template>
  <div class="login">
    <ElForm ref="loginFormRef" :rules="rules" :model="loginForm" label-width="0px" class="login-container">
      <div class="login__title">
        <img :src="logo" />
        <h3>Easy Admin</h3>
      </div>

      <ElFormItem prop="username">
        <ElInput v-model="loginForm.username" :prefix-icon="User" type="text" placeholder="请输入用户名" />
      </ElFormItem>

      <ElFormItem prop="password">
        <ElInput v-model="loginForm.password" :prefix-icon="Key" type="password" placeholder="请输入密码" />
      </ElFormItem>

      <ElFormItem prop=""></ElFormItem>

      <div class="login__checkout">
        <ElButton text type="primary" style="width:auto" @click="onCheckoutPageMode">
          去{{ pageMode === PageMode.login ? "注册" : "登录" }}
        </ElButton>
      </div>

      <ElButton type="primary" @click="onLogin" :loading="loading">
        {{ pageMode === PageMode.login ? "登录" : "注册" }}
      </ElButton>
    </ElForm>
  </div>
</template>

<style lang="scss" scoped>
.login {
  &-container {
    border-radius: 15px;
    box-sizing: border-box;
    margin: 180px auto;
    width: 350px;
    padding: 15px 35px;
    background-color: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;

    button {
      width: 100%;
      margin-top: 20px;
    }
  }

  &__title {
    display: flex;
    color: #087af5;
    align-items: center;
    justify-content: center;
    flex-direction: column;

    h3 {
      margin: 20px 0px;
      font-size: 22px;
      font-weight: 700;
    }

    img {
      width: 40px;
      height: 30px;
      margin-right: 5px;
      margin-bottom: -10px;
    }
  }

  &__checkout {
    display: flex;
    justify-content: flex-end;

    button {
      margin-top: -15px;
    }
  }
}
</style>
