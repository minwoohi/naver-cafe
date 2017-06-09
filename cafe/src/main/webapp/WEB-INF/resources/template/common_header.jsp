<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id" content="724513251495-v5puuk76dk0sur0u2fa8boab6b1t1u4g.apps.googleusercontent.com">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Oswald">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open Sans">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
h1,h2,h3,h4,h5,h6 {font-family: "Oswald"}
body {font-family: "Open Sans"}
</style>
<title>Insert title here</title>

<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js" />"></script>
<script type="text/javascript">
	
	$().ready(function(){
		
		<c:if test="${ empty sessionScope._MEMBER_}" >
			$("#signUp").click(function(){
				window.open('<c:url value='/member/signUp' />', 'popup', 'z-lock=yes, width=600, height=400, left=500, top=100').focus();
			});
		
			$("#signIn").click(function(){
				window.open('<c:url value='/member/signIn' />', 'popup', 'z-lock=yes, width=300, height=250, left=500, top=100').focus();
			});
			
		</c:if>
		
		<c:if test="${not empty sessionScope._MEMBER_}">
			
			$("#signOut").click(function(){
				var child = window.open('https://accounts.google.com/logout', 'popup', 'z-lock=yes, width=600, height=400, left=500, top=100');
				
				 $.post("<c:url value="/member/signOut" />", $("#signInForm").serialize()
				, function(response){
					
					if(response.error){
						alert("응답 에러");
						return;
					} else {
						// 페이지 새로고침
						child.close();
						location.reload();
					}
				});
			});
		
			$("#editInfo").click(function(){
				window.open('<c:url value='/member/modify' />', 'popup', 'z-lock=yes, width=600, height=250, left=500, top=100').focus();
			});
			
		</c:if>
		
	});
</script>

</head>
<body class="w3-light-grey">
<div class="w3-bar w3-black w3-hide-small" >
<c:if test="${empty sessionScope._MEMBER_ }">
  <button class="w3-button w3-black" id="signUp">Sign Up</button>
  <button class="w3-button w3-black" id="signIn">Sign In</button>
</c:if>
  <c:if test="${not empty sessionScope._MEMBER_ }">
  	<a href="<c:url value="/"/>" class="w3-bar-item w3-button">Main</a>
  	<button class="w3-button w3-black" id="editInfo">Edit My Info</button>
  	<button class="w3-button w3-black" id="signOut">Sign Out</button>
  </c:if>
</div>