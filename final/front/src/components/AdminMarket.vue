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
      petList: [
        {
          petId: 1,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petStatus: 1,
          petImg: "",
          petIntro: "这是一只狗啊啊啊啊啊啊啊啊啊"
        },
        {
          petId: 2,
          petType: "猫",
          petPrice: 200,
          petName: "jerry",
          petStatus: 0,
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只猫"
        },
        {
          petId: 3,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petStatus: 1,
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804170123&di=1ddf8a5e1d4345013ee0cd36ad3a1ba9&imgtype=0&src=http%3A%2F%2Fuploads.oh100.com%2Fallimg%2F1709%2F132-1FZ2121051.jpg",
          petIntro: "这是一只狗"
        },
        {
          petId: 4,
          petType: "猫",
          petPrice: 200,
          petName: "jerry",
          petStatus: 0,
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只猫"
        },
        {
          petId: 5,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petStatus: 1,
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804170123&di=1ddf8a5e1d4345013ee0cd36ad3a1ba9&imgtype=0&src=http%3A%2F%2Fuploads.oh100.com%2Fallimg%2F1709%2F132-1FZ2121051.jpg",
          petIntro: "这是一只狗"
        },
        {
          petId: 6,
          petType: "猫",
          petPrice: 200,
          petName: "jerry",
          petStatus: 0,
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只猫"
        },
        {
          petId: 7,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petStatus: 1,
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561812530148&di=d31285463da7b5651d88a19ea5a4c4f7&imgtype=0&src=http%3A%2F%2Fwww.pig66.com%2Fuploadfile%2F2018%2F0110%2F20180110102944250.jpg",
          petIntro: "这是一只狗"
        }
      ]
    };
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
</style>
