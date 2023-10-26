import { FormConfig } from "@/components/PageSearch";

const searchFormConfig: FormConfig = {
  formItems: [
    {
      type: "input",
      field: "username",
      placeholder: "请输入用户名"
    },
    {
      type: "input",
      field: "email",
      placeholder: "请输入邮箱"
    },
    {
      type: "input",
      field: "address",
      placeholder: "地址"
    }
  ]
};

export default searchFormConfig;
