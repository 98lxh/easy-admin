import { App } from 'vue'
import * as ElementPlusIcons from '@element-plus/icons-vue'


const toline = (value: string) => {
  return value.replace(/A-Z/g, '-$1').toLowerCase()
}

export function setupIcons(app: App) {
  for (const icon in ElementPlusIcons) {
    //注册全局组件
    app.component('el-icon-' + toline(icon), (ElementPlusIcons as any)[icon])
  }
}
