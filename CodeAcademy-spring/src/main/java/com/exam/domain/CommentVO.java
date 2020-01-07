package com.exam.domain;

import java.sql.Timestamp;

public class CommentVO {
	private int num;
	private String username;
	private String passwd;
	private String comments;
	private String ip;
	private Timestamp regDate;
	private int reRef;
	private int reLev;
	private int reSeq;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public int getReRef() {
		return reRef;
	}
	public void setReRef(int reRef) {
		this.reRef = reRef;
	}
	public int getReLev() {
		return reLev;
	}
	public void setReLev(int reLev) {
		this.reLev = reLev;
	}
	public int getReSeq() {
		return reSeq;
	}
	public void setReSeq(int reSeq) {
		this.reSeq = reSeq;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommentVO [num=").append(num).append(", username=").append(username).append(", passwd=")
				.append(passwd).append(", comments=").append(comments).append(", ip=").append(ip).append(", regDate=")
				.append(regDate).append(", reRef=").append(reRef).append(", reLev=").append(reLev).append(", reSeq=")
				.append(reSeq).append("]");
		return builder.toString();
	}
	
} //CommentVO
