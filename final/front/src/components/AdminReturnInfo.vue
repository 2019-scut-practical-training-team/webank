<template>
  <div class="infoContainer">
    <div v-for="(l, index) in list" :key="l" style="margin-top:20px;">
      <el-card class="box-card" v-if="l.orderStatus == 1">
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
            <span v-if="l.orderStatus == 1">退货理由：{{ l.reason }}</span>
          </div>
          <el-button
            type="primary"
            class="buttons"
            style="margin-left:100px;"
            @click="agree(index)"
            >同意</el-button
          >
          <el-button
            type="primary"
            class="buttons"
            style="margin-left:30px;"
            @click="refuse(index)"
            >拒绝</el-button
          >
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminReturnInfo",
  watch() {
    // list;
  },
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
          orderStatus: 1,
          reason: "不要了"
        },
        {
          orderId: 2,
          orderBuyer: "0x123",
          orderSeller: "0x456",
          orderTime: "yyyy-MM-dd HH:mm:ss",
          petId: 1,
          petPrice: 2000,
          orderStatus: 1,
          reason: "不要了"
        },
        {
          orderId: 3,
          orderBuyer: "0x123",
          orderSeller: "0x456",
          orderTime: "yyyy-MM-dd HH:mm:ss",
          petId: 1,
          petPrice: 2000,
          orderStatus: 1,
          reason: "不要了"
        }
      ]
    };
  },
  methods: {
    agree(index) {
      this.$confirm("是否同意退货?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$message({
          type: "success",
          message: "已同意退货"
        });
        this.list[index].orderStatus = 0;
      });
    },
    refuse(index) {
      this.$confirm("是否拒绝退货?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$message({
          type: "success",
          message: "已拒绝退货"
        });
        this.list[index].orderStatus = 0;
      });
    }
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
  flex-direction: column;
  align-items: left;
  width: 300px;
}

.container {
  display: flex;
}

.buttons {
  width: 70px;
  height: 40px;
}

.infoContainer {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}
</style>
