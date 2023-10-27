import { ContentConfig } from "@/components/PageContent";
import { getRoles, updateRole, createRole, deleteRoleById} from "@/service/modules/system/role";

const columns = [
  {
    label: "ID",
    prop: "id"
  },
  {
    label: "角色名",
    prop: "name"
  },
  {
    label: "描述",
    prop: "description"
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
  title: "角色列表",
  columns,
  apis: {
    httpList: getRoles,
    httpUpdate: updateRole,
    httpAdd: createRole,
    httpDelete: deleteRoleById
  }
};

export default contentConfig;
