package com.atguigu.schedule.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysUser implements Serializable {
	private Integer uid;
	private String username;
	private String userPwd;
}
