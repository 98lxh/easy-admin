<script setup lang="ts">
import { computed } from "vue";
import Logo from "./logo.vue"
import { Menu as IconMenu } from '@element-plus/icons-vue'
import { useRoute } from "vue-router";

const route = useRoute();

const state = computed(() => {
  const { matched , path } = route;
  return {
    opends:matched.map(m => m.path),
    active: path
  }
})

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
      <ElMenu
        :collapse="isCollapse"
        :default-openeds="state.opends"
        :default-active="state.active"
        active-text-color="#409EFF"
        background-color="#001428"
        text-color="#fff"
        router
      >
        <ElMenuItem index="/welcome/dashboard">
          <ElIcon>
            <IconMenu />
          </ElIcon>
          <template #title>
            首页
          </template>
        </ElMenuItem>

        <ElSubMenu index="/system">
          <template #title>
            <ElIcon>
              <IconMenu />
            </ElIcon>
            <span>系统设置</span>
          </template>
          <ElMenuItem index="/system/user">用户管理</ElMenuItem>
          <ElMenuItem index="/system/role">角色管理</ElMenuItem>
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
  transition: all 0.3s ease-in-out;
  position: fixed;
  z-index: 1000;


  :deep(.el-menu) {
    border-right: none;
  }

  &.is-collapse {
    width: 63px;
  }
}
</style>
