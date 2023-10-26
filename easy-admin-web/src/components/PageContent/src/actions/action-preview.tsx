import { defineComponent } from "vue";
import { View } from "@element-plus/icons-vue";

const ActionPreview = defineComponent({
  name: "ActionCreate",
  emits: ["preview"],
  setup(_, { emit }) {
    return () => (
      <el-button
        class="reset-margin"
        link
        type="primary"
        onClick={() => emit("preview")}
        icon={<View />}
      />
    );
  }
});

export default ActionPreview;
