<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Oswald">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open Sans">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
h1,h2,h3,h4,h5,h6 {font-family: "Oswald"}
body {font-family: "Open Sans"}

.validCheck {
	background-color: #ed2828;
	font-weight: bolder;
}

</style>
<title>member/signUp.jsp</title>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js"/>"></script>
<script type="text/javascript">

	$().ready(function(){
		
		$("#signUpBtn").click(function(){
			var memberId = $("#memberId").val();
			var memberPassword = $("#password").val();
			var nickName = $("#nickName").val();
			
			var isValidNumber = $("#isValidNumber").val();
			var isValidSpecialCharacter = $("#isValidSpecialCharacter").val();
			var isValidCharacter = $("#isValidCharacter").val();
			var isOverEight = $("#isOverEight").val();
			
			if( !memberId ){
				alert("Id를 입력하세요");
				return;
			}else if( !memberPassword) {
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
			
			/* $("#signUpForm").attr({
				"method" : "post",
				"action" : "<c:url value="/member/signUp" />"
			});
			$("#signUpForm").submit(); */
			
			$.post("<c:url value="/member/signUp" />", $("#signUpForm").serialize(), function(response) {
				alert("signIn 요청");
				// 세션 정보 있는 response 를 받은 후 부모창 새로고침
				opener.location.reload();
				window.close();
			});
			
		});
		
		$("#memberId").keyup(function(){
			$.post("<c:url value="/member/checkDuplicatedMemberId" />",
					{
						"memberId" : $("#memberId").val()
					}, function(response){
						data = JSON.parse(response)
						
						if(data.isDuplicated){
							$("#idDuplicated").text("중복되는 아이디입니다. 다른 아이디를 입력해주세요");
						} else {
							$("#idDuplicated").text("사용할 수 있는 아이디입니다!");
						}
						
					});
		});
		
		$("#password").keyup(function(){
			$.post("<c:url value="/member/checkPasswordCondition" />", 
					{
						"memberPassword" : $("#password").val()
					}, function(response){
						data = JSON.parse(response);
				if(data.isValidNumber){
					$("#isValidNumber").val("v");
					$("#isValidNumber").css("background-color", "#2cff19");
				} else {
					$("#isValidNumber").val("X");
				}
				if(data.isValidCharacter){
					$("#isValidCharacter").val("v");
					$("#isValidCharacter").css("background-color", "#2cff19");
				} else {
					$("#isValidCharacter").val("X");
				}
				if(data.isValidSpecialCharacter){
					$("#isValidSpecialCharacter").val("v");
					$("#isValidSpecialCharacter").css("background-color", "#2cff19");
				} else {
					$("#isValidSpecialCharacter").val("X");
				}
				if(data.isOverEight){
					$("#isOverEight").val("v");
					$("#isOverEight").css("background-color", "#2cff19");
				} else {
					$("#isOverEight").val("X");
				}
				
			});
		});
		
	});

</script>
</head>
<body>
	<form:form commandName="signUpForm">
		<input type="text" name="memberId" id="memberId" placeholder="ID 입력하세요" />
		<span id="idDuplicated"></span><br/>
		<input type="password" name="memberPassword" id="password" placeholder="Password 입력하세요"/><br/>
		<input type="text" name="nickName" id="nickName" placeholder="nickName 입력하세요" />
		<input type="button" class="w3-button w3-black" value="signUp" id="signUpBtn" /><br/><hr/>
		숫자 포함 : <input type="text" class="validCheck" id="isValidNumber" disabled="disabled" value="X" /><br/>
		영대소문자 포함 : <input type="text" class="validCheck" id="isValidCharacter" disabled="disabled" value="X" /><br/>
		특수문자 포함 : <input type="text" class="validCheck" id="isValidSpecialCharacter" value="X" disabled="disabled" /><br/>
		8자 이상 : <input type="text" class="validCheck" id="isOverEight" value="X" disabled="disabled" />
	</form:form>
</body>
</html>