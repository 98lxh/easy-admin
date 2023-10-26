import { onMounted, ref } from "vue";
import { ActionType, PageContentProps } from "../types";
import { mapActionToApi } from "../utils/create-operation";
import { mergeParams } from "../utils/merge-params";
import { handleTree } from "@pureadmin/utils";
import { usePagination } from "./use-pagination";
import { useParams } from "./use-params";

// 请求方法
export function useContentRequest(props: PageContentProps) {
  const dataList = ref([]);
  const loading = ref(false);
  const { getParams, setParams } = useParams();
  const paginationConfig = usePagination(props, { onSearch });
  const { setPagination, getPagination } = paginationConfig;


  // 请求列表的方法
  async function onSearch() {
    const { contentConfig } = props;
    const { apis, isTree } = contentConfig;

    loading.value = true;

    const params = mergeParams({  
      pagination: getPagination ? getPagination() : {}, 
      basicParams: getParams() 
    });

    const { data } = await apis.httpList(params);
    const list = Array.isArray(data) ? data : data.list;
    dataList.value = isTree ? handleTree(list ?? []) : list;

    setPagination?.("total", data.total);
    loading.value = false;
  }

  // 暴露给外部请求列表的方法
  function getPageData(parmas: any = {}) {
    setPagination?.("currentPage", 1);
    setParams && setParams(parmas);
    onSearch();
  }

   // 外部action执行传入数据发起请求
  async function onBeforeAction( action: ActionType,  data : any = {}, responseCallback?: () => void ) {
    const { contentConfig } = props;
    const { apis } = contentConfig;
    const key = mapActionToApi[action];
    loading.value = true;
    await apis[key](data);
    responseCallback && responseCallback();
    onSearch();
    loading.value = false;
  }

  //初始化数据
  onMounted(() => onSearch());

  return {
    loading,
    dataList,
    onSearch,
    getPageData,
    onBeforeAction,
    paginationConfig,
  };
}
