import { createApp } from "vue";
// 导入App组件
import App from "./App.vue";
// 导入路由
import router from "./router/router";
// 导入pinia
import pinia from "./pinia.js";

const app = createApp(App);
// 挂载路由
app.use(router);
// 挂载pinia
app.use(pinia);
// 启动应用
app.mount("#app");
