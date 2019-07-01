<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      mode="horizontal"
      background-color="#409EFF"
      text-color="#fff"
      active-text-color="#303133"
      class="el-menu--horizontal-self"
      @select="headleSelect"
    >
      <span class="title">PetShop管理员</span>
      <el-menu-item index="2" class="el-menu-item-self">所有交易</el-menu-item>
      <el-menu-item index="1" class="el-menu-item-self">宠物市场</el-menu-item>
      <el-menu-item index="0" class="el-menu-item-self">退货信息</el-menu-item>
    </el-menu>
    <component :is="activeComponent" class="user-container"></component>
  </div>
</template>

<script>
import AdminMarket from "@/components/AdminMarket";
import AdminTran from "@/components/AdminTran";
import AdminReturnInfo from "@/components/AdminReturnInfo";
export default {
  name: "AdminPage",
  data() {
    return {
      activeIndex: "0"
    };
  },
  components: {
    AdminReturnInfo,
    AdminMarket,
    AdminTran
  },
  methods: {
    headleSelect(index) {
      this.activeIndex = index;
    }
  },
  computed: {
    activeComponent() {
      switch (this.activeIndex) {
        case "0": {
          return "admin-return-info";
        }
        case "1": {
          return "admin-market";
        }
        case "2": {
          return "admin-tran";
        }
      }
      // 默认返回
      return "admin-return-info";
    }
  }
};
</script>

<style scoped>
.title {
  font-size: 30px;
  color: white;
  margin-left: 50px;
  margin-top: 8px;
}

.el-menu--horizontal-self {
  padding-right: 100px;
}

.el-menu-item-self {
  float: right !important;
  font-size: 16px;
}
.user-container {
  display: flex;
  align-items: center;
}
</style>
