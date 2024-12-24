package com.atguigu.schedule.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysSchedule implements Serializable {
	private Integer sid;
	private Integer uid;
	private String title;
	private Integer completed;
}
