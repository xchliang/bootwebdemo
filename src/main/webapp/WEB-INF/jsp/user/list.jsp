<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>user list</title>
</head>
<body>
	<br/>
	<h1>用户列表</h1>
	<br/><br/>
	<div class="with:80%">
	    <table>
	        <thead>
	        <tr>
	            <th>#</th>
	            <th>User Name</th>
	            <th>email</th>
	            <th>Delete</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${users }" var="user">
		        <tr>
		            <th scope="row">${user.id}</th>
		            <td>${user.user_name}</td>
		            <td>${user.email}</td>
		            <td><a href="delete?userId=${user.id}">delete</a></td>
		        </tr>
	        </c:forEach>
	        </tbody>
	    </table>
	</div>
</body>
</html>