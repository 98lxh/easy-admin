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
    const operators = createOperation(props.contentConfig.apis);

    const {
      loading,
      dataList,
      onSearch,
      getPageData,
      onBeforeAction,
      paginationConfig
    } = useContentRequest(props);

    function renderUpdateOperator(row: any){
      if(!operators.update) return null;
      const { contentConfig } = props;
      const { apis } = contentConfig;
      return (
        <Operation.Update onUpdate={() => emit("updateBtnClick", row)}
            {...createActionProps("update", apis)}
         />
      )
    }

    function renderStatusOperator(row: any){
      if(!operators.status) return null
      const { contentConfig } = props;
      const { apis } = contentConfig;
      return (
        <Operation.Status row={row} prop={contentConfig.statusProp}
           onReload={() => onSearch()}
           {...createActionProps("status", apis)}
         />
      )
    }

    function renderDeleteOperator(row: any){
      if(!operators.delete) return null
      const { contentConfig } = props;
      const { apis } = contentConfig;
      return (
        <Operation.Delete  row={row} onReload={() => onSearch()}
            {...createActionProps("delete", apis)}
        />
      )
    }

    function renderCreateOperator(){
      if(!operators.create) return;
      const { contentConfig } = props;
      const { apis } = contentConfig;
      return (
        <Operation.Create onCreate={() => emit("createBtnClick")}
          {...createActionProps("create",apis)} 
        />
      )
    }



    // 渲染需要的操作按钮
    function renderOperation(row: any) {
      return (
        <>
          <Operation.Preview onPreview={() => emit("previewBtnClick", row)} />
          {renderUpdateOperator(row)}
          {renderStatusOperator(row)}
          {renderDeleteOperator(row)}
        </>
      );
    }

    function renderHeader(){
      const { contentConfig} = props;
      const { title } = contentConfig;
      return (
        <div class="page-content__header">
          <div>
            {title && <h3>{title}</h3>}
          </div>
          {renderCreateOperator()}
        </div>
      )      
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
          {renderHeader()}
          {renderTable()}
          {renderPagination()}
        </div>
      )
    };
  }
});

export default PageContent;
