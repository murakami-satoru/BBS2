<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー新規登録画面</title>
</head>
<body>
	<form action="registerUser" method="post">
		<table>
			<tr>
				<td align="right">ログインID:</td>
				<td>
					<input type="text" name="login_id"  size="20" maxlength="20"
					value="<c:out value="${ inputUsers.loginId }"/>">
					<c:forEach items="${ violationMessages['_loginId'] }" var="message">
						<c:out value="${ message }"/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td align="right">パスワード:</td>
				<td>
					<input type="password" name="password"  size="20" maxlength="255"
					value="<c:out value="${ inputUsers.password }"/>">
					<c:forEach items="${ violationMessages['_password'] }" var="message">
						<c:out value="${ message }"/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td align="right">名称:</td>
				<td>
					<input type="text" name="name"  size="10" maxlength="10"
					value="<c:out value="${ inputUsers.name }"/>">
					<c:forEach items="${ violationMessages['_name'] }" var="message">
						<c:out value="${ message }"/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td align="right">部署:</td>
				<td>
					<select name="branch">
						<c:forEach items="${ branches }" var="branch">
							<option value="${ branch.id }"><c:out value="${ branch.name }" /></option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">役職:</td>
				<td>
					<select name="department">
						<c:forEach items="${ departments }" var="department">
							<option value="${ department.id }"><c:out value="${ department.name }" /></option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="登録"></td>
			</tr>
		</table>
	</form>
</body>
</html>