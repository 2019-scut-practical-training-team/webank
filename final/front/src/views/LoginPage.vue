<template>
  <div class="login-page">
    <el-row>
      <el-col :span="24">
        <div class="grid-top" :style="backgroundHeight"></div>
      </el-col>
    </el-row>
    <el-form
      ref="form"
      :model="form"
      label-width="auto"
      class="login-form"
      label-position="top"
      :rules="loginRules"
    >
      <el-form-item>
        <span class="login-header">登录</span>
      </el-form-item>
      <!-- <el-divider style="margin-bottom: 0"></el-divider> -->
      <el-form-item label="密钥：" prop="privateKey">
        <el-input v-model="form.privateKey" placeholder="请输入你的密钥" prefix-icon="el-icon-key"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" class="button-width" @click="login('form')">登录</el-button>
      </el-form-item>
      <el-divider content-position="center">还没有账号？</el-divider>
      <el-form-item>
        <el-button class="button-width" @click="signUpDialogVisible = true">注册</el-button>
      </el-form-item>
    </el-form>
    <el-dialog title="提示" :visible.sync="signUpDialogVisible" width="380px" show-close center>
      <span>是否确认注册账号</span>
      <span slot="footer">
        <el-button type="primary" @click="signUpDialogVisible = false">确认</el-button>
        <el-button @click="signUpDialogVisible = false">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "LoginPage",
  data: function() {
    var validatePrivateKey = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密钥"));
      } else {
        callback();
      }
    };
    return {
      backgroundHeight: {
        height: ""
      },
      form: {
        privateKey: ""
      },
      signUpDialogVisible: false,
      loginRules: {
        privateKey: [{ validator: validatePrivateKey, trigger: "blur" }]
      }
    };
  },
  methods: {
    // 获取页面的高度
    getHeight() {
      this.backgroundHeight.height = window.innerHeight / 2.8 + "px";
    },
    login(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          // 登录成功
          // 模拟登录
          sessionStorage.setItem("privateKey", "123456");
          sessionStorage.setItem("address", "123456");
          // 路由跳转
          this.$router.push("admin");
        } else {
          this.$message({
            message: "登录失败",
            type: "error"
          });
          return false;
        }
      });
    }
  },
  created() {
    window.addEventListener("resize", this.getHeight);
    this.getHeight();
    // 开发需要，直接跳转
    // this.login();
  },
  destroyed() {
    window.removeEventListener("resize", this.getHeight);
  }
};
</script>

<style lang="less" scoped>
.login-page {
  min-height: 600px;
  min-width: 768px;
}
.grid-top {
  background-color: rgb(64, 158, 255); // 深蓝
  // background-color: #9eceff;  //浅蓝
  min-height: 300px;
  max-height: 500px;
  width: 100%;
  z-index: -1;
}
.login-form {
  position: relative;
  bottom: 85px;
  width: 300px;
  height: 380px;
  background-color: white;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  margin: 0 auto;
  z-index: 2;
  padding: 0 30px;
}
.login-header {
  display: flex;
  justify-content: center;
  margin-top: 30px;
  font-size: 30px;
}
.button-width {
  width: 100%;
}
</style>
