<template>
  <div>
    <el-card class="user-info-card">
      <div slot="header">
        <span>用户信息</span>
      </div>
      <el-row class="el-row-self">
        <el-col :span="4">账户地址：</el-col>
        <el-col :span="20">{{ address }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="4">账号余额：</el-col>
        <el-col :span="20">
          <el-progress
            :text-inside="true"
            :stroke-width="22"
            :percentage="balance / 500"
            :format="myBalance"
          ></el-progress>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "UserInfo",
  data() {
    return {
      address: "0x123456789abcdefghi",
      balance: 5000
    };
  },
  methods: {
    myBalance() {
      return "" + this.balance;
    }
  }
};
</script>

<style lang="less" scoped>
.user-info-card {
  width: 600px;
  margin-top: 50px;
  .el-row-self {
    margin-bottom: 20px;
  }
}
</style>
