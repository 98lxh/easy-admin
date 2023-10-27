<script setup lang="ts">
import { shallowRef, watch } from "vue";
import * as ElementPlusIcons from "@element-plus/icons-vue";

const emit = defineEmits(["update:visible"]);
const visible = shallowRef(false);

const toline = (value: string) => {
  return value.replace(/A-Z/g, '-$1').toLowerCase()
}

function handleClick() {
  visible.value = !visible.value;
}

function clickIcon(icon: string) {
  const text = `el-icon-${toline(icon)}`;

  console.log(text)
}
</script>

<template>
  <el-input style="width: 300px;" disabled placeholder="请选择图标">
    <template #append>
      <el-popover trigger="click" placement="left"  width="300px" :visible="visible">
        <template #reference>
          <el-button  @click="handleClick">
            <component :is="`el-icon-addlocation`" />
          </el-button>
        </template>

        <el-scrollbar>
          <div class="container">
            <div class="item" v-for="(icon, index) in Object.keys(ElementPlusIcons)" @click="clickIcon(icon)"
              :key="index">
                <component :is="`el-icon-${toline(icon)}`" />
            </div>
          </div>
        </el-scrollbar>
      </el-popover>
    </template>
  </el-input>
</template>

<style lang="scss" scoped>
.container {
  height: 300px;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  padding: 10px;
  gap: 10px;
  box-sizing: border-box;

  .item {
    border: 1px solid #ccc;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 15px;
    padding: 5px;
    box-sizing:border-box;
    cursor: pointer;

    &:hover{
      border: 1px solid #409eff;
      color: #409eff;
    }

    svg {
      width: 1em;
      height: 1em;
    }
  }
}

::v-deep(.el-dialog__body) {
  height: 60vh;
  overflow: auto;
}
</style>
