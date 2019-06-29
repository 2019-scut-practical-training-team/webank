import Vue from "vue";
import Router from "vue-router";
import LoginPage from "./views/LoginPage";
import UserPage from "./views/UserPage";

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
    }
  ]
});
