import { defineStore } from "pinia";

export const defineSchedule = defineStore("schedule", {
  state: () => {
    return {
      itemList: [],
    };
  },
});
