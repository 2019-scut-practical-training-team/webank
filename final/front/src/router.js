import Vue from "vue";
import Router from "vue-router";
import LoginPage from "./views/LoginPage";
import UserPage from "./views/UserPage";
import AdminPage from "./views/AdminPage";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      component: LoginPage
    },
    {
      path: "/user",
      component: UserPage
    },
    {
      path: "/admin",
      component: AdminPage
    }
  ]
});
