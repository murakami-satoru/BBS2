<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jkl-calendar.js" charset="Shift_JIS"></script>
<script>
    var toCal = new JKL.Calendar("toCal","search_date","to_date");
    var fromCal = new JKL.Calendar("fromCal","search_date","from_date");

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
			<h1>ホーム画面</h1>
			<div id="menu">
				<ul>
					<li><a href="registerPost">新規投稿</a></li>
					<li><a href="managementUser">ユーザー管理</a></li>
					<li>　</li>
					<li>　</li>
					<li>　</li>
					<li>　</li>
					<li><a href="logout">ログアウト</a></li>
				</ul>
				<div id="error">
					<c:if test="${ not empty errorMessages }">
						<c:forEach items="${ errorMessages }" var="message">
							<div id="error"><c:out value="${ message }"/></div>
						</c:forEach>
						<c:remove var="errorMessages" scope="session"/>
					</c:if>
				</div>
			</div>
		</div>
		<div id="search">
			<form action="searchCategory" method="post">
				<input type="text" name="category" id="selectedCategory" size="20" maxlength="10"
					value="<c:out value="${ inputCategory }"/>">
				<select name="categories" onChange="getSelect(this.value)">
					<c:forEach items="${ categories }" var="category" >
						<option value="${ category }">
							<c:out value="${ category }" />
						</option>
					</c:forEach>
				</select>
				<input type="submit" value="カテゴリー検索">
			</form>
			<form id="search_date" action="searchDate" method="post">
				<input type="text" name="from_date" onClick="toCal.hide(); fromCal.write();"
				onChange="fromCal.getFormValue(); fromCal.hide();"
				value="<c:out value="${ inputFromDate }"/>" readonly size="8"><div id="fromCal"></div>

				<label>～</label>
				<input type="text" name="to_date" onClick="fromCal.hide(); toCal.write();"
				onChange="toCal.getFormValue(); toCal.hide();"
				value="<c:out value="${ inputToDate }"/>" readonly size="8"><div id="toCal"></div>
				<input type="submit" value="日時検索">
			</form>
		</div>
		<div id="posts">
			<c:forEach items="${ posts }" var="post" varStatus="status">
				<div id="post">
					<div id="postTitle"><c:out value="${ post.title }"/></div>
					<div id="postText"><c:out escapeXml="fales" value="${ post.text }"/> </div>
					<div id="postDate"><c:out value="${ post.createdDateString }"/></div>
					<div id="postName"><c:out value="${ post.userName }"/></div>
					<div id="comments">
						<label>コメント欄</label>
						<c:forEach items="${ post.comments }" var="comment">
							<div id="comment">
								<div id="commentText"><c:out escapeXml="fales" value="${ comment.text }"/></div>
								<div id="commentDate"><c:out value="${ comment.createdDateString }"/></div>
								<div id="commentName"><c:out value="${ comment.userName }"/></div>
							</div>
						</c:forEach>
						<div>
							<form action="registerComment" method="post">
							<textarea name="mainText" cols=80 rows=10 ><c:if test="${ isErrorPost == post.id }"><c:out value="${ inputComments.text }"/></c:if></textarea>
							<input type="submit" value="コメントする">
							<input type="hidden" name="post_id" value="<c:out value="${ post.id }"/>">
							</form>
							<c:if test="${ isErrorPost == post.id }">
								<c:forEach items="${ violationMessages['_text'] }" var="message">
									<div id="error"><c:out value="${ message }"/></div>
								</c:forEach>
							</c:if>
						</div>
					</div>
					<div id="deleteButton">
						<form action="deletePost" method="post">
							<input type="hidden" name="post_id" value="<c:out value="${ post.id }"/>">
							<input type="submit" value="この投稿を削除"
							<c:choose>
								<c:when test="${ (loginUser.branchId == 1 && loginUser.departmentId == 2) || (loginUser.branchId == post.branchId && loginUser.departmentId <= post.departmentId) }"></c:when>
								<c:otherwise>disabled</c:otherwise>
							</c:choose>
							>
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>