<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区 -->
      <div class="avatar_box">
        <img src="../assets/logo.png" />
      </div>
      <!-- 登录表单区域 -->
      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginFormRules"
        label-width="0px"
        class="login_form"
      >
        <!-- 用户名 -->
        <el-form-item prop="userName">
          <el-input
            v-model="loginForm.userName"
            prefix-icon="el-icon-user"
          ></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="userPwd">
          <el-input
            v-model="loginForm.userPwd"
            prefix-icon="el-icon-password"
            show-password
          ></el-input>
        </el-form-item>
        <!-- 按钮 -->
        <el-form-item class="btns">
          <el-button type="primary" @click="login" @keyup.enter="keyDown(e)">登录</el-button>
          <el-button type="success" @click="register">注册</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>



<script>

import {apiLogin} from '@/api/api'

export default {
  data() {
    return {
      //这是登录表单的数据对象
      loginForm: {
        userName: "",
        userPwd: "",
      },
      //这是登录表单的验证规则对象
      loginFormRules: {
        userName: [
          { required: true, message: "请输入用户名称", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
          {
            pattern: /^[a-zA-Z\d_]*$/,
            message: `仅能出现英⽂字符、数字与下划线`,
            trigger: "blur",
          },
        ],
        userPwd: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 6,
            max: 32,
            message: "长度在 6 到 32 个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    //重置表单
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields();
    },

    login() {
      this.$refs.loginFormRef.validate(async (valid) => {
        if (!valid) return;
       // md5加密 密码
        var loginForm={...this.loginForm};
        loginForm.userPwd=this.$md5(loginForm.userPwd);
        apiLogin(loginForm).then(response =>{
          if (!response.success) return this.$message.error(response.message);
          this.$message({
            showClose: true,
            message: "登录成功",
            type: 'success'
          });
          //保存token
          window.sessionStorage.setItem("token", response.object.token);
          this.$router.push("/home");
        });
        //提示登录信息
        //var result = { success: true, message: "登录成功", token: "adadad" };

        //if (!result.success) return this.$message.error(result.message);
        //this.$message({
        //  showClose: true,
        //  message: result.message,
        //  type: 'success'
        //});
        //保存token
        //window.sessionStorage.setItem("token", result.token);
        //this.$router.push("/home");
      });
    },
    register(){
        this.$router.push("/register");
    },
    // 点击回车键登录
    keyDown(e) {
      // 回车则执行登录方法 enter键的ASCII是13
      if (e.keyCode ==13 || e.keyCode == 100) {
        this.login(); // 定义的登录方法
      }
    },

  },
  // 绑定监听事件
  mounted () {
    window.addEventListener('keydown', this.keyDown)
  },
  // 销毁事件
  destroyed () {
    window.removeEventListener('keydown', this.keyDown, false)
  }

};
</script>

<style lang="less" scoped>
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}
.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

  .avatar_box {
    height: 130px;
    width: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;

    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }

  .login_form {
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;
  }
  .btns {
    display: flex;
    justify-content: flex-end;
  }
}
</style>
