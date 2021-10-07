import Vue from 'vue'
import VueRouter from 'vue-router'
import Deathratepage from '@/views/Deathratepage'
import Searchpage from '@/views/Searchpage'
import Screenpage from '@/views/Screenpage'
import Usamappage from '@/views/Usamappage'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/Screenpage'
  },
  {
    path: '/Deathratepage',
    component: Deathratepage
  },
  {
    path: '/Searchpage',
    component: Searchpage
  },
  {
    path: '/Usamappage',
    component: Usamappage
  },
  {
    path: '/Screenpage',
    component: Screenpage
  }
]

const router = new VueRouter({
  routes
})
export default router
