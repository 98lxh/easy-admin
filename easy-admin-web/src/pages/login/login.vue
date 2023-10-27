<script setup lang="ts">
import { computed, ref, shallowRef } from "vue"
import { User, Key } from "@element-plus/icons-vue"
import { register } from "@/service/modules/system/user"
import { ElMessage } from "element-plus"
import { useRouter } from "vue-router"
import { rules } from "./config/rules"
import { ElForm } from "element-plus"
import logo from "@/assets/logo.png"

import { useUserStore } from "@/store/modules/user"

import { PageType, initialUserData } from "./config/constants"

const router = useRouter();
const store = useUserStore();

const loading = shallowRef(false);
const userData = ref({ ...initialUserData });
const pageType = shallowRef(PageType.login);
const loginFormRef = ref<InstanceType<typeof ElForm> | null>(null);

const isLogin = computed(() => pageType.value === PageType.login);


async function execRequestCallback(callback: () => Promise<void>) {
  loading.value = true;
  try {
    await loginFormRef.value?.validate();
    callback();
  } catch (err) {
    console.log(err)
  } finally {
    loading.value = false;
  }
}

function checkout() {
  pageType.value = isLogin.value ? PageType.register : PageType.login;
  userData.value = { ...initialUserData }
}

async function onLogin() {
  execRequestCallback(async () => {
    const { username, password } = userData.value;
    const response = await store.login({ username, password })
    if (response === null) return
    ElMessage.success(`登录成功, 欢迎用户 ${username}!`)
    router.push("/welcome/dashboard");
  })
}

async function onRegister() {
  execRequestCallback(async () => {
    const response = await register(userData.value);
    if (response.code !== 200) return;
    ElMessage.success("注册成功, 请登录!")
    checkout();
  })
}

</script>

<template>
  <div class="login">
    <ElForm ref="loginFormRef" :rules="rules" :model="userData" label-width="0px" class="login-container">
      <div class="login__title">
        <img :src="logo" />
        <h3>Easy Admin</h3>
      </div>

      <ElFormItem prop="username">
        <ElInput v-model="userData.username" :prefix-icon="User" type="text" placeholder="请输入用户名" />
      </ElFormItem>

      <ElFormItem prop="password">
        <ElInput v-model="userData.password" :prefix-icon="Key" type="password" placeholder="请输入密码" />
      </ElFormItem>

      <template v-if="pageType === PageType.register">
        <ElFormItem prop="nickname">
          <ElInput v-model="userData.nickname" :prefix-icon="Key" type="password" placeholder="请输入昵称" />
        </ElFormItem>
      </template>

      <div class="login__checkout">
        <ElButton text type="primary" style="width:auto" @click="checkout">
          去{{ pageType === PageType.login ? "注册" : "登录" }}
        </ElButton>
      </div>

      <ElButton type="primary" @click="() => isLogin ? onLogin() : onRegister()" :loading="loading">
        {{ pageType === PageType.login ? "登录" : "注册" }}
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
@/service/modules/system/user
