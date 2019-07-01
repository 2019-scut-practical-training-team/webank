<template>
  <div class="tranContainer">
    <div v-for="l in list" :key="l" style="margin-top:20px;">
      <el-card class="box-card">
        <span>订单号：{{ l.orderId }}</span>
        <div class="container">
          <div class="text">
            <span>交易宠物ID：{{ l.petId }}</span>
            <span>买家账户：{{ l.orderBuyer }}</span>
            <span>交易时间：{{ l.orderTime }}</span>
          </div>
          <div class="text">
            <span>交易价格：{{ l.perPrice }}</span>
            <span>卖家账户：{{ l.orderSeller }}</span>
            <span v-if="l.orderStatus == 1">是否退货中：是</span>
            <span v-if="l.orderStatus == 0">是否退货中：否</span>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminTran",
  data() {
    return {
      list: [
        {
          orderId: 1,
          orderBuyer: "0x123",
          orderSeller: "0x456",
          orderTime: "yyyy-MM-dd HH:mm:ss",
          petId: 1,
          petPrice: 2000,
          orderStatus: 1
        },
        {
          orderId: 1,
          orderBuyer: "0x123",
          orderSeller: "0x456",
          orderTime: "yyyy-MM-dd HH:mm:ss",
          petId: 1,
          petPrice: 2000,
          orderStatus: 0
        },
        {
          orderId: 1,
          orderBuyer: "0x123",
          orderSeller: "0x456",
          orderTime: "yyyy-MM-dd HH:mm:ss",
          petId: 1,
          petPrice: 2000,
          orderStatus: 1
        }
      ]
    };
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.box-card {
  width: 1000px;
}

.text {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: left;
  width: 400px;
}

.container {
  display: flex;
}

.tranContainer {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}
</style>
