<script setup lang="ts">
import { useVModel } from "@vueuse/core";
import { basicFormProps } from "./types";

const props = defineProps(basicFormProps);
const formData = useVModel(props, "formData");
</script>

<template>
  <template v-for="(item, index) in formItems" :key="index">
    <el-col v-bind="colLayout" v-if="!item.isHidden">
      <el-form-item
        :label="item.label"
        :prop="item.field"
        :rules="item.rules"
        :label-width="item.labelWidth"
        :style="itemStyle"
      >
        <!-- 输入框 -->
        <template v-if="item.type === 'input'">
          <el-input
            v-model="formData[item.field]"
            :placeholder="item.placeholder"
          />
        </template>

        <!-- 选择框 -->
        <template v-if="item.type === 'select'">
          <el-select
            v-model="formData[item.field]"
            :placeholder="item.placeholder"
          >
            <el-option
              v-for="(opt, index) in item.options"
              :key="index"
              :label="opt.label"
              :value="opt.value"
            />
          </el-select>
        </template>

        <template v-if="item.type === 'radio'">
          <el-radio-group v-model="formData[item.field]">
            <el-radio
              v-for="(opt, index) in item.options"
              :key="index"
              :label="opt.value"
              >{{ opt.label }}</el-radio
            >
          </el-radio-group>
        </template>

        <template v-if="item.type === 'img_preview'">
          <el-image :src="formData[item.field]" />
        </template>

        <template v-if="item.type === 'img_upload'">
          <el-upload class="el-avatar" />
        </template>

        <template v-if="item.type === 'checkbox'">
          <el-checkbox-group v-model="formData[item.field]">
            <el-checkbox
              v-for="(opt, index) in item.options"
              :key="index"
              :label="opt.value"
              >{{ opt.label }}</el-checkbox
            >
          </el-checkbox-group>
        </template>

        <template v-if="item.type === 'date_picker'">
          <el-date-picker
            v-model="formData[item.field]"
            v-bind="item.otherOptions"
          />
        </template>
      </el-form-item>
    </el-col>
  </template>
</template>
