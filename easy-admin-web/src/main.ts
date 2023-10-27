import { createApp ,type App} from 'vue'
import AppComponent from './App.vue'
import ElementPlus from "element-plus"
import { setupRouter } from "@/router"
import { setupPinia } from "@/store"
import { setupIcons } from './plugins/icon'

import "element-plus/dist/index.css"
import "@/style/index.scss"

async function bootstrap(app:App){
  setupPinia(app)
  setupRouter(app)
  setupIcons(app)
  app.use(ElementPlus)
  app.mount('#app')
}

await bootstrap(createApp(AppComponent));
