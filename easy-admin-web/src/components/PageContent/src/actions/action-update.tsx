import { defineComponent } from "vue";
import { actionProps } from "../types";
import { Edit } from "@element-plus/icons-vue";

const ActionUpdate = defineComponent({
  name: "ActionUpdate",
  props: actionProps,
  emits: ["update"],
  setup(_, { emit }) {
    return () => (
      <el-button
        class="reset-margin"
        onClick={() => emit("update")}
        link
        type="primary"
        icon={<Edit />}
      />
    );
  }
});

export default ActionUpdate;
