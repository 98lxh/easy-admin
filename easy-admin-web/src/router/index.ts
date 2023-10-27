import Dashboard from "@/pages/welcome/dashboard/index.vue"
import Login from "@/pages/login/login.vue";
import Layout from "@/layout/index.vue"


import User from "@/pages/system/user/index.vue"
import Role from "@/pages/system/role/index.vue"

import { createRouter, createWebHistory } from "vue-router"
import { type App } from "vue"
import { getToken } from "@/utils/auth";



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
      { path: "role", component: Role }
    ]
  }
]

export const router = createRouter({
  history: createWebHistory(),
  routes
})


const whites = ["/login"]
router.beforeEach((to, form, next) => {
  const token = getToken();

  if(!token && !whites.includes(to.path)){
    next("/login")
  }

  if(to.path === "/"){
    next("welcome/dashboard")
  }

  next();
})

export function setupRouter(app: App) {
  app.use(router)
}

