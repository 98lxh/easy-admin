<script lang="ts" setup>
import { shallowRef } from 'vue'
import User from "@/pages/system/user/index.vue"
import Scrollbar from "./components/scollbar/index.vue"
import Header from "./components/header/index.vue"

const isCollapse = shallowRef(false);
const handleCollapse = (collapse: boolean) => isCollapse.value = collapse;
</script>

<template>
  <ElContainer class="layout">
    <Scrollbar :is-collapse="isCollapse" />

    <ElContainer class="layout__wrapper">
      <Header :is-collapse="isCollapse" @change-collapse="handleCollapse" />
      <ElMain>
        <ElScrollbar>
          <User />
        </ElScrollbar>
      </ElMain>
    </ElContainer>
  </ElContainer>
</template>

<style lang="scss" scoped>
.layout {
  height: 100%;
  display: flex;

  &__wrapper {
    flex: 1;
    display: flex;
    flex-direction: column;

    :deep(.el-main) {
      height: calc(100vh - 120px);
      padding: 0 !important;
    }
  }
}
</style>
