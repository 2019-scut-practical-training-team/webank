<template>
  <div class="all">
    <div class="tranContainer">
      <div v-for="l in list" :key="l" style="margin-top:20px;">
        <el-card class="box-card">
          <div class="container">
            <img :src="l.petImg" class="image" />
            <div class="text">
              <span>名字：{{ l.petName }}</span>
              <span>价格：{{ l.petPrice }}</span>
              <span>宠物类别：{{ l.petType }}</span>
              <span>宠物ID：{{ l.petId }}</span>
              <span>介绍：{{ l.petIntro }}</span>
              <span>卖家账户：{{ l.owner }}</span>
            </div>
          </div>
        </el-card>
      </div>
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
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只狗狗狗狗狗狗狗狗",
          owner: "0x123"
        },
        {
          petId: 1,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只狗狗狗狗狗狗狗狗",
          owner: "0x123"
        },
        {
          petId: 1,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只狗狗狗狗狗狗狗狗",
          owner: "0x123"
        },
        {
          petId: 1,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只狗狗狗狗狗狗狗狗",
          owner: "0x123"
        },
        {
          petId: 1,
          petType: "狗",
          petPrice: 100,
          petName: "tom",
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804109660&di=1c11266cac314c21f719f27e6225e3ee&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F07%2F20150507214556_JYinM.jpeg",
          petIntro: "这是一只狗狗狗狗狗狗狗狗",
          owner: "0x123"
        }
      ]
    };
  }
};
</script>

<style scoped>
.image {
  width: 240px;
  height: 200px;
}

.box-card {
  width: 280px;
  height: 370px;
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

.all {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 5%;
}
</style>
