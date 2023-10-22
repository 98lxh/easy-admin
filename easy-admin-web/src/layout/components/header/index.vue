<script setup lang="ts">
import { ArrowDown, Expand, Fold } from '@element-plus/icons-vue'
import { ElDropdownItem, ElDropdownMenu } from 'element-plus';

defineProps<{ isCollapse: boolean }>();
const emit = defineEmits(["change-collapse"])

</script>

<template>
  <ElHeader class="header">
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
          <span>Tom</span>
          <ElIcon style="margin-right: 8px; margin-top: 1px">
            <ArrowDown />
          </ElIcon>
        </div>

        <template #dropdown>
          <ElDropdownMenu>
            <ElDropdownItem>View</ElDropdownItem>
            <ElDropdownItem>Add</ElDropdownItem>
            <ElDropdownItem>Delete</ElDropdownItem>
          </ElDropdownMenu>
        </template>
      </ElDropdown>
    </div>
  </ElHeader>
</template>

<style lang="scss" scoped>
.header{
  width:100%;
  display: flex;
  align-items: center;
  height: 60px;
  border-bottom: 1px solid #ccc;
  justify-content: space-between;

  &__left{
    :deep(.el-icon){
      font-size: 18px;
      cursor: pointer;
    }
  }

  &__right {
    .userinfo{
      display: flex;
      align-items: center;
      user-select: none;
      cursor: pointer;
      font-size: 16px;

      span{
        margin-right:5px;
      }
    }
  }
}
</style>
