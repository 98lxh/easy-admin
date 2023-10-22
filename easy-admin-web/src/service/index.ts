import Service from "./request/index";
import { ElMessage } from "element-plus"
import { router } from "@/router"

const service = new Service({
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: 5000,
  interceptors: {
    requestInterceptor(config) {
      return config
    },
    responseInterceptor(response) {
      const { code, message } = response.data as any;

      switch(Number(code)){
          case 500:
            ElMessage.warning(message);
            break;
          case 401:
            ElMessage.warning(message);
            router.replace("/login")
            break;
          case 403:
            ElMessage.warning(message);
            break;
      }

      return response.data
    },
    responseInterceptorCatch(error) {
      const { code, message } = error.response;
      if(Number(code) === 504 || Number(code) === 404){
      }

      switch(Number(code)){
        case 504:
        case 404:
          ElMessage.error("服务器内部错误( ╯□╰ )")
          break;
        case 403:
          ElMessage.error("权限不足,请联系管理员( ╯□╰ )")
          break;
        case 401:
          ElMessage.error("尚未登录,请登录( ╯□╰ )")
          router.replace("/login")
          break;
        default:
          if(message) {
            ElMessage.error(message)
          }else{
            ElMessage.error("服务器未知错误( ╯□╰ )")
          }
      }
      return error
    }
  },
});

export default service;
