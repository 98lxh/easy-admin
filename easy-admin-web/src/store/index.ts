import { type App } from "vue"
import { createPinia } from "pinia"
import { getToken } from "@/utils/auth";
import { useUserStore } from "./modules/user";

const store = createPinia();

export function setupPinia(app: App<Element>) {
  app.use(store)
}


export async function initialApp(){
  const token = getToken();
  if(!token) return null;
  const userStore = useUserStore();
  await userStore.initialUserInfo();
}
