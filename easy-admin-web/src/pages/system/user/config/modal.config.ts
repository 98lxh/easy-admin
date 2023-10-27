import { ModalConfig } from "@/components/PageModal";

const modalConfig: ModalConfig = {
  title: "用户",
  colLayout: {span:24},
  formItems: [
    {
      field: "username",
      type: "input",
      label: "用户名",
      labelWidth: "120px"
    },
    {
      type: "input",
      field: "nickname",
      labelWidth: "120px",
      label: "昵称"
    },
    {
      type: "input",
      field: "email",
      labelWidth: "120px",
      label: "邮箱"
    },
    {
      type: "input",
      field: "phone",
      labelWidth: "120px",
      label: "手机号"
    },
    {
      field: "address",
      labelWidth: "120px",
      type: "input",
      label: "地址"
    }
  ]
};

export default modalConfig;
