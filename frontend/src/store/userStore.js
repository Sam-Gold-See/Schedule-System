import { createStore } from "pinia";

export const defineUser = defineStore("loginUser", {
  state: () => {
    return {
      uid: 0,
      username: "",
    };
  },
  getters: {},
  actions: {},
});
