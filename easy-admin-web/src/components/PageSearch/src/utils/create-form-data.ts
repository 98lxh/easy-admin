import { FormItem } from "@/components/BasicForm";

export function createFormData(formItems: Array<FormItem>) {
  const formData: Record<string, any> = {};

  for (const item of formItems) {
    const isCheckBox = item.type === "checkbox";
    const isDatepicker = item.type === "date_picker";
    formData[item.field] = isCheckBox || isDatepicker ? [] : "";
  }

  return formData;
}
