<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript">
function func(command,form){
	var popupMessage;

	//あらかじめ停止・復活機能を入れる
	form.action = "managementUser";

	if(command == "edit"){
		form.action = "editUser";
		form.submit();
	}else if(command == "stop"){
		confirmSubmit("ユーザーを停止しますか。",form)
	}else if(command == "resurrect"){
		confirmSubmit("ユーザーを復活しますか。",form);
	}
}

function confirmSubmit(message,form){
	var result = confirm( message );
	if(result){
		form.submit();
	}
}

</script>
<link rel="stylesheet" type="text/css" href="css/bbs.css">
<title>掲示板システム</title>
</head>
<body id="home">
	<div id="wrapper">
		<div id="header">
			<h1>ユーザー管理画面</h1>
			<div id="menu">
				<ul>
					<li><a href="registerUser">ユーザー新規登録</a></li>
					<li><a href="home">ホーム</a></li>
				</ul>
			</div>
			<div id="error">
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
			</div>
		</div>
		<div id="users">
			<div id="usersHeader">
				<div>ログインID</div>
				<div>名称</div>
				<div>操作</div>
			</div>
			 <c:forEach items="${ users }" var="user" varStatus="status">
	 			<div id="user">
					<div><c:out value="${ user.loginId }"/></div>
					<div><c:out value="${ user.name }"/></div>
					<div>
						<form name="forButton${ status.index }" action="" method="post">
							<input type="hidden" name="id" value="${ user.id }" >
							<input type="button" name="edit" id="editButton" value="編集" onClick="func(this.name,this.form)">
							<c:choose>
								<c:when test="${ user.isLocked == 0 }">
									<input type="button" name="stop" id="lockButton" value="停止" onClick="func(this.name,this.form)">
								</c:when>
								<c:when test="${ user.isLocked == 1 }">
									<input type="button" name="resurrect" id="lockButton" value="復活" onClick="func(this.name,this.form)">
								</c:when>
							</c:choose>
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>