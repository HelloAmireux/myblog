<template>
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
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String
  },
  computed: {
    curPageName () {
      return this.$store.state.curPageName
    }
  },
  methods: {
    collapse () {
      this.$emit('collapse')
    }
  }
}
</script>

<style scoped>
</style>
