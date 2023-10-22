<script setup lang="ts">
import { RouterView } from "vue-router"
import locale from "element-plus/dist/locale/zh-cn.mjs"
</script>

<template>
   <!--   <router-view></router-view>-->
  <ElConfigProvider :locale="locale">
    <RouterView />
  </ElConfigProvider>
</template>
