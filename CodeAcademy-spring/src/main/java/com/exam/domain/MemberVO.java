package com.exam.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	private String id;
	private String passwd;
	private String name;
	private Date regDate;
	private String email;
	private String tel;
	private String gender;
}
