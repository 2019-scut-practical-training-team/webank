<template>
  <div>
    <el-card class="return-orders-card">
      <div slot="header">退款订单</div>
      <el-table :data="orderList" style="width: 100%; margin-bottom: 20px">
        <el-table-column prop="orderId" label="订单编号" width="50px"></el-table-column>
        <el-table-column prop="orderBuyer" label="买家" width="200px"></el-table-column>
        <el-table-column prop="orderSeller" label="卖家" width="200px"></el-table-column>
        <el-table-column prop="orderTime" label="时间" width="240px"></el-table-column>
        <el-table-column prop="petId" label="宠物ID" width="50px"></el-table-column>
        <el-table-column prop="petPrice" label="宠物价格" width="80px"></el-table-column>
        <el-table-column prop="reason" label="退款理由" width="140px"></el-table-column>
        <el-table-column fixed="right" label="操作" width="100px">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click.native.prevent="handleConfirmReturn(scope.$index, orderList)"
            >同意</el-button>
            <el-button
              type="text"
              size="small"
              @click="handleRejectReturn(scope.$index, orderList)"
            >拒绝</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="确认同意" :visible.sync="confirmReturnDialogVisiable" width="500px">
      <span>是否确认同意退款</span>
      <span slot="footer">
        <el-button type="primary" @click="submitConfirmReturn()">确定</el-button>
        <el-button @click="confirmReturnDialogVisiable = false">取消</el-button>
      </span>
    </el-dialog>
    <el-dialog title="确认取消" :visible.sync="rejectReturnDialogVisiable" width="500px">
      <span>是否确认拒绝退款</span>
      <span slot="footer">
        <el-button type="primary" @click="submitRejectReturn()">确定</el-button>
        <el-button @click="rejectReturnDialogVisiable = false">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "AdminReturnInfo",
  data() {
    return {
      confirmReturnDialogVisiable: false,
      rejectReturnDialogVisiable: false,
      orderList: [
        {
          orderId: 1,
          orderBuyer: "0x35aa03c98231a21a2c424c1d2bdd88ae654a44a6",
          orderSeller: "0x35aa03c98231a21a2c424c1d2bdd88ae654a44a6",
          orderTime: "Tue Jul 02 10:25:10 CST 2019",
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
          reason: "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊"
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
        },
        {
          orderId: 4,
          orderBuyer: "0x35aa03c98231a21a2c424c1d2bdd88ae654a44a6",
          orderSeller: "0x35aa03c98231a21a2c424c1d2bdd88ae654a44a6",
          orderTime: "Tue Jul 02 10:25:10 CST 2019",
          petId: 1,
          petPrice: 2000,
          orderStatus: 1,
          reason: "不要了"
        }
      ],
      returningOrder: {
        index: null,
        rows: null
      }
    };
  },
  methods: {
    handleConfirmReturn(index, rows) {
      this.returningOrder.index = index;
      this.returningOrder.rows = rows;
      this.confirmReturnDialogVisiable = true;
    },
    handleRejectReturn(index, rows) {
      this.returningOrder.index = index;
      this.returningOrder.rows = rows;
      this.rejectReturnDialogVisiable = true;
    },
    submitConfirmReturn() {
      this.returningOrder.rows.splice(this.returningOrder.index, 1);
      this.confirmReturnDialogVisiable = false;
      this.$message({
        message: "同意退款成功！",
        type: "success"
      });
    },
    submitRejectReturn() {
      this.returningOrder.rows.splice(this.returningOrder.index, 1);
      this.rejectReturnDialogVisiable = false;
      this.$message({
        message: "拒绝退款成功！",
        type: "success"
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.return-orders-card {
  width: 960px;
  margin: 80px 0;
}
</style>
