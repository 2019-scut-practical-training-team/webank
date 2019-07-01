<template>
  <div class="tranContainer">
    <div v-for="l in list" :key="l" style="margin-top:20px;">
      <el-card class="box-card">
        <div class="container">
          <img :src="l.petImg" class="image" />
          <div class="text">
            <span>价格：{{ l.petPrice }}</span>
            <span>宠物类别：{{ l.petType }}</span>
            <span>宠物ID：{{ l.petId }}</span>
            <span>介绍：{{ l.perIntro }}</span>
            <span>卖家账户：{{ l.owner }}</span>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminMarket",
  data() {
    return {
      list: [
        {
          petId: 1,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg:
            "https://raw.githubusercontent.com/2019-scut-practical-training-team/webank/master/day1/%E7%A7%A6%E5%8D%8E/image/1.png",
          petIntro: "这是一只狗",
          owner: "0x123"
        },
        {
          petId: 1,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg:
            "https://raw.githubusercontent.com/2019-scut-practical-training-team/webank/master/day1/%E7%A7%A6%E5%8D%8E/image/1.png",
          petIntro: "这是一只狗",
          owner: "0x123"
        },
        {
          petId: 1,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg:
            "https://raw.githubusercontent.com/2019-scut-practical-training-team/webank/master/day1/%E7%A7%A6%E5%8D%8E/image/1.png",
          petIntro: "这是一只狗",
          owner: "0x123"
        },
        {
          petId: 1,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg:
            "https://raw.githubusercontent.com/2019-scut-practical-training-team/webank/master/day1/%E7%A7%A6%E5%8D%8E/image/1.png",
          petIntro: "这是一只狗",
          owner: "0x123"
        },
        {
          petId: 1,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg:
            "https://raw.githubusercontent.com/2019-scut-practical-training-team/webank/master/day1/%E7%A7%A6%E5%8D%8E/image/1.png",
          petIntro: "这是一只狗",
          owner: "0x123"
        }
      ]
    };
  }
};
</script>

<style scoped>
.image {
  width: 260px;
  height: 200px;
}

.box-card {
  width: 300px;
  height: 360px;
  margin-left: 40px;
}

.text {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: left;
  margin-top: 15px;
}

.container {
  display: flex;
  flex-direction: column;
}

.tranContainer {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: wrap;
}
</style>
