<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table width="500" border="1">
		<tr>
			<td>���̵�</td>
			<td>�̸�</td>
			<td>����</td>
			<td>��¥</td>
			<td>��Ʈ</td>
		</tr>
		
		<c:forEach var="dto" items="${list}">
		
			<tr>
				<td>${dto.bid}</td>
				<td>${dto.bname}</td>
				
				<td>
					<c:forEach begin="1" end="${dto.bindent}">[Re]</c:forEach>
					<a href="content_view.do?bid=${dto.bid}">${dto.btitle}</a>	
				</td>
				<td>${dto.bdate}</td>
				<td>${dto.bhit}</td>
			</tr>
		
		
		</c:forEach>
	
	
	</table>

</body>
</html>