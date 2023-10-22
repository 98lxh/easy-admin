<script lang="ts" setup>
import { ref ,shallowRef } from 'vue'
import Scrollbar from "./components/scollbar/index.vue"
import Header from "./components/header/index.vue"

const item = {
  date: '2016-05-02',
  name: 'Tom',
  address: 'No. 189, Grove St, Los Angeles',
}
const tableData = ref(Array.from({ length: 40 }).fill(item))

const isCollapse = shallowRef(false);

const handleCollapse = (collapse:boolean) => isCollapse.value = collapse;
</script>

<template>
  <ElContainer class="layout">
    <Scrollbar :is-collapse="isCollapse" />

    <ElContainer class="layout__wrapper">
      <Header :is-collapse="isCollapse" @change-collapse="handleCollapse" />
      <ElMain>
          <ElScrollbar>
            <el-table :data="tableData">
              <el-table-column prop="date" label="Date" width="140" />
              <el-table-column prop="name" label="Name" width="120" />
              <el-table-column prop="address" label="Address" />
            </el-table>
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

    :deep(.el-main){
      height: calc(100vh - 120px);
      padding: 0 !important;
    }
  }
}
</style>
