import { defineStore } from "pinia";
import { IUserState } from "./types";
import { getToken, setToken } from "@/utils/auth";
import { login } from "@/service/modules/user";

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
      if (code === 200) setToken(data.token!)
      return response;
    }
  }
})
