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
              @click="handlePurchasePet(pet.petId)"
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
        <el-button type="primary" @click="submintPurchasePetForm()"
          >确认</el-button
        >
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
      petList: null,
      purchasePetForm: {
        petId: null
      }
    };
  },
  methods: {
    submintPurchasePetForm() {
      this.$axios
        .post(this.$axios.baseURL + "/api/user/buy", {
          key: sessionStorage.getItem("privateKey"),
          petId: this.purchasePetForm.petId
        })
        .then(response => {
          if (response.data.checked == true) {
            // 改变宠物的持有者
            // for (let pet of this.petList) {
            //   if (pet.petId === this.purchasePetForm.petId) {
            //     pet.owner = sessionStorage.getItem("address");
            //     break;
            //   }
            // }
            // 关闭二次确认窗口
            this.purchaseConfirmDialogVisiable = false;
            // 显示成功信息
            this.$message({
              message: "购买宠物成功！",
              type: "success"
            });
            // 刷新页面
            setTimeout(() => {
              window.location.reload();
            }, 1500);
          } else {
            this.purchaseConfirmDialogVisiable = false;
            this.$message({
              message: "购买宠物失败！",
              type: "error"
            });
            setTimeout(() => {
              window.location.reload();
            }, 2000);
          }
        })
        .catch();
    },
    handlePurchasePet(id) {
      this.purchasePetForm.petId = id;
      this.purchaseConfirmDialogVisiable = true;
    },
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
    }
  },
  created() {
    this.$axios
      .get(this.$axios.baseURL + "/api/market/pets")
      .then(response => {
        window.console.log(response);
        this.petList = response.data.petsList;
        this.$message({
          message: "获取在售宠物列表成功!",
          type: "success"
        });
      })
      .catch(error => {
        window.console.log(error);
        this.$message({
          message: "获取在售宠物列表失败!",
          type: "error"
        });
      });
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
  height: 120px;
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
