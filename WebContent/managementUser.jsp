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
<title>ユーザー管理画面</title>
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
	<table border=2 align="left">
		<tr>
			<td>メニュー</td>
		</tr>
		<tr>
			<td>
				・<a href="registerUser">ユーザー新規登録画面</a>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td>ユーザー一覧</td>
		</tr>
		<tr><td>
		<table border=2>
		<tr>
			<td>ログインID</td>
			<td>名称</td>
		</tr>
		 <c:forEach items="${ users }" var="user" varStatus="status">
			<tr>
				<td><c:out value="${ user.loginId }"/></td>
				<td><c:out value="${ user.name }"/></td>
				<td>
					<form name="forButton${ status.index }" action="" method="post">
						<input type="hidden" name="id" value="${ user.id }" >
						<input type="button" name="edit" value="編集" onClick="func(this.name,this.form)">
						<c:choose>
							<c:when test="${ user.isLocked == 0 }">
								<input type="button" name="stop" value="停止" onClick="func(this.name,this.form)">
							</c:when>
							<c:when test="${ user.isLocked == 1 }">
								<input type="button" name="resurrect" value="復活" onClick="func(this.name,this.form)">
							</c:when>
						</c:choose>
					</form>
				</td>
			</tr>
		</c:forEach>
		</table>
		</td></tr>
	</table>
</body>
</html>