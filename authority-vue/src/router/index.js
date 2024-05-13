import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'manage',
    component: () => import(/* webpackChunkName: "about" */ '../views/Manage.vue'),
    // children:{
    //   path: 'user',
    //   name: 'user',
    //   component: () => import(/* webpackChunkName: "about" */ '@/components/HelloWorld')
    // }
    //  TODO 上面的两个问题就是 注释children之后能运行  扫描view的会卡死但是扫描components没事
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
