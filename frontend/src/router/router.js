import { createRouter, createWebHashHistory } from "vue-router";

import Login from "../components/Login.vue";
import Regist from "../components/Regist.vue";
import ShowSchedule from "../components/ShowSchedule.vue";
import pinia from "../pinia.js";
import { defineUser } from "../store/userStore.js";

// 导入 user pinia数据
let sysUser = defineUser(pinia);

// 路由配置
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

// 设置路由守卫
router.beforeEach((to, from, next) => {
  // 登录保护
  if (to.path == "/showSchedule") {
    // 判断是否登录，sysUser.username初始化为空
    if (sysUser.username == "") next("/login");
    else next();
  } else {
    next();
  }
});

// 导出路由
export default router;
