<script setup lang="ts">
import { initialApp } from "@/store"
import { RouterView } from "vue-router"
import locale from "element-plus/dist/locale/zh-cn.mjs"


initialApp();
</script>

<template>
  <ElConfigProvider :locale="locale">
    <RouterView />
  </ElConfigProvider>
</template>
