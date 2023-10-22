import type { AxiosResponse, AxiosRequestConfig } from "axios";

export interface IServiceInterceptors<T = AxiosResponse> {
  requestInterceptor?: (config: AxiosRequestConfig) => AxiosRequestConfig;
  requestInterceptorCatch?: (error: any) => any;
  responseInterceptor?: (response: AxiosResponse<T>) => AxiosResponse<T>;
  responseInterceptorCatch?: (error: any) => any;
}

export interface IRequestConfig<T = AxiosResponse> extends AxiosRequestConfig {
  interceptors?: IServiceInterceptors<T>;
}
