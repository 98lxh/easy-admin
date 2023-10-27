import { defineStore } from "pinia";
import { IUserState } from "./types";
import { getToken, removeToken, setToken } from "@/utils/auth";
import { login, getUserInfo } from "@/service/modules/system/user";
import { router } from "@/router";

export const useUserStore = defineStore({
  id: "userStore",
  state: (): IUserState => ({
    info: null,
    token: getToken()
  }),
  actions: {
    async login(form: Record<string, string>) {
      const response = await login(form)
      const { code, data } = response;
      if (code !== 200)  return null;
      setToken(data.token!)
      this.initialUserInfo() // 初始化用户信息
      return response;
    },
    async initialUserInfo(){
      const { code, data }  = await getUserInfo();
      if(code !== 200) return;
      this.info = data;
    }
  }
})


export function logout(){
  removeToken();
  router.push("/login")
}
