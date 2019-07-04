<template>
  <div>
    <el-card class="return-orders-card">
      <div slot="header">退款订单</div>
      <el-table :data="orderList" style="width: 100%; margin-bottom: 20px">
        <el-table-column
          prop="orderId"
          label="订单编号"
          width="50px"
        ></el-table-column>
        <el-table-column
          prop="orderBuyer"
          label="买家"
          width="200px"
        ></el-table-column>
        <el-table-column
          prop="orderSeller"
          label="卖家"
          width="200px"
        ></el-table-column>
        <el-table-column
          prop="orderTime"
          label="时间"
          width="240px"
        ></el-table-column>
        <el-table-column
          prop="petId"
          label="宠物ID"
          width="50px"
        ></el-table-column>
        <el-table-column
          prop="petPrice"
          label="宠物价格"
          width="80px"
        ></el-table-column>
        <el-table-column
          prop="orderStatus"
          label="订单状态"
          width="80px"
        ></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "adminOrder",
  data() {
    return {
      orderList: null
    };
  },
  created() {
    this.$axios
      .get(this.$axios.baseURL + "/api/market/orders")
      .then(response => {
        this.orderList = response.data.orderList;
        this.$message({
          message: "获取市场所有订单列表成功！",
          type: "success"
        });
      })
      .catch(error => {
        window.console.log(error);
        this.$message({
          message: "获取市场所有订单列表失败！",
          type: "error"
        });
      });
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.return-orders-card {
  width: 940px;
  margin: 80px 0;
}
</style>
