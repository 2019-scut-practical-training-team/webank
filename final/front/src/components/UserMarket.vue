<template>
  <div>
    <el-card class="pets-market-card">
      <div slot="header">
        <span>宠物市场</span>
      </div>
      <div v-for="pet in petList" :key="pet.petId" class="pet-market-card">
        <el-card :body-style="{ padding: '0px' }">
          <el-image
            :src="pet.petImg"
            alt="Image"
            class="pet-market-img"
          ></el-image>
          <div class="pet-market-info">
            <div style="font-size: 23px">{{ pet.petName }}</div>
            <div>{{ pet.petType }}</div>
            <div>{{ "￥" + pet.petPrice }}</div>
            <div>{{ pet.petIntro }}</div>
          </div>
          <div class="pet-market-button">
            <el-button
              :type="checkOwner(pet.owner).type"
              :disabled="checkOwner(pet.owner).disabled"
              @click="purchaseConfirmDialogVisiable = true"
              >{{ checkOwner(pet.owner).text }}</el-button
            >
          </div>
        </el-card>
      </div>
    </el-card>
    <el-dialog
      :visible.sync="purchaseConfirmDialogVisiable"
      width="300px"
      center
    >
      <div>是否确认购买？</div>
      <span slot="footer">
        <el-button type="primary" @click="handlePurchase()">确认</el-button>
        <el-button @click="purchaseConfirmDialogVisiable = false"
          >取消</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "UserMarket",
  data() {
    return {
      purchaseConfirmDialogVisiable: false,
      petList: [
        {
          petId: 1,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg: "",
          petIntro: "这是一只狗",
          owner: "123"
        },
        {
          petId: 2,
          petType: "猫",
          petPrice: 100,
          petName: "jerry",
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只猫",
          owner: "123456"
        },
        {
          petId: 3,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562062837618&di=08ce468cf6dfdb78b6a2885573fb92b9&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fphotoblog%2F7%2F3%2F6%2F2%2F7362735%2F200810%2F3%2F1223049123211_mthumb.jpg",
          petIntro: "这是一只狗",
          owner: "123456"
        },
        {
          petId: 4,
          petType: "猫",
          petPrice: 100,
          petName: "jerry",
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只猫",
          owner: "123"
        },
        {
          petId: 5,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只狗",
          owner: "123"
        },
        {
          petId: 6,
          petType: "猫",
          petPrice: 100,
          petName: "jerry",
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只猫",
          owner: "123"
        },
        {
          petId: 7,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只狗",
          owner: "123"
        }
      ]
    };
  },
  methods: {
    checkOwner(address) {
      if (sessionStorage.getItem("address") == address) {
        return {
          type: "info",
          text: "无法购买自己的宠物",
          disabled: true
        };
      } else {
        return {
          type: "primary",
          text: "购买",
          disabled: false
        };
      }
    },
    handlePurchase() {
      this.purchaseConfirmDialogVisiable = false;
      this.$message({
        message: "购买成功！",
        type: "success"
      });
    }
  }
};
</script>

<style lang="less" scoped>
@pet-img-width: 240px;
@pet-img-height: @pet-img-width;
.pets-market-card {
  margin: 100px 0;
  padding-bottom: 30px;
  width: 1200px;
}
.pet-market-card {
  width: @pet-img-width;
  float: left;
  margin: 20px;
}
.pet-market-info {
  padding: 14px;
}
.pet-market-img {
  width: @pet-img-width;
  height: @pet-img-height;
  display: block;
}
.pet-market-info div {
  font-size: 15px;
  margin: 5px 0;
}
.pet-market-button {
  margin: 0 20px 10px;
  display: flex;
  justify-content: center;
  button {
    width: 200px;
  }
}
</style>
