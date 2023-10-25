import Dashboard from "@/pages/welcome/dashboard/index.vue"
import User from "@/pages/system/user/index.vue"
import Login from "@/pages/login/login.vue";
import Layout from "@/layout/index.vue"

import { createRouter, createWebHistory } from "vue-router"
import { type App } from "vue"



const routes = [
  { path: "/login", component: Login },
  {
    path: "/welcome", component: Layout, children: [
      { path: "dashboard", component: Dashboard }
    ]
  },
  {
    path :"/system", component: Layout, children: [
      { path: "user", component: User },
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

