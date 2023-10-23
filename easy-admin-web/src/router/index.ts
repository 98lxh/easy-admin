import Dashboard from "@/pages/welcome/dashboard/index.vue"
import User from "@/pages/system/user/index.vue"
import Login from "@/pages/login/login.vue";
import Layout from "@/layout/index.vue"

import { createRouter, createWebHistory } from "vue-router"
import { type App } from "vue"



const routes = [
  { path: "/login", component: Login },
  {
    path: "/", component: Layout, children: [
      { path: "/system/user", component: User },
      { path: "/welcome/dashboard", component: Dashboard }
    ]
  }
]

export const router = createRouter({
  history: createWebHistory(),
  routes
})

export function setupRouter(app: App) {
  app.use(router)
}

