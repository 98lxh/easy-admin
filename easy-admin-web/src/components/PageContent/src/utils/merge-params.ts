type Params = {
  pagination: any;
  basicParams: any;
};

/**
 * 合并查询参数
 */
export function mergeParams({ pagination, basicParams }: Params) {
  if (!pagination) return basicParams;

  const { currentPage: pageNum, pageSize } = pagination;

  return {
    pageNum,
    pageSize,
    ...basicParams
  };
}
