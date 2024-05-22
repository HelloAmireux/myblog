import store from '@/store'
import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'manage',
    component: () => import(/* webpackChunkName: "about" */ '@/components/Manage'),
    children: [
      {
        path: '/user',
        name: '用户管理',
        component: () => import(/* webpackChunkName: "about" */ '@/components/User')
      },
      {
        path: '/role',
        name: '角色管理',
        component: () => import(/* webpackChunkName: "about" */ '@/components/Role')
      }
    ]

  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "about" */ '@/components/Login'),
  },
  {
    path: '/register',
    name: 'register',
    component: () => import(/* webpackChunkName: "about" */ '@/components/Register'),
  },


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

router.beforeEach((to, from, next) => {
  localStorage.setItem('curPageName', to.name)
  store.commit('setPath')
  next()
})
