 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/resources/template/common_header.jsp" />
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js"/>" ></script>
<script type="text/javascript">

	$().ready(function(){
		$("#signInBtn").click(function(){
			var memberId = $("#memberId").val();
			var memberPassword = $("#memberPassword").val();
			
			if( !memberId ){
				alert("Id를 입력하세요");
				return;
			}else if( !memberPassword) {
				alert("Password를 입력하세요");
				return;
			}
			
			/* $("#signInForm").attr({
				"method" : "post",
				"action" : "<c:url value="/member/signIn" />"
			});
			세션 정보는 response에 존재하는데 바로 submit 해버리면 그 정보를 받을 수 없다. 따라서  post 요청으로 세션이 담긴 응답정보를 담아 부모창을 새로고침한다.
			$("#signInForm").submit(); */
			
			// form을 json 형태로 전송
			$.post("<c:url value="/member/signIn" />", $("#signInForm").serialize(), function(response) {
				// 세션 정보 있는 response 를 받은 후 부모창 새로고침
				opener.location.reload();
				window.close();
			});
		});
		
	});
</script>
	<form:form id="signInForm" >
		<input type="text" name="memberId" id="memberId"placeholder="ID 입력하세요"/>
		<input type="password" name="memberPassword" id="memberPassword" placeholder="Password 입력하세요" /><br/>
		<input type="button" id="signInBtn" value="sign in" />
	</form:form>
	<a href="<c:url value="/member/googleSignIn"/>"><img src="<c:url value="/static/img/googleLogin.PNG"/>" height="50px" width="180px"></a>
</body>
</html>