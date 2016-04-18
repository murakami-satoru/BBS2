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
<link rel="stylesheet" type="text/css" href="css/home.css">
<title>掲示板システム</title>
</head>
<body id="home">
	<div id="wrapper">
		<div id="header">
			<h1>掲示板システムホーム画面</h1>
			<div id="globalnavi">
				<ul>
					<li><a href="registerPost">新規投稿</a></li>
					<li><a href="managementUser">ユーザー管理画面</a></li>
				</ul>
				<c:if test="${ not empty errorMessages }">
					<c:forEach items="${ errorMessages }" var="message">
						<c:out value="${ message }"/>
					</c:forEach>
					<c:remove var="errorMessages" scope="session"/>
				</c:if>
			</div>
		</div>
		<div id="posts">
			<div id="search">
				<div>
					<form action="searchCategory" method="post">
						<label>カテゴリー検索：</label>
						<input type="text" name="category" id="selectedCategory" size="20" maxlength="10"
							value="<c:out value="${ inputCategory }"/>">
						<select name="categories" onChange="getSelect(this.value)">
							<c:forEach items="${ categories }" var="category" >
								<option value="${ category }">
									<c:out value="${ category }" />
								</option>
							</c:forEach>
						</select>
						<input type="submit" value="検索">
					</form>
				</div>
				<form id="search_date" action="searchDate" method="post">
					<label>日時検索：</label>
					<input type="text" name="from_date" onClick="toCal.hide(); fromCal.write();"
					onChange="fromCal.getFormValue(); fromCal.hide();"
					value="<c:out value="${ inputFromDate }"/>" readonly size="8"><div id="fromCal"></div>

					<label>～</label>
					<input type="text" name="to_date" onClick="fromCal.hide(); toCal.write();"
					onChange="toCal.getFormValue(); toCal.hide();"
					value="<c:out value="${ inputToDate }"/>" readonly size="8"><div id="toCal"></div>
					<input type="submit" value="検索">
				</form>
			</div>
			<div>
				<c:forEach items="${ posts }" var="post" varStatus="status">
					<div id="post">
						<div>件名： <c:out value="${ post.title }"/></div>
						<div>カテゴリー： <c:out value="${ post.category }"/></div>
						<div> <c:out escapeXml="fales" value="${ post.text }"/> </div>
						<div>投稿日時： <c:out value="${ post.createdDateString }"/></div>
						<div>投稿者： <c:out value="${ post.userName }"/></div>
						<div>
							<c:if test="${ (loginUser.branchId == 1 && loginUser.departmentId == 2)  ||
										   (loginUser.branchId == post.branchId && loginUser.departmentId <= post.departmentId) }">
								<form action="deletePost" method="post">
									<input type="hidden" name="post_id" value="<c:out value="${ post.id }"/>">
									<input type="submit" value="削除">
								</form>
							</c:if>
						</div>
						<div>コメント欄</div>
						<div id="comments">
							<c:forEach items="${ post.comments }" var="comment">
								<div>コメント者： <c:out value="${ comment.userName }"/> ＜＜ <c:out value="${ comment.text }"/> </div>
								<div>コメント日時： <c:out value="${ comment.createdDateString }"/></div>
								<div></div>
							</c:forEach>
						</div>
						<div id="form">
							<form action="registerComment" method="post">
							<textarea name="mainText" cols=40 rows=4 ><c:out value="${ inputComments.text }"/></textarea>
							<input type="submit" value="コメントする">
							<input type="hidden" name="post_id" value="<c:out value="${ post.id }"/>">
							</form>
							<c:forEach items="${ violationMessages['_text'] }" var="message">
								<c:out value="${ message }"/>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>