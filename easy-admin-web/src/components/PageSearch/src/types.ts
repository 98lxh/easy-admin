import { FormGorup, FormItem } from "/@/components/BasicForm";

export interface FormConfig {
  formItems: Array<FormItem>;
  formGorup?: Array<FormGorup>;
  colLayout?: any;
  itemStyle?: Record<string, string>;
}

export const basicFormProps = {
  formItems: {
    type: Array as PropType<Array<FormItem>>,
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

export const searchFormProps = {
  formConfig: {
    type: Object as PropType<FormConfig>,
    required: true
  }
} as const;
