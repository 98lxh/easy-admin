import { defineComponent } from "vue";
import { pageContentProps } from "./types";
import { createActionProps, createOperation } from "./utils/create-operation";
import { ElTableColumn, ElTable, ElPagination } from "element-plus";
import { useContentRequest } from "./hooks/use-request";
import { Operation } from "./actions";

import "./style/index.scss"

const PageContent = defineComponent({
  name: "PageContent",
  props: pageContentProps,
  emits: [
    "createBtnClick",
    "updateBtnClick",
    "previewBtnClick",
    "statusBtnClick"
  ],
  setup(props, { expose, emit }) {
    //根据传入的api决定表格需要的操作
    const action = createOperation(props.contentConfig.apis);

    const {
      dataList,
      loading,
      onSearch,
      getPageData,
      onBeforeAction,
      paginationConfig
    } = useContentRequest(props);

    // 渲染需要的操作按钮
    function renderOperation(row: any) {
      const { contentConfig } = props;
      const { apis } = contentConfig;

      return (
        <>
          <Operation.Preview onPreview={() => emit("previewBtnClick", row)} />
          {action.update && (
            <Operation.Update
              onUpdate={() => emit("updateBtnClick", row)}
              {...createActionProps("update", apis)}
            />
          )}
          {action.status && (
            <Operation.Status
              row={row}
              prop={contentConfig.statusProp}
              onReload={() => onSearch()}
              {...createActionProps("status", apis)}
            />
          )}
          {action.delete && (
            <Operation.Delete
              row={row}
              onReload={() => onSearch()}
              {...createActionProps("delete", apis)}
            />
          )}
        </>
      );
    }

    function renderTable() {
      const { contentConfig } = props;
      const { columns } = contentConfig;
      return (
        <ElTable data={dataList.value} border size="small">
          {
            columns.map((column: any) => {
              return <ElTableColumn {...column} v-slots={{
                default: (scope: any) => {
                  if (column.type === "operation") return renderOperation(scope.row);
                  if (column.render) return column.render(scope);
                  return scope.row[column.prop];
                }
              }} />
            })
          }
        </ElTable>
      )
    }

    function renderPagination() {
      const pagination = paginationConfig.getPagination?.();
      return (
        <div class="page-content__pagination">
          <ElPagination
            onCurrentChange={paginationConfig.onCurrentChange}
            onSize-change={paginationConfig.onSizeChange}
            layout="total, prev, pager, next, jumper"
            currentPage={pagination!.currentPage}
            pageSize={pagination!.pageSize}
            pageSizes={[10, 20, 50, 100]}
            total={pagination!.total}
            background
          />
        </div>
      )
    }

    expose({ getPageData, onBeforeAction });

    return () => {
      return (
        <div class="page-content" v-loading={loading.value}>
          {renderTable()}
          {renderPagination()}
        </div>
      )
    };
  }
});

export default PageContent;
