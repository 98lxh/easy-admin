import { ActionType } from "../../PageContent";
import { FormGroup, FormItem } from "../../BasicForm";
import pageModal from "./page-modal.vue";
import { PropType } from "vue";

export type PageModalInstance = typeof pageModal & {
  visible: boolean;
  loading: boolean;
  type: ActionType;
};

export interface ModalConfig {
  title: string;
  colLayout?: any;
  formItems?: Array<FormItem>;
  formGroup?: Array<FormGroup>;
  itemStyle?: Record<string, string>;
}

export const modalProps = {
  modalConfig: {
    type: Object as PropType<ModalConfig>,
    required: true
  },
  defaultInfo: {
    type: Object,
    required: true
  }
}
