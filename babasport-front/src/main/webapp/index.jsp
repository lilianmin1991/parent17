<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%response.sendRedirect("/product/display/list.shtml?keyword=" + URLEncoder.encode("瑜伽服", "UTF-8"));%>