import { ModalConfig } from "@/components/PageModal";

const modalConfig: ModalConfig = {
  title: "角色",
  colLayout: {span:24},
  formItems: [
    {
      field: "name",
      type: "input",
      label: "角色名",
      labelWidth: "120px"
    },
    {
      type: "input",
      field: "description",
      labelWidth: "120px",
      label: "描述"
    }
  ]
};

export default modalConfig;
