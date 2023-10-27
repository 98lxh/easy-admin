<script setup lang="ts">
import { PageSearch, usePageSearch } from "@/components/PageSearch";
import { PageModal, usePageModal } from "@/components/PageModal";
import { PageContent } from "@/components/PageContent";
import searchFormConfig from "./config/search.config";
import contentConfig from "./config/content.config";
import modalConfig from "./config/modal.config";

const { pageContentRef, handleResetClick, handleSearchClick } = usePageSearch();

const { pageModalRef, handlePreviewData, handleUpdateData, handleConfrim, handleCreateData, defaultInfo } = usePageModal({
  pageContentRef
});
</script>

<template>
  <div class="main role">
    <PageSearch :form-config="searchFormConfig" @search-btn-click="handleSearchClick"
      @reset-btn-click="handleResetClick" />

    <PageContent ref="pageContentRef" :content-config="contentConfig" @preview-btn-click="handlePreviewData"
      @update-btn-click="handleUpdateData" @create-btn-click="handleCreateData" />

    <PageModal ref="pageModalRef" :modal-config="modalConfig" :default-info="defaultInfo"
      @confirm-btn-click="handleConfrim" />
  </div>
</template>
