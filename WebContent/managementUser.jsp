<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理画面</title>
</head>
<body>
	<table border=2 align="left">
		<tr>
			<td>メニュー</td>
		</tr>
		<tr>
			<td>
				・<a href="registerUser">ユーザー新規登録画面</a>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td>ユーザー一覧</td>
		</tr>
		<tr><td>
		<table border=2>
		<tr>
			<td>ログインID</td>
			<td>名称</td>
		</tr>
		 <c:forEach items="${ users }" var="user">
			<tr>
				<td><c:out value="${ user.loginId }"/></td>
				<td><c:out value="${ user.name }"/></td>
				<td>
					<form action="editUser" method="post">
						<input type="hidden" name="id" value="<c:out value="${ user.id }"/>">
						<input type="submit" value="ユーザー編集">
					</form>
				</td>
			</tr>
		</c:forEach>
		</table>
		</td></tr>
	</table>
</body>
</html>