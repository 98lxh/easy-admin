<script lang="ts" setup>
import { RouterView } from "vue-router";
import { shallowRef, computed } from 'vue'
import Scrollbar from "./components/scrollbar/index.vue"
import Header from "./components/header/index.vue"

const isCollapse = shallowRef(false);
const handleCollapse = (collapse: boolean) => isCollapse.value = collapse;

const warpperClassNames = computed(() => ({
  "is-collapse": isCollapse.value,
  "layout__wrapper": true
}))
</script>

<template>
  <div class="layout">
    <Scrollbar :is-collapse="isCollapse" />
    <div :class="warpperClassNames">
      <Header :is-collapse="isCollapse" @change-collapse="handleCollapse" />
      <div class="layout__wrapper-main">
        <RouterView />
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.layout {
  display: flex;
  height: 100%;
  width: 100%;

  &__wrapper {
    flex: 1;
    display: flex;
    flex-direction: column;
    margin-left: 200px;
    width: calc(100% - 200px);
    overflow: auto;
    transition: all 0.3s ease-in-out;

    &.is-collapse {
      margin-left: 64px !important;
      width: calc(100% - 64px);
    }

    &-main {
      min-width: 1200px;
      height: calc(100vh - 120px);
      padding-top: 45px;
    }
  }
}
</style>
