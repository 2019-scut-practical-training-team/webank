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
            <div>{{ "ID:" + pet.petId }}</div>
            <div>{{ "￥" + pet.petPrice }}</div>
            <div>{{ pet.petIntro }}</div>
          </div>
        </el-card>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "AdminMarket",
  data() {
    return {
      petList: null
    };
  },
  created() {
    this.$axios
      .get(this.$axios.baseURL + "/api/market/pets")
      .then(response => {
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
  height: 140px;
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
</style>
