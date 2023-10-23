<script setup lang="ts">
import { computed } from "vue"
import { ArrowDown, Expand, Fold } from '@element-plus/icons-vue'
import { ElDropdownItem, ElDropdownMenu } from 'element-plus';
import { useRouter } from "vue-router"

const props = defineProps<{ isCollapse: boolean }>();
const emit = defineEmits(["change-collapse"])

const router = useRouter()
const logout = () => router.push("/login")

const classNames = computed(() => ({
  "is-collapse": props.isCollapse,
  "header": true
}))


</script>

<template>
  <ElHeader :class="classNames">
    <div class="header__left">
      <ElIcon v-if="isCollapse" @click="emit('change-collapse', false)">
        <Expand />
      </ElIcon>

      <ElIcon v-else @click="emit('change-collapse', true)">
        <Fold />
      </ElIcon>
    </div>

    <div class="header__right">
      <ElDropdown>
        <div class="userinfo">
          <el-avatar :size="25" />
          <span>用户Test</span>
          <ElIcon style="margin-right: 8px; margin-top: 1px">
            <ArrowDown />
          </ElIcon>
        </div>

        <template #dropdown>
          <ElDropdownMenu>
            <ElDropdownItem>个人信息</ElDropdownItem>
            <ElDropdownItem @click="logout">退出登录</ElDropdownItem>
          </ElDropdownMenu>
        </template>
      </ElDropdown>
    </div>
  </ElHeader>
</template>

<style lang="scss" scoped>
.header {
  display: flex;
  align-items: center;
  border-bottom: 1px solid #ccc;
  justify-content: space-between;
  width: calc(100% - 200px);
  background: #fff;
  position: fixed;
  height: 45px;
  z-index: 9;
  right: 0;

  &.is-collapse {
    width: calc(100% - 60px) !important;
  }

  &__left {
    :deep(.el-icon) {
      font-size: 18px;
      cursor: pointer;
    }
  }

  &__right {
    .userinfo {
      display: flex;
      align-items: center;
      user-select: none;
      cursor: pointer;
      font-size: 16px;

      span {
        margin-right: 5px;
      }
    }
  }
}
</style>
