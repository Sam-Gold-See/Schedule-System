import { createRouter, createWebHashHistory } from "vue-router";

import Login from "../components/Login.vue";
import Regist from "../components/Regist.vue";
import ShowSchedule from "../components/ShowSchedule.vue";

import pinia from "../pinia.js";

import { defineUser } from "../store/userStore.js";

let sysUser = defineUser(pinia);

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/",
      redirect: "/showSchedule",
    },
    {
      path: "/login",
      component: Login,
    },
    {
      path: "/regist",
      component: Regist,
    },
    {
      path: "/showSchedule",
      component: ShowSchedule,
    },
  ],
});

router.beforeEach((to, from, next) => {
  if (to.path == "/showSchedule") {
    if (sysUser.username == "") next("/login");
    else next();
  } else {
    next();
  }
});

export default router;
