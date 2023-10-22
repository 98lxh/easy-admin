<script setup lang="ts">
import {computed, shallowRef} from "vue"
import {Operator} from "@/constants/operator";

const visible = shallowRef<boolean>(true);
const operator = shallowRef<Operator>(Operator.create);

const title = computed(() =>
    operator.value === Operator.create
      ? "创建用户"
      : "编辑用户"
)


</script>

<template>
  <ElDialog v-model="visible" :title="title" center>

  </ElDialog>
</template>
