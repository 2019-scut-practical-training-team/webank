import Vue from "vue";
import App from "./App.vue";
import router from "./router";

import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

// 引入模块
import axios from "axios";

// 设置基础api
axios.default.baseURL = "http://110.64.72.19:8080";
// 绑定到Vue上
Vue.prototype.$axios = axios;

Vue.config.productionTip = true;
Vue.use(ElementUI);

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
