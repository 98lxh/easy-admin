import { type App } from "vue"
import { createPinia } from "pinia"

const store = createPinia();

export function setupPinia(app: App<Element>) {
  app.use(store)
}
