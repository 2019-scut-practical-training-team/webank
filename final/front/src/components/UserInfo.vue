<template>
  <div>
    <el-card class="user-info-card">
      <div slot="header">
        <span>用户信息</span>
      </div>
      <el-row class="el-row-self">
        <el-col :span="4">账户地址：</el-col>
        <el-col :span="20"
          ><span id="userAddress" style="word-break: break-all">{{
            address
          }}</span></el-col
        >
      </el-row>
      <el-row class="el-row-self">
        <el-col :span="4">账号余额：</el-col>
        <el-col :span="20">
          <el-progress
            :text-inside="true"
            :stroke-width="22"
            :percentage="balancePercentage"
            :format="myBalance"
            :status="balancePercentage === 100 ? 'success' : null"
          ></el-progress>
        </el-col>
      </el-row>
      <el-row class="el-row-self">
        <el-col :span="4">宠物数量：</el-col>
        <el-col :span="20">
          <el-progress
            :text-inside="true"
            :stroke-width="22"
            :percentage="petCountPercentage"
            :format="myPetCount"
            :status="petCountPercentage === 100 ? 'success' : null"
          ></el-progress>
        </el-col>
      </el-row>
    </el-card>
    <el-card class="pets-card">
      <div slot="header">
        <span>我的宠物</span>
      </div>
      <div v-for="pet in petList" :key="pet.petId" class="pet-card">
        <el-card :body-style="{ padding: '0px' }">
          <el-image :src="pet.petImg" alt="Image" class="pet-img"></el-image>
          <div class="pet-info">
            <div style="font-size: 23px">{{ pet.petName }}</div>
            <div>{{ pet.petType }}</div>
            <div>{{ "￥" + pet.petPrice }}</div>
            <div>{{ pet.petIntro }}</div>
          </div>
          <div class="pet-buttons">
            <el-button
              type="primary"
              class="pet-button-left"
              :plain="pet.petStatus === 0 ? false : true"
              @click="changePetStatus(pet.petId, pet.petStatus)"
            >
              <span v-if="pet.petStatus == 0">上架</span>
              <span v-else>下架</span>
            </el-button>
            <el-button
              type="info"
              class="pet-button-right"
              @click="changePetInfo(pet)"
              >修改</el-button
            >
          </div>
        </el-card>
      </div>
      <div class="pet-card" v-if="canCreateNewPet">
        <el-card :body-style="{ padding: '0px' }">
          <div class="new-pet" @click="newPetDialogVisiable = true">
            <i class="el-icon-plus new-pet-icon"></i>
            <div class="new-pet-text">新建宠物</div>
          </div>
        </el-card>
      </div>
    </el-card>
    <el-card class="orders-card">
      <div slot="header">我的订单</div>
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
        <!-- <el-table-column prop="orderStatus" label="订单状态" width="80px"></el-table-column> -->
        <el-table-column label="操作" width="100px" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="small"
              :type="handleOrderStatus(scope.row.orderStatus)"
              @click="handleOrderClick(scope.row.orderId)"
              :disabled="
                isOrderButtonDisabled(
                  scope.row.orderStatus,
                  scope.row.orderBuyer
                )
              "
            >
              <span>{{
                getOrderButtonText(scope.row.orderStatus, scope.row.orderBuyer)
              }}</span>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog
      title="创建新宠物"
      :visible.sync="newPetDialogVisiable"
      width="500px"
      center
    >
      <el-form :model="newPetForm" :rules="petRules" ref="newPetForm">
        <el-form-item
          label="名称"
          :label-width="formLabelWidth"
          class="pet-form-item"
          prop="petName"
        >
          <el-input
            v-model="newPetForm.petName"
            placeholder="请输入你的宠物的名字"
            maxlength="6"
            show-word-limit
          ></el-input>
        </el-form-item>
        <el-form-item
          label="类型"
          :label-width="formLabelWidth"
          class="pet-form-item"
          prop="petType"
        >
          <el-select v-model="newPetForm.petType" placeholder="请选择">
            <el-option
              v-for="petType in petTypes"
              :key="petType"
              :label="petType"
              :value="petType"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="价格"
          :label-width="formLabelWidth"
          class="pet-form-item"
          prop="petPrice"
        >
          <el-input
            v-model.number="newPetForm.petPrice"
            type="number"
            onkeypress="return (/[\d]/.test(String.fromCharCode(event.keyCode)))"
            max="5000"
            min="0"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="图片链接"
          :label-width="formLabelWidth"
          class="pet-form-item"
          prop="petImg"
        >
          <el-input
            v-model="newPetForm.petImg"
            placeholder="请输入你的宠物图片的URL"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="描述"
          :label-width="formLabelWidth"
          class="pet-form-item"
          style="margin-bottom: 0"
          prop="petIntro"
        >
          <el-input
            v-model="newPetForm.petIntro"
            placeholder="请输入你对该宠物的描述"
            maxlength="14"
            show-word-limit
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button type="primary" @click="submitNewPetForm('newPetForm')"
          >确认</el-button
        >
        <el-button @click="newPetDialogVisiable = false">取消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="修改宠物信息"
      :visible.sync="changePetInfoDialogVisiable"
      width="500px"
      center
    >
      <el-form
        :model="changePetInfoForm"
        :rules="petRules"
        ref="changePetInfoForm"
      >
        <el-form-item
          label="名称"
          :label-width="formLabelWidth"
          class="pet-form-item"
          prop="petName"
        >
          <el-input
            v-model="changePetInfoForm.petName"
            placeholder="请输入你的宠物的名字"
            maxlength="6"
            show-word-limit
          ></el-input>
        </el-form-item>
        <el-form-item
          label="类型"
          :label-width="formLabelWidth"
          class="pet-form-item"
          prop="petType"
        >
          <el-select v-model="changePetInfoForm.petType" placeholder="请选择">
            <el-option
              v-for="petType in petTypes"
              :key="petType"
              :label="petType"
              :value="petType"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="价格"
          :label-width="formLabelWidth"
          class="pet-form-item"
          prop="petPrice"
        >
          <el-input
            v-model.number="changePetInfoForm.petPrice"
            type="number"
            onkeypress="return (/[\d]/.test(String.fromCharCode(event.keyCode)))"
            max="5000"
            min="0"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="图片链接"
          :label-width="formLabelWidth"
          class="pet-form-item"
          prop="petImg"
        >
          <el-input
            v-model="changePetInfoForm.petImg"
            placeholder="请输入你的宠物图片的URL"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="描述"
          :label-width="formLabelWidth"
          class="pet-form-item"
          style="margin-bottom: 0"
          prop="petIntro"
        >
          <el-input
            v-model="changePetInfoForm.petIntro"
            placeholder="请输入你对该宠物的描述"
            maxlength="14"
            show-word-limit
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button
          type="primary"
          @click="submintChangePetInfo('changePetInfoForm')"
          >确认</el-button
        >
        <el-button @click="changePetInfoDialogVisiable = false">取消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      :visible.sync="changePetStatusDialogVisiable"
      width="300px"
      center
    >
      <span>
        是否确认
        <span v-if="changePetStatusForm.status === 0">上架</span>
        <span v-else>下架</span>？
      </span>
      <span slot="footer">
        <el-button type="primary" @click="submitChangePetStatus()"
          >确认</el-button
        >
        <el-button @click="changePetStatusDialogVisiable = false"
          >取消</el-button
        >
      </span>
    </el-dialog>
    <el-dialog
      title="申请退款"
      :visible.sync="returnPetDialogVisiable"
      width="500px"
      center
    >
      <el-form
        :model="returnPetForm"
        label-width="80px"
        ref="returnPetForm"
        :rules="orderRules"
      >
        <el-form-item label="退款理由" prop="reason">
          <el-input
            v-model="returnPetForm.reason"
            placeholder="请输退款理由"
            maxlength="15"
            show-word-limit
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button type="primary" @click="submitReturnPet('returnPetForm')"
          >确认</el-button
        >
        <el-button @click="returnPetDialogVisiable = false">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { setTimeout } from "timers";
