import { FormItemRule } from "element-plus";
import { PropType } from "vue";

export type FormItemType =
  | "input"
  | "select"
  | "radio"
  | "checkbox"
  | "img_preview"
  | "img_upload"
  | "date_picker";

export type FormItem = {
  field: string;
  label?: string;
  placeholder?: any;
  rules?: Array<FormItemRule> | FormItemRule;
  type?: FormItemType;
  options?: Array<any>;
  isHidden?: boolean;
  otherOptions?: any;
  labelWidth?: string;
};

export type FormGorup = {
  title: string;
  formItems: Array<FormItem>;
};

export const basicFormProps = {
  formItems: {
    type: Array as PropType<Array<FormItem>>,
    default: () => []
  },
  formGroup: {
    type: Array as PropType<Array<FormGorup>>,
    default: () => []
  },
  itemStyle: {
    type: Object,
    default: () => ({
      paddingRight: "20px"
    })
  },
  disabled: {
    type: Boolean,
    default: false
  },
  colLayout: {
    type: Object,
    default: () => ({
      xl: 6,
      lg: 8,
      md: 12,
      sm: 24,
      xs: 24
    })
  },
  formData: {
    type: Object,
    required: true
  }
} as const;
