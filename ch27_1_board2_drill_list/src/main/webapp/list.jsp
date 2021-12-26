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
<title>list</title>
</head>
<body>
		<table width="500" border="1">
			<tr>
				<td>번호:</td>
				<td>이름:</td>
				<td>제목:</td>
				<td>날짜:</td>
				<td>히트:</td>
			
			</tr>
			
			
			<!-- 여기 또 핵심 3 -->
			<c:forEach var="dto" items="${list}">
			<tr>
			
				<td>${dto.bid}</td>
				<td>${dto.bname}</td>
				<td>
					<c:forEach begin="1" end="${dto.bindent}"> [Re] </c:forEach>
 						<a href="content_view.do?bid=${dto.bid}">${dto.btitle}</a>
				</td>
				
				
				<td>${dto.bdate}</td>
				<td>${dto.bhit}</td>
				
			</tr>	
			</c:forEach>
			
			<tr>
				<td colspan="5"> 
					<a href="write_view.do">글작성</a>
				</td>
			</tr>
		
		</table>
		
		
</body>
</html>