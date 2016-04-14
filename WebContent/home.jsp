<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板システムホーム画面</title>
</head>
<body>
	<table border=2 align="left">
		<tr>
			<td>メニュー</td>
		</tr>
		<tr>
			<td>
				・<a href="registerPost">新規投稿</a>
			</td>
		</tr>
		<tr>
			<td>
				・<a href="managementUser">ユーザー管理画面</a>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td>投稿一覧</td>
		</tr>
		<tr><td>
			<c:forEach items="${ posts }" var="post">
				<tr><td><table border=2>
					<tr>
						<td>件名： <c:out value="${ post.title }"/> </td>
					</tr>
					<tr>
						<td> <c:out escapeXml="fales" value="${ post.text }"/> </td>
					</tr>
					<tr>
						<td>投稿日時： <c:out value="${ post.createdDateString }"/> </td>
					</tr>
					<tr>
						<td>投稿者： <c:out value="${ post.userName }"/> </td>
					</tr>
					<tr>
						<td>コメント欄</td>
					</tr>
					<c:forEach items="${ post.comments }" var="comment">
						<tr><td>
						<table border=2>
							<tr>
								<td>コメント者： <c:out value="${ comment.userName }"/> ＜＜ <c:out value="${ comment.text }"/> </td>
							</tr>
							<tr>
								<td>コメント日時： <c:out value="${ comment.createdDateString }"/> </td>
							</tr>
						</table>
						</td></tr>
					</c:forEach>
					<tr>
						<td>
							<form action="registerComment" method="post">
								<textarea name="mainText" cols=40 rows=4 ><c:out value="${ inputComments.text }"/></textarea>
								<input type="submit" value="コメントする">
								<input type="hidden" name="post_id" value="<c:out value="${ post.id }"/>">
							</form>
							<c:forEach items="${ violationMessages['_text'] }" var="message">
								<c:out value="${ message }"/>
							</c:forEach>
						</td>
					</tr>
				</table></td></tr>
			</c:forEach>
		</td></tr>
	</table>
</body>
</html>