import { ActionType } from "../../PageContent";
import { FormGorup, FormItem } from "../../BasicForm";
import pageModal from "./page-modal.vue";
import { PropType } from "vue";

export type PageModalInstance = typeof pageModal & {
  visible: boolean;
  loading: boolean;
  type: ActionType;
};

export interface ModalConfig {
  title: string;
  formItems?: Array<FormItem>;
  formGroup?: Array<FormGorup>;
  colLayout?: any;
  itemStyle?: Record<string, string>;
}

export const modalProps = {
  modalConfig: {
    type: Object as PropType<ModalConfig>,
    required: true
  },
  prviewDescription: {
    type: Boolean,
    default: false
  },
  defaultInfo: {
    type: Object,
    required: true
  }
};

export const descritonProps = {
  config: {
    type: Object as PropType<ModalConfig>,
    required: true
  },
  defaultInfo: {
    type: Object,
    required: true
  }
};
