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
        <el-table-column prop="orderStatus" label="订单状态" width="80px"></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "adminOrder",
  data() {
    return {
      orderList: [
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
        },
        {
          orderId: 4,
          orderBuyer: "0x35aa03c98231a21a2c424c1d2bdd88ae654a44a6",
          orderSeller: "0x35aa03c98231a21a2c424c1d2bdd88ae654a44a6",
          orderTime: "Tue Jul 02 10:25:10 CST 2019",
          petId: 1,
          petPrice: 2000,
          orderStatus: 1,
        }
      ]
    };
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
