<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
</head>
<body>
	<form action="registerPost" method="post">
		<table>
			<tr>
				<td align="right">件名:</td>
				<td><input type="text" name="title"  size="20" maxlength="50"></td>
			</tr>
			<tr>
				<td align="right">投稿内容:</td>
				<td><textarea name="mainText" cols=40 rows=4 ></textarea></td>
			</tr>
			<tr>
				<td align="right">カテゴリー:</td>
				<td><input type="text" name="category"  size="20" maxlength="10"></td>
			</tr>
			<tr>
				<td><input type="submit" value="登録"></td>
			</tr>
		</table>
	</form>
</body>
</html>