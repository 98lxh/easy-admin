<script setup lang="ts">
import { basicFormProps } from "./types";
import FormItems from "./form-items.vue";
import { useVModel } from "@vueuse/core";

const props = defineProps(basicFormProps);

const formData = useVModel(props, "formData");
</script>

<template>
  <template v-for="(item, index) in formGroup!" :key="index">
    <el-col :span="24">
      <h1 class="font-500 text-lg py-2">{{ item.title }}</h1>
    </el-col>
    <FormItems
      :form-items="item.formItems!"
      :form-data="formData"
      :col-layout="colLayout"
    />
  </template>
</template>
