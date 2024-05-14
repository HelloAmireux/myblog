<template>
  <el-container style="min-height: 100vh">

    <el-aside width="sideWidth+'px'">
      <!-- begin -->
      <el-menu
        default-active="1-4-1"
        class="el-menu-vertical-demo"
        style="min-height:100%;overflow-x:hidden"
        text-color="#fff"
        active-text-color="#ffd046"
        background-color="rgb(48,65,86)"
        :collapse-transition="false"
        router
        :collapse="isCollapse"
      >
        <div style="height:60px;line-height:60px;text-align:center">
          <img
            src="../assets/logo.png"
            style=" width:30px;position:relative;margin-right:5px;top:6px"
          >
          <b
            style="color:wheat"
            v-show="logoTextShow"
          >后台管理系统</b>
        </div>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span slot="title">导航一</span>
          </template>
          <el-menu-item-group>
            <span slot="title">分组一</span>
            <el-menu-item index="/user">用户管理</el-menu-item>
            <el-menu-item index="/role">角色</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <span slot="title">选项4</span>
            <el-menu-item index="1-4-1">选项1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-menu-item index="2">
          <i class="el-icon-menu"></i>
          <span slot="title">导航二</span>
        </el-menu-item>

      </el-menu>
      <!-- end -->

      <Aside
        :isCollapse="isCollapse"
        :logoTextShow="logoTextShow"
      ></Aside>

    </el-aside>

    <el-container>
      <el-header style="border-bottom: 1px solid #ccc">

        <!-- begin -->
        <div style="line-height: 60px;display: flex">
          <div style="flex:1; font-size:30px">

            <!--            下面这个不知道为什么显示不出图标   因为没加":"-->
            <span
              :class="collapseBtnClass"
              style="cursor: pointer"
              @click="collapse"
            >
            </span>
            <el-breadcrumb
              separator-class="el-icon-arrow-right"
              style="display:inline-black;margin-left:32px;position:absolute;top:22px"
            >
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item>{{ curPageName }}</el-breadcrumb-item>

            </el-breadcrumb>
          </div>

          <el-dropdown style="width:150px;cursor:pointer;text-align:center">
            <div style="display: inline-block">
              <img
                src="../assets/logo.png"
                style="width: 35px;height: 35px;border-radius: 50%;position: relative; top: 10px;right: 8px"
              >
              <span>张三</span><i
                class="el-icon-arrow-down"
                style="margin-left: 5px"
              ></i>
            </div>
            <i
              class="el-icon-setting"
              style="margin-right: 15px"
            ></i>
            <el-dropdown-menu
              slot="dropdown"
              style="width: 100px;text-align: center;text-decoration: none"
            >
              <el-dropdown-item style="font-size: 15px;padding: 5px 0 ">
                <router-link
                  to="/person1"
                  style="text-decoration: none;color: #606266"
                >
                  个人信息
                </router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 15px;padding: 5px 0 ">
                <router-link
                  to="/person"
                  style="text-decoration: none;color: #606266"
                >
                  修改密码
                </router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 15px;padding: 5px 0 ">
                <div style="text-decoration: none"> 退出登录</div>

              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <!-- end -->
        <Header
          @collapse="collapse"
          :collapseBtnClass="collapseBtnClass"
        ></Header>
      </el-header>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Aside from '@/components/Aside.vue'
import Header from '@/components/Header'
export default {
  name: "Manage",
  component: {
    Aside,
    Header
  },

  data () {
    return {
      logoTextShow: true,
      isCollapse: false,
      collapseBtnClass: 'el-icon-s-fold',
      sideWidth: 200
    }
  },
  computed: {
    curPageName () {
      return this.$store.state.curPageName
    }
  },
  methods: {
    collapse () {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        //收缩
        this.sideWidth = 64;
        this.collapseBtnClass = 'el-icon-s-unfold';
        this.logoTextShow = false
      } else {
        //展开
        this.sideWidth = 200;
        this.collapseBtnClass = 'el-icon-s-fold';
        this.logoTextShow = true
      }
    }
  }

}

</script>

<style scoped>
/*去掉side侧边栏的滚动条*/
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
