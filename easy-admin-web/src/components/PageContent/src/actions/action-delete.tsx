import { PropType, defineComponent } from "vue";
import { Delete } from "@element-plus/icons-vue";
import { actionProps } from "../types";

const ActionDelete = defineComponent({
  name: "ActionDelete",
  props: {
    ...actionProps,
    row: {
      type: Object as PropType<any>,
      required: true
    }
  },
  emits: ["reload"],
  setup(props, { emit }) {
    async function handleDelete() {
      const { config, row } = props;
      const { code } = await config?.api({ id: row.id });
      if(code !== 0) return;
      emit("reload")
    }

    return () => (
      <el-popconfirm
        title="是否确认删除"
        placement="top"
        onConfirm={handleDelete}
        v-slots={{
          reference: () => (
            <el-button
              link
              class="reset-margin"
              type="primary"
              icon={<Delete />}
            />
          )
        }}
      />
    );
  }
});

export default ActionDelete;