export default {
  name: "UserInfo",
  data() {
    var validatePrice = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入宠物的价格"));
      } else {
        if (value < 0 || value > 5000) {
          callback(new Error("请输入0 ~ 5000之间的数字"));
        } else {
          callback();
        }
      }
    };
    return {
      newPetDialogVisiable: false,
      changePetInfoDialogVisiable: false,
      changePetStatusDialogVisiable: false,
      returnPetDialogVisiable: false,
      canCreateNewPet: true,
      address: "",
      balance: 0,
      balancePercentage: 0,
      balanceStatus: "",
      petCount: 0,
      petCountPercentage: 0,
      petCountStatus: "",
      petList: null,
      orderList: null,
      newPetForm: {
        petType: "",
        petPrice: 2500,
        petName: "",
        petImg: "",
        petIntro: ""
      },
      changePetInfoForm: {
        petId: null,
        petType: "",
        petPrice: 0,
        petName: "",
        petImg: "",
        petIntro: ""
      },
      changePetStatusForm: {
        id: null,
        status: null
      },
      returnPetForm: {
        orderId: null,
        reason: ""
      },
      formLabelWidth: "80px",
      petTypes: ["猫", "狗", "兔子", "恐龙", "鸟"],
      petRules: {
        petName: [
          { required: true, message: "请输入宠物的名称", trigger: "blur" }
        ],
        petType: [
          { required: true, message: "请选择宠物的类型", trigger: "blur" }
        ],
        petPrice: [
          { required: true, validator: validatePrice, trigger: "blur" }
        ],
        petImg: [
          { required: true, message: "请输入宠物图片的链接", trigger: "blur" }
        ],
        petIntro: [
          { required: true, message: "请输入对宠物的描述", trigger: "blur" }
        ]
      },
      orderRules: {
        reason: [
          { required: true, message: "请输入退款的理由", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    myBalance() {
      return "" + this.balance;
    },
    myPetCount() {
      return "" + this.petCount;
    },
    submitNewPetForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios
            .post(this.$axios.baseURL + "/api/user/pet/createpet", {
              key: sessionStorage.getItem("privateKey"),
              pet: this.newPetForm
            })
            .then(response => {
              if (response.data.checked == true) {
                this.newPetDialogVisiable = false;
                this.$message({
                  message: "新建宠物成功！",
                  type: "success"
                });
                this.$refs[formName].resetFields();
                // 重新获取宠物列表
                setTimeout(this.getUserPetList(), 1000);
              } else {
                this.newPetDialogVisiable = false;
                this.$message({
                  message: "新建宠物失败！",
                  type: "error"
                });
                this.$refs[formName].resetFields();
              }
            })
            .catch();
        } else {
          window.console.log("表单验证失败！");
          return false;
        }
      });
    },
    changePetInfo(pet) {
      // 显示修改前的信息
      this.changePetInfoForm.petId = pet.petId;
      this.changePetInfoForm.petType = pet.petType;
      this.changePetInfoForm.petPrice = pet.petPrice;
      this.changePetInfoForm.petName = pet.petName;
      this.changePetInfoForm.petImg = pet.petImg;
      this.changePetInfoForm.petIntro = pet.petIntro;

      // 显示修改信息的对话框
      this.changePetInfoDialogVisiable = true;
    },
    submintChangePetInfo(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios
            .post(this.$axios.baseURL + "/api/user/pet/changeinfo", {
              key: sessionStorage.getItem("privateKey"),
              pet: this.changePetInfoForm
            })
            .then(response => {
              if (response.data.checked === true) {
                this.changePetInfoDialogVisiable = false;
                this.$message({
                  message: "修改成功！",
                  type: "success"
                });
                // 重置为初始化状态
                this.$refs[formName].resetFields();
                // 重新获取宠物列表
                setTimeout(this.getUserPetList(), 1000);
                setTimeout(this.getCanCreateNewPet(), 1000);
              } else {
                this.changePetInfoDialogVisiable = false;
                this.$message({
                  message: "修改失败！",
                  type: "error"
                });
                // 重置为初始化状态
                this.$refs[formName].resetFields();
              }
            })
            .catch(error => {
              window.console.log(error);
              this.$message({
                message: "修改失败！请重试。",
                type: "warning"
              });
            });
        } else {
          window.console.log("表单检测不通过！");
          return false;
        }
      });
    },
    changePetStatus(id, status) {
      this.changePetStatusForm.id = id;
      this.changePetStatusForm.status = status;

      // 显示二次确认的对话框
      this.changePetStatusDialogVisiable = true;
    },
    submitChangePetStatus() {
      let url;
      let move;
      if (this.changePetStatusForm.status === 0) {
        url = "/api/user/pet/sell";
        move = "上架";
      } else {
        url = "/api/user/pet/unsell";
        move = "下架";
      }

      this.$axios
        .post(this.$axios.baseURL + url, {
          key: sessionStorage.getItem("privateKey"),
          petId: this.changePetStatusForm.id
        })
        .then(response => {
          // 成功下架or上架
          if (response.data.checked == true) {
            // 修改状态
            setTimeout(() => {
              for (let pet of this.petList) {
                if (pet.petId === this.changePetStatusForm.id) {
                  pet.petStatus = pet.petStatus ? 0 : 1;
                  break;
                }
              }
            }, 0);
            // 关闭二次确认窗口
            this.changePetStatusDialogVisiable = false;
            // 发生成功消息
            this.$message({
              message: move + "成功！",
              type: "success"
            });
          } else {
            this.changePetStatusDialogVisiable = false;
            this.$message({
              message: move + "失败！",
              type: "error"
            });
          }
        })
        .catch(error => {
          window.console.log(error);
          this.$message({
            message: move + "失败！请重试。",
            type: "warning"
          });
        });
    },
    // 准备退货所需的数据
    handleOrderClick(id) {
      this.returnPetForm.orderId = id;

      this.returnPetDialogVisiable = true;
    },
    // 处理退货表单
    submitReturnPet(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios
            .post(this.$axios.baseURL + "/api/user/return", {
              key: sessionStorage.getItem("privateKey"),
              orderId: this.returnPetForm.orderId,
              reason: this.returnPetForm.reason
            })
            .then(response => {
              if (response.data.checked == true) {
                setTimeout(() => {
                  for (let order in this.orderList) {
                    if (order.orderId === this.returnPetForm.orderId) {
                      order.petStatus = 1;
                      break;
                    }
                  }
                }, 0);
                this.returnPetDialogVisiable = false;
                this.$message({
                  message: "申请退款成功！",
                  type: "success"
                });
                this.$refs[formName].resetFields();
              } else {
                this.returnPetDialogVisiable = false;
                this.$message({
                  message: "申请退款失败！",
                  type: "error"
                });
                this.$refs[formName].resetFields();
              }
            })
            .catch(error => {
              window.console.log(error);
              this.$message({
                message: "申请退款失败！请重试。",
                type: "warning"
              });
            });
        } else {
          window.console.log("表单检验失败！");
          return false;
        }
      });
    },
    // 获取余额进度条最大值
    getBalanceLimit() {
      if (this.balance === 0) {
        return 10000;
      } else {
        return Math.ceil(this.balance / 5000) * 5000;
      }
    },
    // 获取宠物数量进度条最大值
    getPetCountLimit() {
      if (this.petCount === 0) {
        return 10;
      } else {
        return Math.ceil(this.petCount / 10) * 10;
      }
    },
    getUserPetList() {
      this.$axios
        .post(this.$axios.baseURL + "/api/user/pet/petslist", {
          key: sessionStorage.getItem("privateKey")
        })
        .then(response => {
          this.petList = response.data.petsList;
          this.petCount = this.petList.length;
          // 设置进度条的百分比
          this.petCountPercentage =
            this.petCount / this.getPetCountLimit() > 1
              ? 100
              : (this.petCount / this.getPetCountLimit()) * 100;
          this.$message({
            message: "获取宠物列表成功！",
            type: "success"
          });
        })
        .catch(error => {
          window.console.log(error);
          this.$message({
            message: "获取宠物列表失败！",
            type: "error"
          });
        });
    },
    getCanCreateNewPet() {
      this.$axios
        .post(this.$axios.baseURL + "/api/user/ifcreated", {
          key: sessionStorage.getItem("privateKey")
        })
        .then(response => {
          this.canCreateNewPet = response.data.checked;
        })
        .catch(error => {
          window.console.log(error);
          this.$message({
            message: "获取是否已创建过宠物失败！"
          });
        });
    },
    handleOrderStatus(status) {
      switch (status) {
        case 0:
          return "primary";
        case 1:
          return "warning";
        case 2:
          return "success";
        case 3:
          return "danger";
        default:
          return "";
      }
    },
    isOrderButtonDisabled(status, buyer) {
      if (status === 0 && buyer == sessionStorage.getItem("address")) {
        return false;
      } else {
        return true;
      }
    },
    getOrderButtonText(status, buyer) {
      switch (status) {
        case 0:
          if (buyer == sessionStorage.getItem("address")) {
            return "申请退货";
          } else {
            return "无法申请";
          }
        case 1:
          return "正在退货";
        case 2:
          return "退货成功";
        case 3:
          return "退货失败";
        default:
          return "未知状态";
      }
    }
  },
  created() {
    // 读取地址信息
    this.address = sessionStorage.getItem("address");
    // 获取账户余额
    this.$axios
      .post(this.$axios.baseURL + "/api/user/balance", {
        key: sessionStorage.getItem("privateKey")
      })
      .then(response => {
        this.balance = response.data.balance;
        // 设置进度条的百分比
        this.balancePercentage =
          this.balance / this.getBalanceLimit() > 1
            ? 100
            : (this.balance / this.getBalanceLimit()) * 100;
        this.$message({
          message: "获取账户余额成功！",
          type: "success"
        });
      })
      .catch(error => {
        window.console.log(error);
        this.$message({
          message: "获取账户余额失败！",
          type: "error"
        });
      });

    // 获取是否已创建过宠物
    this.getCanCreateNewPet();

    // 获取账号宠物列表
    this.getUserPetList();

    // 获取订单列表
    this.$axios
      .post(this.$axios.baseURL + "/api/user/order/check", {
        key: sessionStorage.getItem("privateKey")
      })
      .then(response => {
        this.orderList = response.data.orderList;
        this.$message({
          message: "获取订单列表成功！",
          type: "success"
        });
      })
      .catch(error => {
        window.console.log(error);
        this.$message({
          message: "获取订单列表失败！",
          type: "error"
        });
      });
  },
  computed: {}
};
</script>

<style lang="less" scoped>
@pet-img-width: 240px;
@pet-img-height: @pet-img-width;
.user-info-card {
  width: 600px;
  margin-top: 50px;
  .el-row-self {
    margin-bottom: 20px;
  }
}
.pets-card {
  margin: 100px 0;
  padding-bottom: 30px;
  width: 900px;
}
.pet-card {
  width: @pet-img-width;
  float: left;
  margin: 20px;
}
.pet-info {
  padding: 14px;
  height: 120px;
}
.pet-img {
  width: @pet-img-width;
  height: @pet-img-height;
  display: block;
}
.pet-info div {
  font-size: 15px;
  margin: 5px 0;
}
.new-pet {
  display: flex;
  height: 300px;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}
.new-pet-icon {
  transform: scale(4);
  margin-bottom: 30px;
}
.new-pet-text {
  font-size: 20px;
  font-weight: 600;
  user-select: none;
}
.pet-form-item {
  margin-right: 30px;
}
.pet-buttons {
  margin: 0 20px 10px;
}
.pet-button-left {
  width: 90px;
}
.pet-button-right {
  width: 90px;
  float: right;
}
.orders-card {
  width: 960px;
  margin-bottom: 100px;
}
</style>
