import { defineStore } from "pinia";

// schedule 数据的pinia定义模块
export const defineSchedule = defineStore("schedule", {
  state: () => {
    return {
      itemList: [],
    };
  },
});
