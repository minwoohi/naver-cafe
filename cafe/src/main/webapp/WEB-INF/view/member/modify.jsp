<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>member/signUp.jsp</title>
<style type="text/css">
	form > input{
		margin: 1px;
	}
</style>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/common.css"/>" /> --%>
<jsp:include page="/WEB-INF/resources/template/common_header.jsp" />
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js"/>"></script>
<script type="text/javascript">

	$().ready(function(){
		
		$("#modifyBtn").click(function(){
			var memberPassword = $("#password").val();
			var nickName = $("#nickName").val();
			
			var isValidNumber = $("#isValidNumber").val();
			var isValidSpecialCharacter = $("#isValidSpecialCharacter").val();
			var isValidCharacter = $("#isValidCharacter").val();
			var isOverEight = $("#isOverEight").val();
			
			if( !memberPassword) {
				alert("Password를 입력하세요");
				return;
			} else if ( !nickName ){
				alert("nickName을 입력하세요");
				return;
			} else if ( isValidNumber == 'X' || isValidCharacter == 'X' 
						|| isValidSpecialCharacter == 'X' || isOverEight == 'X'){
				alert("Password는 영대소문자, 숫자, 특수문자를 포함한 8자 이상이어야 합니다.");
				return;
			}
			
			$("#modifyForm").attr({
				"method" : "post",
				"action" : "<c:url value="/member/modify" />"
			});
			$("#modifyForm").submit();
			
			opener.parent.location.reload();
			window.close();
		});
		
		
		$("#password").keyup(function(){
			$.post("<c:url value="/member/checkPasswordCondition" />", 
					{
						"memberPassword" : $("#password").val()
					}, function(response){
						data = JSON.parse(response);
				if(data.isValidNumber){
					$("#isValidNumber").val("v");
				} else {
					$("#isValidNumber").val("X");
				}
				if(data.isValidCharacter){
					$("#isValidCharacter").val("v");
				} else {
					$("#isValidCharacter").val("X");
				}
				if(data.isValidSpecialCharacter){
					$("#isValidSpecialCharacter").val("v");
				} else {
					$("#isValidSpecialCharacter").val("X");
				}
				if(data.isOverEight){
					$("#isOverEight").val("v");
				} else {
					$("#isOverEight").val("X");
				}
				
			});
		});
		
	});

</script>
</head>
<body>
	<form:form commandName="modifyForm">
		<input type="hidden" name="memberId" value="${member.memberId }" />
		<input type="text" id="memberId" value="${member.memberId }" disabled="disabled"/><br/>
		<input type="password" name="memberPassword" id="password" placeholder="Password 입력하세요"/><br/>
		<input type="text" name="nickName" id="nickName" value="${member.nickName }" />
		<input type="button" value="modify" id="modifyBtn" /><br/><hr/>
		숫자 포함 : <input type="text" id="isValidNumber" value="X" /><br/>
		영대소문자 포함 : <input type="text" id="isValidCharacter" value="X" /><br/>
		특수문자 포함 : <input type="text" id="isValidSpecialCharacter" value="X" /><br/>
		8자 이상 : <input type="text" id="isOverEight" value="X" />
	</form:form>
</body>
</html>