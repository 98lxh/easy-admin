<script setup lang="ts">
import {computed} from "vue";
import logo from "@/assets/logo.png"

const props = defineProps<{ isCollapse: boolean }>()

const classNames = computed(() => ({
  logo: true,
  "is-collapse": props.isCollapse
}))

</script>

<template>
  <div :class="classNames">
    <img :src="logo" alt="" />
    <h3 v-if="!isCollapse">EasyAdmin</h3>
  </div>
</template>


<style lang="scss" scoped>
.logo {
  color: #ffffff;
  display: flex;
  justify-content: center;
  font-size: 16px;
  margin: 20px 0;
  align-items: center;

  &.is-collapse {
    img {
      margin-right: 0;
    }
  }

  h3{
    white-space: nowrap;
    font-weight: 700;
  }

  img{
    height: 18px;
    width: 22px;
    margin-right: 10px;
  }
}
</style>
