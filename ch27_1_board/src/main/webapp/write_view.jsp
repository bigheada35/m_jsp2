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
			<form action="write.do" method="post">
				<tr>
					<td>이름</td>
					<td>
						<input type="text" name="bname" size="50">
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td> 
						<input type="text" name="btitle" size="50"> 
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="bcontent" rows="10"> </textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="입력">
						 &nbsp;&nbsp;
						 <a href="list.do">
						 목록보기
						 </a>
					</td>
				</tr>
			</form>
		
		</table>
		
		
</body>
</html>