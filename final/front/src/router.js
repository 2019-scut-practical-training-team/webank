import Vue from "vue";
import Router from "vue-router";
import LoginPage from "./views/LoginPage";
import UserPage from "./views/UserPage";
import AdminPage from "./views/AdminPage";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [{
      path: "/",
      component: LoginPage
    },
    {
      path: "/user",
      component: UserPage,
      beforeEnter: (to, from, next) => {
        // 防止管理员跳转到用户页面
        if (sessionStorage.getItem("identity") == 2) {
          next("/admin");
        } else {
          next();
        }
      }
    },
    {
      path: "/admin",
      component: AdminPage,
      beforeEnter: (to, from, next) => {
        // 防止用户跳转到管理员页面
        if (sessionStorage.getItem("identity") == 1) {
          next("/user");
        } else {
          next();
        }
      }
    }
  ]
});