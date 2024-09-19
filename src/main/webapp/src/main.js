import { createApp } from 'vue'
//引入組件
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import ElementPlusicon from '@element-plus/icons-vue'


import App from './App.vue'

const app = createApp(App)

app.use(ElementPlus)
app.use(ElementPlusicon)
app.mount('#app')
