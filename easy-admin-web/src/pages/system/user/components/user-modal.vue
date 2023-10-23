<script setup lang="ts">
import {computed ,ref,shallowReactive} from "vue"
import { initialFormData } from "./../config/initialData";
import { updateUser } from "@/service/modules/user";
import { Operator } from "@/constants/operator";
import { ElForm, ElMessage } from "element-plus";
import { rules } from "./../config/rules";

const emit = defineEmits(["refresh"]);

const state = shallowReactive({
  operator: Operator.update,
  visible: false,
  loading: false
})

const formData = ref({...initialFormData})
const formRef = ref<InstanceType<typeof ElForm> | null>(null)

const title = computed(() => {
  return  state.operator === Operator.create
      ? "创建用户"
      : "编辑用户"
})

async function onSubmit(){
  state.loading = true;
  try{
    await formRef.value?.validate();
    const { code } = await updateUser(formData.value);
    if(code !== 200) return
    ElMessage.success("更新成功!")
    state.visible = false
    emit("refresh")
  }catch(err){
    console.log(err)
  } finally {
    state.loading = false;
  }
}

defineExpose({
  onShow(operator:Operator, _data:IUser){
    formData.value = {..._data};
    state.operator = operator;
    state.operator === Operator.create && delete formData.value.id;
    state.visible = true;
  }
})

</script>

<template>
  <ElDialog v-model="state.visible" :title="title" center>
    <ElForm label-width="80px" :model="formData" :rules="rules" ref="formRef">
      <ElFormItem label="用户名" prop="username">
        <ElInput v-model="formData.username" placeholder="请输入用户名" />
      </ElFormItem>

      <ElFormItem label="昵称" prop="nickname">
        <ElInput v-model="formData.nickname"  placeholder="请输入昵称" />
      </ElFormItem>

      <ElFormItem label="邮箱" prop="email">
        <ElInput v-model="formData.email" placeholder="请输入邮箱" />
      </ElFormItem>

      <ElFormItem label="手机号" prop="phone">
        <ElInput v-model="formData.phone" placeholder="请输入手机号" />
      </ElFormItem>

      <ElFormItem label="地址" prop="address">
        <ElInput v-model="formData.address" placeholder="请输入地址" />
      </ElFormItem>

      <div style="display: flex;justify-content: flex-end;">
        <ElButton type="primary" @click="onSubmit" :loading="state.loading">保存</ElButton>
      </div>
    </ElForm>
  </ElDialog>
</template>

