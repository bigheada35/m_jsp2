<%@page import="edu.kosmo.ex.dto.BDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.kosmo.ex.dao.BDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%-- 주의  주의  <% 는 왼쪽에 꼭 붙여서 쓰기 --%>
 <!-- 주의  이것 넣고, lib도 넣어야 함. 꼭 --> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	BDao bdao = new BDao();
	ArrayList<BDto> dtos = bdao.list();
	
	for(BDto dto : dtos){
		out.print(" 번호  " + dto.getBid() + 
				",제목  " + dto.getBtitle() +"<br>"
				);
	}
	%>
</body>
</html>