<template>
  <div class="wrapper">

    <!--    项目名称-->
    <div style="height: 60px;line-height: 60px;font-size: 20px;padding-left: 50px;color: white;text-align: center;align-content: center;background-color: #cccccc">
      注册页面
    </div>

    <div style="display: flex;width:55%;height:40%;margin: 150px auto;background-color: white;border-radius: 10px;overflow: hidden;background-color: bisque">
      <!--      左侧图片显示-->
      <div style="width: 50%;margin-top: 30px;margin-left: 30px">
        <img src="../assets/logo.png" alt="" style="width:100%;height:90%" />
      </div>
      <!--      提交表单-->
      <div style="width:350px;margin-top: 30px">
        <div style="margin: 20px 0;text-align: center;font-size: 20px">注册页面</div>
        <el-form :model="userForm" :rules="rules" ref="userForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="userForm.username" prefix-icon="el-icon-user"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="userForm.password" prefix-icon="el-icon-lock" type="password" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="confirmPassword">
            <el-input v-model="userForm.confirmPassword" prefix-icon="el-icon-lock" type="password" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="register('userForm')">注册</el-button>
            <el-button type="success" @click="$router.push('/login')">返回登录</el-button>
            <!--            <el-button type="warning" @click="resetForm('userForm')">重置</el-button>-->
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data(){
    return{
      userForm:{},

      rules: {
        username: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 50, message: '长度不能小于6位', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请输入确认密码', trigger: 'blur' },
          { min: 6, max: 50, message: '长度不能小于6位', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    //提交数据
    register(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.userForm.password !== this.userForm.confirmPassword){
            this.$message.warning("两次密码不一致，请重新输入");
            return;
          }
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // 重置数据
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    // 验证码方法
    refreshCode(){
      this.identifyCode = ''
      this.makeCode(this.identifyCodes,4);
    },
    makeCode(o,l){
      for(let i = 0;i < l;i++){
        this.identifyCode += this.identifyCodes[
            this.randomNum(0,this.identifyCodes.length)
            ]
      }
    },
    randomNum(min,max){
      return Math.floor(Math.random() * (max - min) + min);
    }
  }
}
</script>

<style scoped>
.wrapper{
  background: url("../assets/logo.png");
  width:100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
}
</style>
