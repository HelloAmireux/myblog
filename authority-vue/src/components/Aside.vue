<template>
  <div>
    zhanshi
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
  </div>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  }
}
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
