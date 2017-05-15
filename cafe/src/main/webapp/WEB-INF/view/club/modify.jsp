<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<c:url value="/static/js/jquery-3.1.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#saveBtn").click(function() {
			var subject = $("#subject").val();
			var content = $("#content").val();
			
			if( !subject) {
				alert("제목을 입력하세요!");
				return;
			} else if ( !content){
				alert("내용을 입력하세요!");
				return;
			}
			
			$("#writeForm").attr({
				"method" : "post",
				"action" : "<c:url value="/club/modify/${menuId}/${club.articleId}" />"
			});
			$("#writeForm").submit();

		});
		
		$("#cancleBtn").click(function(){
			$("#writeForm").attr({
				"method" : "get",
				"action" : "<c:url value="/club/detail/${menuId}/${club.articleId}" />"
			});
			$("#writeForm").submit();
		});

	});
</script>
<script src="<c:url value="/static/js/ckeditor/ckeditor.js" />"></script>
</head>
<body>
	<form:form id="writeForm">
		작성자 : ${memberId } <br/>
		<input type="hidden" name="csrf_token" value="${sessionScope._CSRF_TOKEN_ }" />
		<input type="text" name="subject" value="${club.subject }" id="subject" placeholder="제목을 입력하세요"/>
		<textarea name="content" id="content" rows="10" cols="80">
                ${club.content }
            </textarea>
		<script>
			// Replace the <textarea id="editor1"> with a CKEditor
			// instance, using default configuration.
			CKEDITOR.replace('content');
		</script>
	</form:form>
	<div id="uploadFiles"></div>
	<div>
		<form target="uploadFrame"
				method="post" action="<c:url value="/club/upload" />"
				enctype="multipart/form-data">
			<input type="file" name="file" />
			<input type="submit" value="Upload" />
		</form>
		<iframe name="uploadFrame" style="display: none;" ></iframe>
	</div>
	
	<input type="button" value="Save" id="saveBtn" />
	<input type="button" value="Cancle" id="cancleBtn" />

</body>
</html>