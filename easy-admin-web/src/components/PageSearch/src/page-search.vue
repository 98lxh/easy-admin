<script setup lang="ts">
import { ref } from "vue";
import { BasicForm } from "@/components/BasicForm";
import { Search, Refresh } from "@element-plus/icons-vue";
import { createFormData } from "./utils/create-form-data";
import { searchFormProps } from "./types";

const props = defineProps(searchFormProps);
const emit = defineEmits(["searchBtnClick", "resetBtnClick"]);

//初始表格数据
const defaultFormData = createFormData(props.formConfig.formItems);

const formData = ref({ ...defaultFormData });

//重置搜索
function handleResetClick() {
  formData.value = { ...defaultFormData };
  emit("resetBtnClick");
}

//搜索
function handleSearchClick() {
  emit("searchBtnClick", formData.value);
}
</script>

<template>
  <div class="page-search">
    <BasicForm v-bind="formConfig" v-model:form-data="formData" size="small">
      <template #action>
        <div style="width: 100%">
          <ElButton
            type="primary"
            :icon="Search"
            @click="handleSearchClick"
          >
            搜索
         </ElButton>

          <ElButton
            :icon="Refresh" 
            @click="handleResetClick"
          >
            重置
          </ElButton>
        </div>
      </template>
    </BasicForm>
  </div>
</template>

<style lang="scss" scoped>
.page-search {
  padding-top: 20px;
  padding-left: 10px;
  background:#fff;
  margin-bottom: 10px;
}
</style>
