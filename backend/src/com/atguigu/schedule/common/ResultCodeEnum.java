package com.atguigu.schedule.common;

/**
 * 业务含义和状态码对应关系的枚举
 */

public enum ResultCodeEnum {
	// 正常
	SUCCESS(200, "success"),
 	// 用户名错误
	USERNAME_ERROR(501, "usernameError"),
 	// 密码错误
	PASSWORD_ERROR(503, "passwordError"),
  	// 账号未登录
	NOTLOGIN(504, "notLogin"),
	// 用户名已占用
	USERNAME_USED(505, "usernameUsed");

	private final Integer code;
	private final String message;

	ResultCodeEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
