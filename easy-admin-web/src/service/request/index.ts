import axios, { AxiosResponse, AxiosInstance } from "axios";
import type { IServiceInterceptors, IRequestConfig } from "./types";

class Service {
  instance: AxiosInstance;
  interceptors?: IServiceInterceptors;

  constructor(config: IRequestConfig) {
    this.interceptors = config.interceptors;
    this.instance = axios.create(config);
    this.instance.interceptors.request.use(
      this.interceptors?.requestInterceptor as any,
      this.interceptors?.requestInterceptorCatch
    );
    this.instance.interceptors.response.use(
      this.interceptors?.responseInterceptor,
      this.interceptors?.responseInterceptorCatch
    );
  }

  request<T>(config: IRequestConfig<T>): Promise<T> {
    return new Promise((resolve, reject) => {
      if (config.interceptors?.requestInterceptor) {
        config = config.interceptors.requestInterceptor(config);
      }

      this.instance
        .request<any, AxiosResponse<T>>(config)
        .then((res) => {
          if (config.interceptors?.responseInterceptor) {
            res = config.interceptors.responseInterceptor(res);
          }
          resolve(res.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  }

  get<T>(config: IRequestConfig<T>): Promise<T> {
    return this.request({ ...config, method: "GET" });
  }

  post<T>(config: IRequestConfig<T>): Promise<T> {
    return this.request({ ...config, method: "POST" });
  }

  delete<T>(config: IRequestConfig<T>): Promise<T> {
    return this.request({ ...config, method: "DELETE" });
  }

  patch<T>(config: IRequestConfig<T>): Promise<T> {
    return this.request({ ...config, method: "PATCH" });
  }
}

export default Service;
