<script setup lang="ts">
import { shallowReactive, ref, shallowRef } from "vue";
import UserModal from "./components/user-modal.vue";
import { requestUsers } from "@/service/modules/user";

const tableData = ref([]);
const loading = shallowRef(false);
const pagination = shallowReactive({
  pageNum: 1,
  pageSize: 5,
  pageTotal: 0
});

const initialParams = {
  email: "",
  address: "",
  username: "",
}

const params = ref({ ...initialParams })

async function onSearch() {
  const { pageSize, pageNum } = pagination;
  loading.value = true

  const { data } = await requestUsers({
    pageNum,
    pageSize,
    ...params.value
  })

  loading.value = false
  const { list, total } = data;
  pagination.pageTotal = total;
  tableData.value = list;
}

async function onReset() {
  pagination.pageNum = 1;
  params.value = { ...initialParams };
  await onSearch();
}


onSearch();
</script>

<template>
  <div class="user">
    <ElRow class="user__search" :gutter="10">
      <ElCol :span="4">
        <ElInput placeholder="请输入用户名" v-model="params.username" />
      </ElCol>

      <ElCol :span="4">
        <ElInput placeholder="请输入邮箱" v-model="params.email" />
      </ElCol>

      <ElCol :span="4">
        <ElInput placeholder="请输入地址" v-model="params.address" />
      </ElCol>

      <ElButton type="primary" :loading="loading" @click="onSearch">搜索</ElButton>
      <ElButton type="warning" :loading="loading" @click="onReset">重置</ElButton>
    </ElRow>

    <div class="user__handler">
      <ElButton type="primary">新增</ElButton>
      <ElButton type="primary">导入</ElButton>
      <ElButton type="primary">导出</ElButton>
    </div>

    <ElTable class="user__table" v-loading="loading" :data="tableData" size="small" border>
      <ElTableColumn prop="id" label="ID"></ElTableColumn>
      <ElTableColumn prop="username" label="用户名"></ElTableColumn>
      <ElTableColumn prop="email" label="邮箱"></ElTableColumn>
      <ElTableColumn prop="phone" label="手机号"></ElTableColumn>
      <ElTableColumn prop="address" label="地址"></ElTableColumn>
      <ElTableColumn label="操作">
        <ElButton text type="primary" size="small">编辑</ElButton>
        <ElButton text type="danger" size="small">删除</ElButton>
      </ElTableColumn>
    </ElTable>

    <div class="user__pagination">
      <ElPagination background :page-sizes="[2, 10, 15, 20]" layout="total,sizes, prev, pager, next"
        v-model:current-page="pagination.pageNum" v-model:page-size="pagination.pageSize" :total="pagination.pageTotal"
        @current-change="onSearch" @size-change="onSearch" />
    </div>

    <user-modal />
  </div>
</template>

<style lang="scss" scoped>
.user {
  padding: 10px;

  &__search {
    margin-bottom: 10px;
  }

  &__handler {
    margin-bottom: 10px;
  }

  &__table {
    margin-bottom: 10px;
  }

  &__pagination {
    display: flex;
    justify-content: flex-end;
  }
}
</style>
@/service/modules/user
