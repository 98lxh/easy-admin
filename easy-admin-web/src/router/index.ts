import Login from "@/pages/login/login.vue";
import { createRouter, createWebHistory } from "vue-router"
import { type App  } from "vue"


const routes = [
  {path:"/", component:Login}
]

export const router = createRouter({
  history:createWebHistory(),
  routes
})

export function setupRouter(app:App){
  app.use(router)
}

