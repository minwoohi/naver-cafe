
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/resources/template/common_header.jsp" />
<meta name="google-signin-client_id" content="724513251495-v5puuk76dk0sur0u2fa8boab6b1t1u4g.apps.googleusercontent.com">
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js"/>"></script>
<script type="text/javascript">

$().ready(function(){
	
	$("#googleSignInBtn").click(function(){
		$.post("<c:url value="/member/googleSignIn" />", $("#googleSignInForm").serialize()
				, function(response){
			// 팝업을 연 주체의 url 요청을 변경. 새 페이지 요청 후 자기 닫음
			opener.location.href = response;
			self.close();
		});
	});
	
	$("#signInBtn").click(function(){
				
		var memberId = $("#memberId").val();
		var memberPassword = $("#memberPassword").val();
		
		if( !memberId ){
			alert("Id를 입력하세요");
			return;
		} else if ( !memberPassword ) {
			alert("password 입력하세요");
			return;
		}
		
		$.post("<c:url value="/member/signIn" />", $("#signInForm").serialize()
				, function(response){
			// 페이지 새로고침
			opener.location.reload();
			window.close();
		});
	});
});

</script>
<form:form id="signInForm">
	<input type="text" name="memberId" id="memberId" placeholder="ID 입력하세요" />
	<input type="password" name="memberPassword" id="memberPassword"
		placeholder="Password 입력하세요" />
	<br/>
	<input type="button" id="signInBtn" value="sign in" />
</form:form>
<form:form id="googleSignInForm">
	<input type="image" id="googleSignInBtn"
		src="<c:url value="/static/img/googleLogin.PNG"/>" height="50px" width="180px">
</form:form>
</body>
</html>