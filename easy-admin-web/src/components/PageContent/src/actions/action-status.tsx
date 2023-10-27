import { PropType, defineComponent } from "vue";
import { actionProps } from "../types";
import { Close, Select } from "@element-plus/icons-vue";

const ActionStatus = defineComponent({
  name: "ActionDelete",
  props: {
    ...actionProps,
    row: {
      type: Object as PropType<any>,
      required: true
    },
    prop: {
      type: String
    }
  },
  emits: ["reload"],
  setup(props, { emit }) {
    async function handleStatus() {
      const { config, row } = props;
      const { status: rawStatus, id } = row;
      const status = Number(rawStatus) === 1 ? "0" : "1";
      const fields = (props.prop ? { [props.prop]: row[props.prop] } : { id })
      // eslint-disable-next-line no-unsafe-optional-chaining
      const response= await config?.api({ ...fields, status });
      if (response!.code !== 0) return
      emit("reload")
    }

    return () => (
      <el-popconfirm
        title={Number(props.row.status) === 1 ? "改为禁用？" : "改为启用？"}
        onConfirm={handleStatus}
        v-slots={{
          reference: () => (
            <el-button
              link
              type="primary"
              class="reset-margin"
              icon={ Number(props.row.status) === 1  ? <Close />  : <Select /> }
            />
          )
        }}
      ></el-popconfirm>
    );
  }
});

export default ActionStatus;
