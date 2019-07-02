<template>
  <div>
    <el-card class="user-info-card">
      <div slot="header">
        <span>用户信息</span>
      </div>
      <el-row class="el-row-self">
        <el-col :span="4">账户地址：</el-col>
        <el-col :span="20">{{ address }}</el-col>
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
      <div class="pet-card">
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
              :disabled="scope.row.orderStatus === 0 ? false : true"
            >
              <span v-if="scope.row.orderStatus === 0">申请退货</span>
              <span v-else-if="scope.row.orderStatus === 1">正在退货</span>
              <span v-else-if="scope.row.orderStatus === 2">退货成功</span>
              <span v-else-if="scope.row.orderStatus === 3">退货失败</span>
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
          prop="name"
        >
          <el-input
            v-model="newPetForm.name"
            placeholder="请输入你的宠物的名字"
            maxlength="6"
            show-word-limit
          ></el-input>
        </el-form-item>
        <el-form-item
          label="类型"
          :label-width="formLabelWidth"
          class="pet-form-item"
          prop="type"
        >
          <el-select v-model="newPetForm.type" placeholder="请选择">
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
          prop="price"
        >
          <el-input
            v-model.number="newPetForm.price"
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
          prop="imgURL"
        >
          <el-input
            v-model="newPetForm.imgURL"
            placeholder="请输入你的宠物图片的URL"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="描述"
          :label-width="formLabelWidth"
          class="pet-form-item"
          style="margin-bottom: 0"
          prop="intro"
        >
          <el-input
            v-model="newPetForm.intro"
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
          prop="name"
        >
          <el-input
            v-model="changePetInfoForm.name"
            placeholder="请输入你的宠物的名字"
            maxlength="6"
            show-word-limit
          ></el-input>
        </el-form-item>
        <el-form-item
          label="类型"
          :label-width="formLabelWidth"
          class="pet-form-item"
          prop="type"
        >
          <el-select v-model="changePetInfoForm.type" placeholder="请选择">
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
          prop="price"
        >
          <el-input
            v-model.number="changePetInfoForm.price"
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
          prop="imgURL"
        >
          <el-input
            v-model="changePetInfoForm.imgURL"
            placeholder="请输入你的宠物图片的URL"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="描述"
          :label-width="formLabelWidth"
          class="pet-form-item"
          style="margin-bottom: 0"
          prop="intro"
        >
          <el-input
            v-model="changePetInfoForm.intro"
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
      address: "",
      balance: 5000,
      balancePercentage: 0,
      balanceStatus: "",
      petCount: 10,
      petCountPercentage: 0,
      petCountStatus: "",
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
      ],
      orderList: [
        {
          orderId: 1,
          orderBuyer: "0x35aa03c98231a21a2c424c1d2bdd88ae654a44a6",
          orderSeller: "0x35aa03c98231a21a2c424c1d2bdd88ae654a44a6",
          orderTime: "Tue Jul 02 10:25:10 CST 2019",
          petId: 1,
          petPrice: 2000,
          orderStatus: 0
        },
        {
          orderId: 2,
          orderBuyer: "0x123",
          orderSeller: "0x456",
          orderTime: "Tue Jul 02 10:25:10 CST 2019",
          petId: 2,
          petPrice: 3000,
          orderStatus: 1
        },
        {
          orderId: 3,
          orderBuyer: "0x123",
          orderSeller: "0x456",
          orderTime: "Tue Jul 02 10:25:10 CST 2019",
          petId: 1,
          petPrice: 2000,
          orderStatus: 2
        },
        {
          orderId: 4,
          orderBuyer: "0x123",
          orderSeller: "0x456",
          orderTime: "Tue Jul 02 10:25:10 CST 2019",
          petId: 2,
          petPrice: 3000,
          orderStatus: 3
        },
        {
          orderId: 5,
          orderBuyer: "0x123",
          orderSeller: "0x456",
          orderTime: "Tue Jul 02 10:25:10 CST 2019",
          petId: 1,
          petPrice: 2000,
          orderStatus: 0
        },
        {
          orderId: 6,
          orderBuyer: "0x123",
          orderSeller: "0x456",
          orderTime: "Tue Jul 02 10:25:10 CST 2019",
          petId: 2,
          petPrice: 3000,
          orderStatus: 0
        }
      ],
      newPetForm: {
        name: "",
        type: "",
        price: 2500,
        imgURL: "",
        intro: ""
      },
      changePetInfoForm: {
        id: null,
        name: "",
        type: "",
        price: 0,
        imgURL: "",
        intro: ""
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
        name: [
          { required: true, message: "请输入宠物的名称", trigger: "blur" }
        ],
        type: [
          { required: true, message: "请选择宠物的类型", trigger: "blur" }
        ],
        price: [{ required: true, validator: validatePrice, trigger: "blur" }],
        imgURL: [
          { required: true, message: "请输入宠物图片的链接", trigger: "blur" }
        ],
        intro: [
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
          this.$message({
            message: "新建成功",
            type: "success"
          });
          // 关闭窗口
          this.newPetDialogVisiable = false;
          // 重置为初始化状态
          this.$refs[formName].resetFields();
        } else {
          window.console.log("error submit");
          return false;
        }
      });
    },
    changePetInfo(pet) {
      // 显示修改前的信息
      this.changePetInfoForm.id = pet.petId;
      this.changePetInfoForm.name = pet.petName;
      this.changePetInfoForm.type = pet.petType;
      this.changePetInfoForm.price = pet.petPrice;
      this.changePetInfoForm.imgURL = pet.petImg;
      this.changePetInfoForm.intro = pet.petIntro;

      // 显示修改信息的对话框
      this.changePetInfoDialogVisiable = true;
    },
    submintChangePetInfo(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          // 关闭窗口
          this.changePetInfoDialogVisiable = false;

          this.$message({
            message: "修改成功",
            type: "success"
          });
          // 重置为初始化状态
          this.$refs[formName].resetFields();
        } else {
          window.console.log("error submit");
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
      setTimeout(() => {
        for (let pet of this.petList) {
          if (pet.petId === this.changePetStatusForm.id) {
            pet.petStatus = pet.petStatus ? 0 : 1;
            break;
          }
        }
      }, 0);
      this.changePetStatusDialogVisiable = false;
      this.$message({
        message:
          this.changePetStatusForm.status === 0 ? "上架成功" : "下架成功",
        type: "success"
      });
    },
    handleOrderClick(id) {
      this.returnPetForm.orderId = id;

      this.returnPetDialogVisiable = true;
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
    submitReturnPet(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.returnPetDialogVisiable = false;
          this.$message({
            message: "申请成功",
            type: "success"
          });
          this.$refs[formName].resetFields();
        } else {
          window.console.log("error submit!");
          return false;
        }
      });
    }
  },
  created() {
    // 读取地址信息
    this.address = sessionStorage.getItem("address");

    this.balancePercentage =
      this.balance / 500 > 100 ? 100 : this.balance / 500;

    this.petCountPercentage =
      this.petCount / 0.2 > 100 ? 100 : this.petCount / 0.2;
  }
};
</script>

<style lang="less" scoped>
@pet-img-width: 240px;
@pet-img-height: @pet-img-width;
.user-info-card {
  width: 700px;
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
