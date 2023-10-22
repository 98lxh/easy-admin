import Login from "@/pages/login/login.vue";
import { createRouter, createWebHistory } from "vue-router"
import Layout from "@/layout/index.vue"
import { type App  } from "vue"


const routes = [
  {path: "/login", component:Login},
  {path:"/" , component: Layout}
]

export const router = createRouter({
  history:createWebHistory(),
  routes
})

export function setupRouter(app:App){
  app.use(router)
}

