<script setup lang="ts">
import Logo from "./logo.vue"
import { computed } from "vue";
import { Menu as IconMenu } from '@element-plus/icons-vue'

const props = defineProps<{
  isCollapse: boolean
}>();

const classNames = computed(() => ({
  sidebar: true,
  "is-collapse": props.isCollapse
}))
</script>

<template>
  <div :class="classNames">
    <Logo :is-collapse="isCollapse" />
    <ElScrollbar>
      <ElMenu :collapse="isCollapse" active-text-color="#409EFF" background-color="#001428" default-active="1-2" text-color="#fff">
        <ElSubMenu index="1">
          <template #title>
            <ElIcon>
              <IconMenu />
            </ElIcon>
            <span>导航一</span>
          </template>
          <ElMenuItem index="1-1">子项一</ElMenuItem>
          <ElMenuItem index="1-2">子项一</ElMenuItem>
          <ElMenuItem index="1-3">子项一</ElMenuItem>
        </ElSubMenu>
      </ElMenu>
    </ElScrollbar>
  </div>
</template>

<style lang="scss" scoped>
.sidebar {
  height: 100vh;
  width: 200px;
  background-color: #001428;
  transition: all 0.3s;

  :deep(.el-menu){
    border-right: none;
  }

  &.is-collapse {
    width: 63px;
  }
}
</style>
