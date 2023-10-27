<script setup lang="ts">
import { ref } from "vue";
import { ElForm } from "element-plus";
import { useVModel } from "@vueuse/core";
import { basicFormProps } from "./types";
import FormItems from "./form-items.vue";
import GroupForm from "./group-form.vue";

const props = defineProps(basicFormProps);
const formRef = ref<InstanceType<typeof ElForm> | null>(null);
const formData = useVModel(props, "formData");

//校验方法
function vaildate(callback?: (vaild: boolean) => void) {
  formRef.value?.validate(vaild => {
    callback && callback(vaild);
  });
}

defineExpose({
  vaildate
});
</script>

<template>
  <el-form
    ref="formRef"
    v-model="formData"
    :model="formData"
    :disabled="disabled"
  >
    <el-row>
      <FormItems
        v-if="!formGroup.length"
        :form-items="formItems"
        :col-layout="colLayout"
        v-model:form-data="formData"
      />

      <GroupForm
        v-else
        :form-group="formGroup"
        v-model:form-data="formData"
        :col-layout="colLayout"
      />

      <el-form-item>
        <slot name="action" />
      </el-form-item>
    </el-row>
  </el-form>
</template>

<style lang="scss" scoped>
:deep(.el-select) {
  width: 100%;
}
</style>
