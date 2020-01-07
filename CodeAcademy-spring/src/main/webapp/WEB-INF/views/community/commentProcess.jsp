<%-- <%@page import="com.exam.dao.CommentDao"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="CommentVO" class="com.exam.vo.CommentVO" />

<jsp:setProperty property="*" name="CommentVO" />

<% CommentVO.setRegDate(new Timestamp(System.currentTimeMillis())); %>

<% CommentVO.setIp(request.getRemoteAddr()); %>

<% CommentDao commentDao = CommentDao.getInstance(); %>

<%
int num = commentDao.nextCommentNum();

CommentVO.setNum(num);
CommentVO.setReRef(num);
CommentVO.setReLev(0);
CommentVO.setReSeq(0);

%>

<% commentDao.insertComment(CommentVO); %> --%>