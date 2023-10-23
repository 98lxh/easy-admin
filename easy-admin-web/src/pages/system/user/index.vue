<script setup lang="ts">
import { initialParams, initialPagination } from "./config/initialData";
import { getUsers, deleteUserById } from "@/service/modules/user";
import { shallowReactive, ref, shallowRef } from "vue";
import UserModal from "./components/user-modal.vue";
import { ElMessageBox } from "element-plus"
import { Operator } from "@/constants/operator";

const tableData = ref<IUser[]>([]);
const loading = shallowRef(false);

const userModalRef = ref<InstanceType<typeof UserModal> | null>(null);
const pagination = shallowReactive({ ...initialPagination });
const params = ref({ ...initialParams })

async function search() {
  const { pageSize, pageNum } = initialPagination;
  try {
    loading.value = true
    const { data } = await getUsers({ pageNum, pageSize, ...params.value })
    const { list, total } = data;
    pagination.pageTotal = total;
    tableData.value = list;
  } catch (err) {
    console.log(err)
  } finally {
    loading.value = false
  }
}
function deleteUser(user: IUser) {
  ElMessageBox.confirm(`是否确认删除用户${user.nickname}?`, "请确认", {
    type: 'warning',
    confirmButtonText: '确定',
    cancelButtonText: "取消"
  }).then(async () => {
    const { code } = await deleteUserById(user.id!)
    code === 200 && search();
  })
}

async function reset() {
  pagination.pageNum = 1;
  params.value = { ...initialParams };
  await search();
}

search();
</script>

<template>
  <div class="user">
    <ElRow class="user__search" :gutter="10">
      <ElCol :span="4">
        <ElInput placeholder="请输入用户名" v-model="params.username" size="small" />
      </ElCol>

      <ElCol :span="4">
        <ElInput placeholder="请输入邮箱" v-model="params.email" size="small" />
      </ElCol>

      <ElCol :span="4">
        <ElInput placeholder="请输入地址" v-model="params.address" size="small" />
      </ElCol>

      <ElButton type="primary" :loading="loading" @click="search" size="small">搜索</ElButton>
      <ElButton type="warning" :loading="loading" @click="reset" size="small">重置</ElButton>
    </ElRow>

    <div class="user__handler">
      <ElButton type="primary" size=small>新增</ElButton>
      <ElButton type="primary" size="small">导入</ElButton>
      <ElButton type="primary" size="small">导出</ElButton>
    </div>

    <ElTable class="user__table" v-loading="loading" :data="tableData" size="small" border>
      <ElTableColumn width="60px" prop="id" label="ID"></ElTableColumn>
      <ElTableColumn min-width="90px" prop="username" label="用户名" show-overflow-tooltip></ElTableColumn>
      <ElTableColumn min-width="90px" prop="nickname" label="昵称" show-overflow-tooltip></ElTableColumn>
      <ElTableColumn prop="email" label="邮箱" show-overflow-tooltip></ElTableColumn>
      <ElTableColumn prop="phone" label="手机号" show-overflow-tooltip></ElTableColumn>
      <ElTableColumn prop="address" label="地址" show-overflow-tooltip></ElTableColumn>
      <ElTableColumn label="操作" width="140px" fixed="right">
        <template #="scope">
          <ElButton text type="primary" size="small" @click="userModalRef?.onShow(Operator.update, scope.row)">编辑
          </ElButton>
          <ElButton text type="danger" size="small" @click="deleteUser(scope.row)">删除</ElButton>
        </template>
      </ElTableColumn>
    </ElTable>

    <div class="user__pagination">
      <ElPagination background :page-sizes="[2, 10, 15, 20]" layout="total,sizes, prev, pager, next"
        v-model:current-page="pagination.pageNum" v-model:page-size="pagination.pageSize" :total="pagination.pageTotal"
        @current-change="search" @size-change="search" />
    </div>

    <user-modal ref="userModalRef" @refresh="search" />
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
