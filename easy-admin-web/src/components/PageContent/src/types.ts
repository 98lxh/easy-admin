import { PropType, ExtractPropTypes } from "vue";
import PageContent from "./page-content";

/***
 * @param {title} 标题
 * @param {apis} 请求
 * @param {columns} 列数据
 * @param {notPagination} 不需要分页器
 * @param {isTree} 是否为tree
 */
export interface ContentConfig {
  title: string;
  apis: ContentApis;
  columns: any;
  notPagination?: boolean;
  statusProp?: string;
  isTree?: boolean;
}

interface ContentApiResponse {
  code: number;
  message: string;
  data: any;
}

type API = (data: any) => Promise<ContentApiResponse>;

export type ActionType = "update" | "delete" | "create" | "preview" | "status";

export interface ContentApis {
  httpAdd?: API;
  httpList: API;
  httpDelete?: API;
  httpUpdate?: API;
  httpStatus?: API;
}

export type PageContentInstance = typeof PageContent & {
  getPageData: (params?: any) => void;
  onBeforeAction: (
    type: ActionType,
    data?: any,
    responseCallback?: () => void
  ) => void;
};

export const pageContentProps = {
  contentConfig: {
    type: Object as PropType<ContentConfig>,
    required: true
  },
  pageSize: {
    type: Number,
    default: 15
  }
} as const;

export const actionProps = {
  config: {
    type: Object as PropType<{ api: API }>
  }
};

export type PageContentProps = ExtractPropTypes<typeof pageContentProps>;
