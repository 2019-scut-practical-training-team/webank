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
          prop="reason"
          label="退款理由"
          width="140px"
        ></el-table-column>
        <el-table-column fixed="right" label="操作" width="100px">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click.native.prevent="
                handleConfirmReturn(scope.$index, orderList)
              "
              >同意</el-button
            >
            <el-button
              type="text"
              size="small"
              @click="handleRejectReturn(scope.$index, orderList)"
              >拒绝</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog
      title="确认同意"
      :visible.sync="confirmReturnDialogVisiable"
      width="500px"
    >
      <span>是否确认同意退款</span>
      <span slot="footer">
        <el-button type="primary" @click="submitConfirmReturn()"
          >确定</el-button
        >
        <el-button @click="confirmReturnDialogVisiable = false">取消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="确认取消"
      :visible.sync="rejectReturnDialogVisiable"
      width="500px"
    >
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
      orderList: null,
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
      // 发送请求，同意退款
      this.$axios
        .post(this.$axios.baseURL + "/api/market/refund", {
          orderId: this.returningOrder.rows[this.returningOrder.index].orderId,
          op: 0
        })
        .then(response => {
          if (response.data.checked == true) {
            this.returningOrder.rows.splice(this.returningOrder.index, 1);
            this.confirmReturnDialogVisiable = false;
            this.$message({
              message: "同意退款成功！",
              type: "success"
            });
          } else {
            this.returningOrder.rows.splice(this.returningOrder.index, 1);
            this.confirmReturnDialogVisiable = false;
            this.$message({
              message: "退款失败！宠物已转手或卖家余额不足！",
              type: "error"
            });
          }
        })
        .catch(error => {
          window.console.log(error);
          this.$message({
            message: "同意退款失败，请重试。",
            type: "warning"
          });
        });
    },
    submitRejectReturn() {
      // 发生请求，拒绝退款
      this.$axios
        .post(this.$axios.baseURL + "/api/market/refund", {
          orderId: this.returningOrder.rows[this.returningOrder.index].orderId,
          op: 1
        })
        .then(response => {
          if (response.data.checked == true) {
            this.returningOrder.rows.splice(this.returningOrder.index, 1);
            this.rejectReturnDialogVisiable = false;
            this.$message({
              message: "拒绝退款成功！",
              type: "success"
            });
          } else {
            this.returningOrder.rows.splice(this.returningOrder.index, 1);
            this.rejectReturnDialogVisiable = false;
            this.$message({
              message: "拒绝退款失败！",
              type: "error"
            });
          }
        })
        .catch(error => {
          window.console.log(error);
          this.$message({
            message: "拒绝退款失败，请重试。",
            type: "warning"
          });
        });
    }
  },
  created() {
    // 发送请求。获取退款订单列表
    this.$axios
      .get(this.$axios.baseURL + "/api/market/refundlist")
      .then(response => {
        this.orderList = response.data.orderList;
        this.$message({
          message: "获取退款订单列表成功！",
          type: "success"
        });
      })
      .catch(error => {
        window.console.log(error);
        this.$message({
          message: "获取退款订单列表失败！",
          type: "error"
        });
      });
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.return-orders-card {
  width: 1100px;
  margin: 80px 0;
}
</style>
