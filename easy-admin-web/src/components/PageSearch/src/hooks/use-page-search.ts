/* eslint-disable prettier/prettier */
import { ref } from "vue";
import { PageContentInstance } from "@/components/PageContent";

export function usePageSearch() {
  const pageContentRef = ref<PageContentInstance | null>(null);

  function handleSearchClick(params: any) {
    pageContentRef.value.getPageData(params);
  }

  function handleResetClick() {
    pageContentRef.value.getPageData();
  }

  return {
    pageContentRef,
    handleSearchClick,
    handleResetClick
  };
}
