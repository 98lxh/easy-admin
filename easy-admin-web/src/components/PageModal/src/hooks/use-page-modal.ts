import { Ref, ref } from "vue";
import { PageModalInstance } from "../types";
import { ActionType,  PageContentInstance } from "@/components/PageContent/src/types";

type CallbackType = () => any;

type Config = {
  createCallback?: CallbackType;
  updateCallback?: CallbackType;
  previewCallback?: CallbackType;
  confirmCallback?: (data: any) => any;
  pageContentRef: Ref<PageContentInstance | null>;
};

export function usePageModal({
  createCallback,
  updateCallback,
  confirmCallback,
  previewCallback,
  pageContentRef
}: Config) {
  const pageModalRef = ref<PageModalInstance | null>(null);
  const defaultInfo = ref<Record<string, string>>({});

  function handleCreateData() {
    defaultInfo.value = {};
    pageModalRef.value!.type = "create";
    createCallback && createCallback();
    pageModalRef.value!.visible = true;
  }

  function handleUpdateData(item: any) {
    console.log(item);

    defaultInfo.value = { ...item };
    pageModalRef.value!.type = "update";
    updateCallback && updateCallback();
    pageModalRef.value!.visible = true;
  }

  function handlePreviewData(item: any) {
    defaultInfo.value = { ...item };
    pageModalRef.value!.type = "preview";
    previewCallback && previewCallback();
    pageModalRef.value!.visible = true;
  }

  function handleConfrim(type: ActionType, data: any) {
    const result = confirmCallback ? confirmCallback(data) : {};
    pageModalRef.value!.loading = true;

    const params = {
      ...(type === "update" ? { id: defaultInfo.value.id } : {}),
      ...(result ? result : {}),
      ...data
    };

    pageContentRef.value!.onBeforeAction(type, params, () => {
      pageModalRef.value!.loading = false;
      pageModalRef.value!.visible = false;
    });
  }

  return {
    handleCreateData,
    handleUpdateData,
    handlePreviewData,
    handleConfrim,
    defaultInfo,
    pageModalRef
  };
}
