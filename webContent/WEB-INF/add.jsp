<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.dao.GuestBookDao" %>
<%@ page import="com.javaex.vo.GuestBookVo" %>

<%
	String name = request.getParameter("name");
	String password = request.getParameter("pass");
	String content = request.getParameter("content");
	
	GuestBookVo guestbookVo = new GuestBookVo(name, password, content);
	
	GuestBookDao guestbookDao = new GuestBookDao();
	guestbookDao.insert(guestbookVo);
	
	response.sendRedirect("./addList.jsp");
%>