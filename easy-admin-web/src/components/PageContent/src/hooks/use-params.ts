import { ref } from "vue";

export function useParams() {
  //查询参数
  const params = ref<Record<string, any>>({});
  const setParams = (newParams: any) => params.value = newParams;
  const getParams = () => params.value


  return {
    setParams,
    getParams
  };
}
