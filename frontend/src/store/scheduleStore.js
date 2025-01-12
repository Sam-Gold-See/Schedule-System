import { defineStore } from "pinia";

export const defineSyschedule = defineStore("schedule", {
  state: () => {
    return {
      itemList: [
        /* 
          开发测试用数据
        */
        {
          sid: 1,
          uid: 1,
          title: "学习Java",
          completed: 0,
        },
        {
          sid: 1,
          uid: 1,
          title: "学习Java",
          completed: 0,
        },
        {
          sid: 1,
          uid: 1,
          title: "学习Java",
          completed: 0,
        },
      ],
    };
  },
});
