<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bbs.css">
<title>掲示板システム</title>
</head>
<body id="home">
	<div id="wrapper">
		<div id="header">
			<h1>掲示板システム</h1>
			<div id="menu">
				<div id="error">
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
			<form action="login" method="post">
				<div>
					<label>ログインID:</label>
					<input type="text" name="login_id"  size="20" maxlength="20">
				</div>
				<div>
					<label>パスワード:</label>
					<input type="password" name="password"  size="20" maxlength="255">
				</div>
				<div>
					<input type="submit" value="ログイン">
				</div>
			</form>
		</div>
	</div>
</body>
</html>