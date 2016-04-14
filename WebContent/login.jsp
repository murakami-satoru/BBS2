<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板システムログイン画面</title>
</head>
<body>

	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${ errorMessages }" var="message">
					<li><c:out value="${ message }"/></li>
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>
	<form action="login" method="post">
		<table>
			<tr>
				<td align="right">ログインID:</td>
				<td>
					<input type="text" name="login_id"  size="20" maxlength="20">
				</td>
			</tr>
			<tr>
				<td align="right">パスワード:</td>
				<td>
					<input type="password" name="password"  size="20" maxlength="255">
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="ログイン"></td>
			</tr>
		</table>
	</form>
</body>
</html>