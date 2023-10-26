import type { ContentApis, ActionType } from "./../types";

export const mapActionToApi = {
  update: "httpUpdate",
  create: "httpAdd",
  delete: "httpDelete",
  status: "httpStatus"
};

/**
 * 生成表格包含的操作
 */
export function createOperation(apis: ContentApis) {
  const keys = Object.keys(mapActionToApi);
  const actions: Record<string, boolean> = {};

  keys.forEach(key => {
    actions[key] = !!apis[mapActionToApi[key]];
  });

  return actions;
}

/**
 * 生成操作需要的参数
 */
export function createActionProps(type: ActionType, apis: ContentApis) {
  return {
    config: {
      api: apis[mapActionToApi[type]]
    }
  };
}
