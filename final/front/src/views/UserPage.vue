<template>
  <div class="user-page">
    <el-menu
      :default-active="activeIndex"
      mode="horizontal"
      background-color="#409EFF"
      text-color="#fff"
      active-text-color="#303133"
      class="el-menu--horizontal-self"
      @select="headleSelect"
    >
      <el-menu-item index="1" class="el-menu-item-self">宠物市场</el-menu-item>
      <el-menu-item index="0" class="el-menu-item-self">个人信息</el-menu-item>
    </el-menu>
    <component :is="activeComponent" class="user-container"></component>
  </div>
</template>

<script>
import UserInfo from "@/components/UserInfo";
import UserMarket from "@/components/UserMarket";
export default {
  name: "UserPage",
  data() {
    return {
      activeIndex: "0"
    };
  },
  components: {
    UserInfo,
    UserMarket
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
          return "user-info";
        }
        case "1": {
          return "user-market";
        }
      }
      // 默认返回
      return "user-info";
    }
  }
};
</script>

<style scoped>
.user-page {
  min-width: 768px;
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
  flex-direction: column;
  align-items: center;
}
</style>
