<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.ddiv">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bbs.css">
<title>掲示板システム</title>
</head>
<body id="home">
	<div id="wrapper">
		<div id="header">
			<h1>ユーザー新規登録</h1>
			<div id="menu">
				<ul>
					<li><a href="home">ホーム</a></li>
				</ul>
				<div class="error">
					<c:if test="${ not empty errorMessages }">
						<c:forEach items="${ errorMessages }" var="message">
							<c:out value="${ message }"/>
						</c:forEach>
						<c:remove var="errorMessages" scope="session"/>
					</c:if>
				</div>
			</div>
		</div>
		<div id="form">
			<form action="registerUser" method="post">
				<div>
					<label>ログインID:</label>
					<input type="text" name="login_id"  size="20" maxlength="20"
					value="<c:out value="${ inputUsers.loginId }"/>">
					<c:forEach items="${ violationMessages['_loginId'] }" var="message">
						<div class="error"><c:out value="${ message }"/></div>
					</c:forEach>
				</div>
				<div>
					<label>パスワード:</label>
					<input type="password" name="password"  size="20" maxlength="255">
					<c:forEach items="${ violationMessages['_password'] }" var="message">
						<div class="error"><c:out value="${ message }"/></div>
					</c:forEach>
				</div>
				<div>
					<label>確認用パスワード:</label>
					<input type="password" name="confirmation_password"  size="20" maxlength="255">
					<c:forEach items="${ violationMessages['_confirmationPassword'] }" var="message">
						<div class="error"><c:out value="${ message }"/></div>
					</c:forEach>
					<c:forEach items="${ messages }" var="message">
						<div class="error"><c:out value="${ message }"/></div>
					</c:forEach>
				</div>
				<div>
					<label>名称:</label>
					<input type="text" name="name"  size="10" maxlength="10"
					value="<c:out value="${ inputUsers.name }"/>">
					<c:forEach items="${ violationMessages['_name'] }" var="message">
						<div class="error"><c:out value="${ message }"/></div>
					</c:forEach>
				</div>
				<div>
					<label>部署:</label>
					<select name="branch">
						<c:forEach items="${ branches }" var="branch">
							<option value="${ branch.id }" <c:if test="${ branch.id == inputUsers.branchId  }">selected</c:if> >
								<c:out value="${ branch.name }" />
							</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label>役職:</label>
					<select name="department">
						<c:forEach items="${ departments }" var="department">
							<option value="${ department.id }" <c:if test="${ department.id == inputUsers.departmentId  }">selected</c:if> >
								<c:out value="${ department.name }" />
							</option>
						</c:forEach>
					</select>
				</div>
				<div><input id="button" type="submit" value="登録"></div>
			</form>
		</div>
	</div>
</body>
</html>