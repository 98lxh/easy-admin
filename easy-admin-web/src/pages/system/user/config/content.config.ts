import { ContentConfig } from "@/components/PageContent";
import { getUsers, updateUser, deleteUserById } from "@/service/modules/system/user";

const columns = [
  {
    label: "ID",
    prop: "id"
  },
  {
    label: "用户名",
    prop: "username"
  },
  {
    label: "昵称",
    prop: "nickname"
  },
  {
    label: "邮箱",
    prop: "email"
  },
  {
    label: "手机号",
    prop: "phone"
  },
  {
    label: "地址",
    prop: "address"
  },
  {
    label: "创建时间",
    prop: "createTime"
  },
  {
    label: "操作",
    type: "operation",
    fixed: "right",
    width: 110
  }
];

const contentConfig: ContentConfig = {
  title: "用户列表",
  columns,
  apis: {
    httpList:getUsers,
    httpUpdate: updateUser,
    httpDelete: deleteUserById
  }
};

export default contentConfig;
