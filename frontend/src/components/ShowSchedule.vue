<script setup>
import { defineUser } from "../store/userStore";
import { defineSchedule } from "../store/scheduleStore";

let sysUser = defineUser();
let schedule = defineSchedule();

import { onMounted } from "vue";
import request from "../utils/request";

// 页面加载完成后，获取日程列表
onMounted(() => {
  showSchedule();
});

// 展示日程列表（可充当刷新效果）
async function showSchedule() {
  let { data } = await request.get("schedule/findAllSchedule", {
    params: { uid: sysUser.uid },
  });
  schedule.itemList = data.data.itemList;
}

// 添加日程
async function addItem() {
  let { data } = await request.get("schedule/addDefaultSchedule", {
    params: { uid: sysUser.uid },
  });
  if (data.code == 200) {
    showSchedule();
  } else {
    alert("添加日程失败");
  }
}

// 修改日程并且保存
async function updateItem(index) {
  let { data } = await request.post(
    "schedule/updateSchedule",
    schedule.itemList[index]
  );

  if (data.code == 200) {
    showSchedule();
  } else {
    alert("修改日程失败");
  }
}

// 删除日程
async function removeItem(index) {
  let sid = schedule.itemList[index].sid;
  let { data } = await request.get("schedule/removeSchedule", {
    params: { sid: sid },
  });
  if (data.code == 200) {
    showSchedule();
  } else {
    alert("删除日程失败");
  }
}
</script>

<template>
  <div>
    <h3 class="ht">您的日程如下</h3>
    <table class="tab" cellspacing="0px">
      <thead>
        <tr class="ltr">
          <th>编号</th>
          <th>内容</th>
          <th>进度</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr class="ltr" v-for="(item, index) in schedule.itemList" :key="index">
          <td v-text="index + 1"></td>
          <td>
            <input type="text" v-model="item.title" />
          </td>
          <td>
            <input type="radio" value="1" v-model="item.completed" />已完成
            <input type="radio" value="0" v-model="item.completed" />未完成
          </td>
          <td class="buttonContainer">
            <button class="btn1" @click="removeItem(index)">删除</button>
            <button class="btn1" @click="updateItem(index)">保存修改</button>
          </td>
        </tr>
        <tr class="ltrButtonContainer">
          <td colspan="4">
            <button class="btn1" @click="addItem()">新增日程</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.ht {
  text-align: center;
  color: cadetblue;
  font-family: 幼圆;
}
.tab {
  width: 80%;
  border: 5px solid cadetblue;
  margin: 0px auto;
  border-radius: 5px;
  font-family: 幼圆;
}
.ltr td {
  border: 1px solid powderblue;
}
.ipt {
  border: 0px;
  width: 50%;
}
.btn1 {
  border: 2px solid powderblue;
  border-radius: 4px;
  width: 100px;
  background-color: antiquewhite;
}
#usernameMsg,
#userPwdMsg {
  color: gold;
}

.buttonContainer {
  text-align: center;
}
</style>
