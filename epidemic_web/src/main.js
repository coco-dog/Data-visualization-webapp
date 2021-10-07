import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/globle.less'
import 'element-ui/lib/theme-chalk/index.css'
import { Input, Select, Button, Option, Table, TableColumn, Autocomplete } from 'element-ui'
import axios from 'axios'
// 请求基准路径的配置（后台接口）
axios.defaults.baseURL = 'http://127.0.0.1:8600//local/'
// 将axios挂载到vue的原型对象上
Vue.prototype.$http = axios
// 将全局的echarts挂载到VUE的原型对象上，
// 调用：this.$echarts
Vue.prototype.$echarts = window.echarts

Vue.config.productionTip = false
Vue.use(Input)
Vue.use(Select)
Vue.use(Button)
Vue.use(Option)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Autocomplete)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
