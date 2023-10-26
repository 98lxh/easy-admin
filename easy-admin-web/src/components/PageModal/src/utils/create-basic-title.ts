import { ActionType } from "@/components/PageContent";

const mapActionToBasicTitle = {
  update: "编辑",
  create: "新增",
  preview: "详情"
};

export function createBasicTitle(type: ActionType) {
  return mapActionToBasicTitle[type];
}
