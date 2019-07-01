<template>
  <div id="user-page">
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
            :status="balancePercentage === 100 ? 'success': null"
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
            :status="petCountPercentage === 100 ? 'success': null"
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
    <el-dialog title="创建新宠物" :visible.sync="newPetDialogVisiable" width="500px" center>
      <el-form :model="newPetForm" :rules="rules" ref="newPetForm">
        <el-form-item label="名称" :label-width="formLabelWidth" class="pet-form-item" prop="name">
          <el-input
            v-model="newPetForm.name"
            placeholder="请输入你的宠物的名字"
            maxlength="6"
            show-word-limit
          ></el-input>
        </el-form-item>
        <el-form-item label="类型" :label-width="formLabelWidth" class="pet-form-item" prop="type">
          <el-select v-model="newPetForm.type" placeholder="请选择">
            <el-option v-for="petType in petTypes" :key="petType" :label="petType" :value="petType"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="价格" :label-width="formLabelWidth" class="pet-form-item" prop="price">
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
          <el-input v-model="newPetForm.imgURL" placeholder="请输入你的宠物图片的URL"></el-input>
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
        <el-button type="primary" @click="submitNewPetForm('newPetForm')">确认</el-button>
        <el-button @click="newPetDialogVisiable = false">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
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
      address: "0x123456789abcdefghi",
      balance: 5000 ,
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
          petImg:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561804170123&di=1ddf8a5e1d4345013ee0cd36ad3a1ba9&imgtype=0&src=http%3A%2F%2Fuploads.oh100.com%2Fallimg%2F1709%2F132-1FZ2121051.jpg",
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
      newPetForm: {
        name: "",
        type: "",
        price: 2500,
        imgURL: "",
        intro: ""
      },
      formLabelWidth: "80px",
      petTypes: ["猫", "狗", "兔子", "恐龙", "鸟"],
      rules: {
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
            message: "提交成功",
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
    }
  },
  created() {
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
</style>
