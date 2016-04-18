<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー編集画面</title>
</head>
<body>
	<h2>
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
	</h2>
	<form action="updateUser" method="post">
		<table>
			<tr>
				<td align="right">ログインID:</td>
				<td>
					<input type="text" name="login_id"  size="20" maxlength="20"
					value="<c:out value="${ user.loginId }"/>">
					<c:forEach items="${ violationMessages['_loginId'] }" var="message">
						<c:out value="${ message }"/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td align="right">パスワード:</td>
				<td>
					<input type="password" name="password"  size="20" maxlength="255">
					<c:forEach items="${ violationMessages['_password'] }" var="message">
						<c:out value="${ message }"/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td align="right">確認用パスワード:</td>
				<td>
					<input type="password" name="confirmation_password"  size="20" maxlength="255">
					<c:forEach items="${ violationMessages['_confirmationPassword'] }" var="message">
						<c:out value="${ message }"/>
					</c:forEach>
					<c:forEach items="${ messages }" var="message">
						<c:out value="${ message }"/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td align="right">名称:</td>
				<td>
					<input type="text" name="name"  size="10" maxlength="10"
					value="<c:out value="${ user.name }"/>">
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
							<option value="${ branch.id }" <c:if test="${ branch.id == user.branchId  }">selected</c:if> >
								<c:out value="${ branch.name }" />
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">役職:</td>
				<td>
					<select name="department">
						<c:forEach items="${ departments }" var="department">
							<option value="${ department.id }" <c:if test="${ department.id == user.departmentId  }">selected</c:if> >
								<c:out value="${ department.name }" />
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" name="id" value="<c:out value="${ user.id }"/>">
					<input type="submit" value="更新">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>