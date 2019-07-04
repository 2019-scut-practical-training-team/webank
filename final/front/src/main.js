import Vue from "vue";
import App from "./App.vue";
import router from "./router";

import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

// 引入模块
import axios from "axios";
axios.default.withCredentials = true;

// 设置基础api,设置为后台的IP
// axios.default.baseURL = "http://110.64.72.19:8080"; // 服务器1
axios.default.baseURL = "http://110.64.72.21:8080"; // 服务器2
// 绑定到Vue上
Vue.prototype.$axios = axios;

// 是否显示开发时的错误提示信息
Vue.config.productionTip = true;
// 是否激活开发者工具插件
Vue.config.devtools = true;

Vue.use(ElementUI);

// 如果没有登录的话强制跳转到首页
router.beforeEach((to, from, next) => {
  if (to.path == "/") {
    next();
  } else {
    if (sessionStorage.getItem("privateKey")) {
      next();
    } else {
      next("/");
    }
  }
});

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
