<script setup>
import { ref, reactive } from "vue";
import request from "../utils/request";
import { useRouter } from "vue-router";

const router = useRouter();

let registUser = reactive({
  username: "",
  userPwd: "",
});

let usernameMsg = ref("");
let userPwdMsg = ref("");
let reUserPwd = ref("");
let reUserPwdMsg = ref("");

// 校验用户名是否可用
async function checkUsername() {
  let usernameReg = /^[a-zA-Z0-9]{5,10}$/;
  if (!usernameReg.test(registUser.username)) {
    usernameMsg.value = "格式有误";
    return false;
  }
  // 发送请求
  let { data } = await request.post(
    `user/checkUsernameUsed?username=${registUser.username}`
  );
  // 处理响应数据
  if (data.code != 200) {
    usernameMsg.value = "用户名占用";
    return false;
  }
  usernameMsg.value = "可用";
  return true;
}

// 校验密码格式
function checkUserPwd() {
  let userPwdReg = /^[0-9]{6}$/;
  if (!userPwdReg.test(registUser.userPwd)) {
    userPwdMsg.value = "格式有误";
    return false;
  }
  userPwdMsg.value = "OK";
  return true;
}

// 校验确认密码
function checkReUserPwd() {
  let userPwdReg = /^[0-9]{6}$/;
  if (!userPwdReg.test(reUserPwd.value)) {
    reUserPwdMsg.value = "格式有误";
    return false;
  }
  if (registUser.userPwd != reUserPwd.value) {
    reUserPwdMsg.value = "两次密码不一致";
    return false;
  }
  reUserPwdMsg.value = "OK";
  return true;
}

// 注册
async function regist() {
  // 校验所有的输入框
  let flag1 = await checkUsername();
  let flag2 = await checkUserPwd();
  let flag3 = await checkReUserPwd();
  if (flag1 && flag2 && flag3) {
    // 发送请求
    let { data } = await request.post("user/regist", registUser);
    if (data.code == 200) {
      alert("注册成功，请登录");
      router.push("/login");
    } else {
      alert("抱歉，用户名被抢注");
    }
  } else {
    alert("校验不通过，请求再次检查数据");
  }
}
</script>

<template>
  <div>
    <h3 class="ht">请注册</h3>

    <table class="tab" cellspacing="0px">
      <tbody>
        <tr class="ltr">
          <td>请输入账号</td>
          <td>
            <input
              class="ipt"
              id="usernameInput"
              type="text"
              name="username"
              v-model="registUser.username"
              @blur="checkUsername()"
            />

            <span id="usernameMsg" class="msg" v-text="usernameMsg"></span>
          </td>
        </tr>
        <tr class="ltr">
          <td>请输入密码</td>
          <td>
            <input
              class="ipt"
              id="userPwdInput"
              type="password"
              name="userPwd"
              v-model="registUser.userPwd"
              @blur="checkUserPwd()"
            />
            <span id="userPwdMsg" class="msg" v-text="userPwdMsg"></span>
          </td>
        </tr>
        <tr class="ltr">
          <td>确认密码</td>
          <td>
            <input
              class="ipt"
              id="reUserPwdInput"
              type="password"
              v-model="reUserPwd"
              @blur="checkReUserPwd()"
            />
            <span id="reUserPwdMsg" class="msg" v-text="reUserPwdMsg"></span>
          </td>
        </tr>
        <tr class="ltr">
          <td colspan="2" class="buttonContainer">
            <input class="btn1" type="button" @click="regist()" value="注册" />
            <input class="btn1" type="button" value="重置" />
            <router-link to="/login">
              <button class="btn1">去登录</button>
            </router-link>
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
  width: 500px;
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
  width: 60px;
  background-color: antiquewhite;
}
.msg {
  color: gold;
}
.buttonContainer {
  text-align: center;
}
</style>
