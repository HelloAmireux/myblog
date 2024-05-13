import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    curPageName: ''
  },
  getters: {
  },
  mutations: {
    setPath (state) {
      state.curPageName = localStorage.getItem('curPageName');
    }
  },
  actions: {
  },
  modules: {
  }
})
