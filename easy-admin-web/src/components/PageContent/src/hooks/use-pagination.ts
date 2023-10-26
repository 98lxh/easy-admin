import { reactive } from "vue";
import { PageContentProps } from "../types";
import { PaginationProps } from "@pureadmin/table";

export function usePagination(props: PageContentProps, { onSearch }: any) {
  const { pageSize, contentConfig } = props;
  if (contentConfig.notPagination) return {};

  // 分页参数
  const pagination = reactive<PaginationProps>({
    total: 0,
    pageSize,
    currentPage: 1,
    background: true
  });

  // 设置当前页
  async function onCurrentChange(currentPage: number) {
    pagination.currentPage = currentPage;
    await onSearch();
  }

  // 设置分页器
  async function onSizeChange(pageSize: number) {
    pagination.pageSize = pageSize;
    pagination.currentPage = 1;
    await onSearch();
  }

  function getPagination() {
    return pagination;
  }

  function setPagination(key: string, newVal:any) {
    pagination[key] = newVal;
  }

  return {
    pagination,
    getPagination,
    setPagination,
    onSizeChange,
    onCurrentChange,
  };
}
