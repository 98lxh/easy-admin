import { defineComponent } from "vue";
import { Plus } from "@element-plus/icons-vue";

const ActionCreate = defineComponent({
  name: "ActionCreate",
  emits: ["create"],
  setup(_, { emit }) {
    return () => (
      <el-button
        onClick={() => emit("create")}
        icon={<Plus />}
        type="primary"
        size="small"
      >
        新增
      </el-button>
    );
  }
});

export default ActionCreate;
