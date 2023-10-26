<script setup lang="ts">
import { modalProps } from "./types";
import { computed, ref, unref, watch , shallowRef} from "vue";
import { ActionType } from "@/components/PageContent";
import { BasicForm, FormItem } from "@/components/BasicForm";
import { createBasicTitle } from "./utils/create-basic-title";

const props = defineProps(modalProps);
const emit = defineEmits(["confirmBtnClick"]);

const formData = ref({});
const visible = shallowRef(false);
const loading = shallowRef(false);
const type = ref<ActionType>("preview");
const formRef = ref<InstanceType<typeof BasicForm> | null>(null);

//状态是否处于预览详情
const isPreview = computed(() => type.value === "preview");

const title = computed(() => {
  const basicTitle = createBasicTitle(type.value);
  return type.value === "preview" ? props.modalConfig.title + basicTitle : basicTitle + props.modalConfig.title;
});

function changeDefaultInfo(formItems: Array<FormItem>, newVal: any) {
  for (const item of formItems) {
    const isCheckBox = item.type === "checkbox";
    const isArray = isCheckBox && !Array.isArray(newVal[item.field])
    formData.value[item.field] = isArray ? [] : newVal[item.field];
  }
}

watch(
  () => props.defaultInfo,
  newVal => {
    const { formGroup, formItems } = props.modalConfig;
    if (!formGroup) return changeDefaultInfo(formItems, newVal);
    formGroup.forEach(item => changeDefaultInfo(item.formItems, newVal));
  }
);

function handleConfirmClick() {
  formRef.value!.vaildate((isValid: boolean) => {
    //表单校验通过派发事件
    isValid && emit("confirmBtnClick", unref(type), unref(formData));
  });
}

defineExpose({
  visible,
  loading,
  type
});
</script>

<template>
  <el-dialog :title="title" v-model="visible" center destroy-on-close>
    <!-- 表单 -->
    <BasicForm ref="formRef" size="small" v-bind="modalConfig" v-model:form-data="formData" :disabled="isPreview" />
    <!-- 保存/重置 -->
    <div class="page-modal__handler" v-if="!isPreview">
      <el-button type="primary" size="small" :loading="loading" @click="handleConfirmClick">保存</el-button>
      <el-button @click="visible = false" size="small">关闭</el-button>
    </div>
  </el-dialog>
</template>


<style lang="scss" scoped>
.page-modal__handler {
  display: flex;
  justify-content: flex-end;
}
</style>
