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
      <el-row class="el-row-self">
        <el-col :span="4">账号余额：</el-col>
        <el-col :span="20">
          <el-progress
            :text-inside="true"
            :stroke-width="22"
            :percentage="balancePercentage"
            :format="myBalance"
            :status="balanceStatus"
          ></el-progress>
        </el-col>
      </el-row>
      <el-row class="el-row-self">
        <el-col :span="4">宠物数量：</el-col>
        <el-col :span="20">
          <el-progress
            :text-inside="true"
            :stroke-width="22"
            :percentage="petCountPercentage"
            :format="myPetCount"
            :status="petCountStatus"
          ></el-progress>
        </el-col>
      </el-row>
    </el-card>
    <el-card class="pets-card">
      <div slot="header">
        <span>我的宠物</span>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "UserInfo",
  data() {
    return {
      address: "0x123456789abcdefghi",
      balance: 5000,
      balancePercentage: 0,
      balanceStatus: "",
      petCount: 10,
      petCountPercentage: 0,
      petCountStatus: "",
      petList: [
        {
          petId: 1,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petStatus: 1,
          petImg: "www.baidu.com",
          petIntro: "这是一只狗"
        },

        {
          petId: 2,
          petType: "猫",
          petPrice: 200,
          petName: "jerry",
          petStatus: 0,
          petImg: "www.baidu.com",
          petIntro: "这是一只狗"
        }
      ]
    };
  },
  methods: {
    myBalance() {
      return "" + this.balance;
    },
    myPetCount() {
      return "" + this.petCount;
    }
  },
  created() {
    this.balancePercentage =
      this.balance / 500 > 100 ? 100 : this.balance / 500;
    if (this.balancePercentage === 100) {
      this.balanceStatus = "success";
    }

    this.petCountPercentage =
      this.petCount / 0.2 > 100 ? 100 : this.petCount / 0.2;
    if (this.petCountPercentage === 100) {
      this.petCountStatus = "success";
    }

    this.$axios
      .get(this.$axios.baseURL + "/api/market/pets")
      .then(res => {
        window.console.log(res);
      })
      .catch(err => {
        window.console.log(err);
      });
  }
};
</script>

<style lang="less" scoped>
.user-info-card {
  width: 700px;
  margin-top: 50px;
  .el-row-self {
    margin-bottom: 20px;
  }
}
.pets-card {
  margin-top: 50px;
  width: 900px;
}
</style>
