<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
    function getSelect(text) {
    	document.getElementById('selectedCategory').value = text;
    }
</script>
<link rel="stylesheet" type="text/css" href="css/bbs.css">
<title>掲示板システム</title>
</head>
<body id="home">
	<div id="wrapper">
		<div id="header">
			<h1>新規投稿</h1>
			<div id="menu">
				<ul>
					<li><a href="home">ホーム</a></li>
				</ul>
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
			<form action="registerPost" method="post">
				<div>
					<label>件名:</label>
					<input type="text" name="title"  size="20" maxlength="50"
					value="<c:out value="${ inputPosts.title }"/>">
					<c:forEach items="${ violationMessages['_title'] }" var="message">
						<div id="error"><c:out value="${ message }"/></div>
					</c:forEach>
				</div>
				<div>
					<label>投稿内容:</label>
					<textarea name="mainText" cols=50 rows=10 ><c:out value="${ inputPosts.text }"/></textarea>
					<c:forEach items="${ violationMessages['_text'] }" var="message">
						<div id="error"><c:out value="${ message }"/></div>
					</c:forEach>
				</div>
				<div>
					<label>カテゴリー:</label>
					<input type="text" name="category" id="selectedCategory" size="20" maxlength="10"
					value="<c:out value="${ inputPosts.category }"/>">
					<c:forEach items="${ violationMessages['_category'] }" var="message">
						<div id="error"><c:out value="${ message }"/></div>
					</c:forEach>
				</div>
				<div>
					<label>カテゴリー一覧:</label>
					<select name="categories" onChange="getSelect(this.value)">
						<c:forEach items="${ categories }" var="category" >
							<option value="${ category }">
								<c:out value="${ category }" />
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