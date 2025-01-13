import { defineStore } from "pinia";

// user数据的pinia定义模块
export const defineUser = defineStore("loginUser", {
  state: () => {
    return {
      uid: 0,
      username: "",
    };
  },
  getters: {},
  actions: {
    $reset() {
      this.uid = 0;
      this.username = "";
    },
  },
});
