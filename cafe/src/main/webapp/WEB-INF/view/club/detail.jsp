<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/resources/template/common_header.jsp" />
<link type="text/css" rel="stylesheet" href="<c:url value="/static/js/ckeditor/contents.css?t=H0CG"/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value="/static/js/ckeditor/plugins/copyformatting/styles/copyformatting.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/common.css"/>" />
<style>
.cke_editable{cursor:text}.cke_editable img,.cke_editable input,.cke_editable textarea{cursor:default}
img.cke_flash{background-image: url("<c:url value="/static/js/ckeditor/plugins/flash/images/placeholder.png?t=H0CG"/>");background-position: center center;background-repeat: no-repeat;border: 1px solid #a9a9a9;width: 80px;height: 80px;}
.cke_editable form{border: 1px dotted #FF0000;padding: 2px;}

img.cke_hidden{background-image: url("<c:url value="/static/js/ckeditor/plugins/forms/images/hiddenfield.gif?t=H0CG"/>");background-position: center center;background-repeat: no-repeat;border: 1px solid #a9a9a9;width: 16px !important;height: 16px !important;}
img.cke_iframe{background-image: url("<c:url value="/static/js/ckeditor/plugins/iframe/images/placeholder.png?t=H0CG"/>");background-position: center center;background-repeat: no-repeat;border: 1px solid #a9a9a9;width: 80px;height: 80px;}
.cke_contents_ltr a.cke_anchor,.cke_contents_ltr a.cke_anchor_empty,.cke_editable.cke_contents_ltr a[name],.cke_editable.cke_contents_ltr a[data-cke-saved-name]{background:url("<c:url value="/static/js/ckeditor/plugins/link/images/anchor.png?t=H0CG"/>") no-repeat left center;border:1px dotted #00f;background-size:16px;padding-left:18px;cursor:auto;}.cke_contents_ltr img.cke_anchor{background:url("<c:url value="/static/js/ckeditor/plugins/link/images/anchor.png?t=H0CG"/>") no-repeat left center;border:1px dotted #00f;background-size:16px;width:16px;min-height:15px;height:1.15em;vertical-align:text-bottom;}.cke_contents_rtl a.cke_anchor,.cke_contents_rtl a.cke_anchor_empty,.cke_editable.cke_contents_rtl a[name],.cke_editable.cke_contents_rtl a[data-cke-saved-name]{background:url("<c:url value="/static/js/ckeditor/plugins/link/images/anchor.png?t=H0CG"/>") no-repeat right center;border:1px dotted #00f;background-size:16px;padding-right:18px;cursor:auto;}.cke_contents_rtl img.cke_anchor{background:url("<c:url value="/static/js/ckeditor/plugins/link/images/anchor.png?t=H0CG"/>") no-repeat right center;border:1px dotted #00f;background-size:16px;width:16px;min-height:15px;height:1.15em;vertical-align:text-bottom;}
div.cke_pagebreak{background:url("<c:url value="/static/js/ckeditor/plugins/pagebreak/images/pagebreak.gif?t=H0CG"/>") no-repeat center center !important;clear:both !important;width:100% !important;border-top:#999 1px dotted !important;border-bottom:#999 1px dotted !important;padding:0 !important;height:7px !important;cursor:default !important;}
.cke_show_blocks h6:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks h5:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks h4:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks h3:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks h2:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks h1:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks blockquote:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks address:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks pre:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks div:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks p:not([contenteditable=false]):not(.cke_show_blocks_off){background-repeat:no-repeat;border:1px dotted gray;padding-top:8px}.cke_show_blocks h6:not([contenteditable=false]):not(.cke_show_blocks_off){background-image:url("<c:url value="/static/js/ckeditor/plugins/showblocks/images/block_h6.png?t=H0CG"/>")}.cke_show_blocks h5:not([contenteditable=false]):not(.cke_show_blocks_off){background-image:url("<c:url value="/static/js/ckeditor/plugins/showblocks/images/block_h5.png?t=H0CG"/>")}.cke_show_blocks h4:not([contenteditable=false]):not(.cke_show_blocks_off){background-image:url("<c:url value="/static/js/ckeditor/plugins/showblocks/images/block_h4.png?t=H0CG"/>")}.cke_show_blocks h3:not([contenteditable=false]):not(.cke_show_blocks_off){background-image:url("<c:url value="/static/js/ckeditor/plugins/showblocks/images/block_h3.png?t=H0CG"/>")}.cke_show_blocks h2:not([contenteditable=false]):not(.cke_show_blocks_off){background-image:url("<c:url value="/static/js/ckeditor/plugins/showblocks/images/block_h2.png?t=H0CG"/>")}.cke_show_blocks h1:not([contenteditable=false]):not(.cke_show_blocks_off){background-image:url("<c:url value="/static/js/ckeditor/plugins/showblocks/images/block_h1.png?t=H0CG"/>")}.cke_show_blocks blockquote:not([contenteditable=false]):not(.cke_show_blocks_off){background-image:url("<c:url value="/static/js/ckeditor/plugins/showblocks/images/block_blockquote.png?t=H0CG"/>")}.cke_show_blocks address:not([contenteditable=false]):not(.cke_show_blocks_off){background-image:url("<c:url value="/static/js/ckeditor/plugins/showblocks/images/block_address.png?t=H0CG"/>")}.cke_show_blocks pre:not([contenteditable=false]):not(.cke_show_blocks_off){background-image:url("<c:url value="/static/js/ckeditor/plugins/showblocks/images/block_pre.png?t=H0CG"/>")}.cke_show_blocks div:not([contenteditable=false]):not(.cke_show_blocks_off){background-image:url("<c:url value="/static/js/ckeditor/plugins/showblocks/images/block_div.png?t=H0CG"/>")}.cke_show_blocks p:not([contenteditable=false]):not(.cke_show_blocks_off){background-image:url("<c:url value="/static/js/ckeditor/plugins/showblocks/images/block_p.png?t=H0CG"/>")}.cke_show_blocks.cke_contents_ltr h6:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_ltr h5:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_ltr h4:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_ltr h3:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_ltr h2:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_ltr h1:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_ltr blockquote:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_ltr address:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_ltr pre:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_ltr div:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_ltr p:not([contenteditable=false]):not(.cke_show_blocks_off){background-position:top left;padding-left:8px}.cke_show_blocks.cke_contents_rtl h6:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_rtl h5:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_rtl h4:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_rtl h3:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_rtl h2:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_rtl h1:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_rtl blockquote:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_rtl address:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_rtl pre:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_rtl div:not([contenteditable=false]):not(.cke_show_blocks_off),.cke_show_blocks.cke_contents_rtl p:not([contenteditable=false]):not(.cke_show_blocks_off){background-position:top right;padding-right:8px}
.cke_show_borders  table.cke_show_border,.cke_show_borders  table.cke_show_border > tr > td, .cke_show_borders  table.cke_show_border > tr > th,.cke_show_borders  table.cke_show_border > tbody > tr > td, .cke_show_borders  table.cke_show_border > tbody > tr > th,.cke_show_borders  table.cke_show_border > thead > tr > td, .cke_show_borders  table.cke_show_border > thead > tr > th,.cke_show_borders  table.cke_show_border > tfoot > tr > td, .cke_show_borders  table.cke_show_border > tfoot > tr > th{border : #d3d3d3 1px dotted}
</style>
</head>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js" />"></script>
<script type="text/javascript">

	$().ready(function(){
		
		$("#commentBtn").click(function(){
			
			if (!$("#content").val()) {
			    alert("내용을 입력하세요!");
			    return;
			}
			
			/* if (!$.trim($("#content").val())) {
				alert("내용을 입력해주세요");
				return;
			} */
			
			$(this).parent().attr({
				"method" : "post",
				"action" : "<c:url value="/club/reply/write/${menuId}/${articleId}" />"
			});
			$(this).parent().submit();
		});
		
		$(".removeReply").click(function(){
			$(this).parent().attr({
				"method" : "post",
				"action" : "<c:url value="/club/reply/remove/${menuId}/${articleId}" />"
			});
			$(this).parent().submit();
		});
		
		// 댓글 작성 버튼 클릭하면
		$(".viewWriteReply").click(function(){
			
			var btnValue = $(this).val();
			
			if(btnValue == "댓글 작성"){
				var parentReplyId = $(this).closest(".replyForm").find(".replyId").val();
				var form = $("<form class='replyForm'></form>");
				var parentReplyTag = $("<input type='hidden' name='parentReplyId' />");
				parentReplyTag.attr("value", parentReplyId);
				var memberId = $("<input type='hidden' name='memberId' value='${sessionScope._MEMBER_.memberId}' />");
				//alert(memberId.attr('value'));
				var articleId = $("<input type='hidden' name='articleId' value='${club.articleId }'/>");
				var textarea = $("<textarea name='content' rows='5' cols='100'></textarea>");
				var btn = $("<input type='button' class='replyWriteBtn' value='reply' style='color: red;'/>");
			
				form = form.append(parentReplyTag).append(memberId).append(articleId).append(textarea).append(btn);
				
				$(this).closest(".replyForm").after(form);
				$(this).val("댓글 취소");
			} else {
				$(this).val("댓글 작성");
				$(this).closest(".replyForm").next(".replyForm").remove();	
			}
		});
		
		$(".editReply").click(function(){
			var btnValue = $(this).val();
			// ul 값 저장해둬 textarea의 내용으로 넣고 cloeset writeForm에 append
			if(btnValue == "댓글 수정"){
				//text = text.replace(/\r?\n/g, '<br />');
				var contentDiv = $(this).closest(".replyForm").find("li").find("ul");
				var content = $(this).closest(".replyForm").find("li").find("ul").text();
				//alert(content);
				content = content.replace('<br/>', '\n');
				//alert(content);
				var contentInput = $("<input type='hidden' class='beforeContent' />");
				
				contentInput.attr("value", content);
				var textarea = $("<textarea name='content' class='content' rows='4' cols='80'></textarea>");
				var btn = $("<input type='button' class='editReplyBtn' value='reply' style='color: red;'/>");
				textarea.val(content);
				
				$(this).closest(".replyForm").append(contentInput).append(textarea).append(btn);
				contentDiv.remove();
				
				$(this).val("댓글 수정 취소");
			} else {
				var content = $(this).closest(".replyForm").find(".beforeContent").val();
				var editForm = $(this).closest(".replyForm");
				
				$(this).val("댓글 수정");
				editForm.find("textarea").remove();
				editForm.find(".editReplyBtn").remove();
				
				var contentUl = $("<ul></ul>");
				contentUl.text(content);
				editForm.find("li").append(contentUl);
				
			}
		});
		
	});

	$(document).on('click', '.replyWriteBtn', function(){
		$(this).closest(".replyForm").attr({
			"method" : "post",
			"action" : "<c:url value="/club/reply/write/${menuId}/${articleId}" />"
		});
		$(this).closest(".replyForm").submit();
	});
	
	
	$(document).on('click', '.editReplyBtn', function(){
		var cont = $(this).closest(".replyForm").find(".content").val();
		//var cont = $(this).closest(".replyForm").find(".editReply").val();
		//alert("cont : " + cont);
		
		if(!cont){
			alert("댓글을 입력하세요!");
			return;
		}
		
		$(this).closest(".replyForm").attr({
			"method" : "post",
			"action" : "<c:url value="/club/reply/modify/${menuId}/${articleId}" />"
		});
		$(this).closest(".replyForm").submit();
	});
</script>
<style>
	div {
		display: inline-block;
		vertical-align: top;
	}
</style>
<body class="cke_contents_ltr cke_show_borders">
	<div id="menubar">
		<jsp:include page="/WEB-INF/view/common/menu.jsp" />
	</div>
	<div >
	<h1>
		<c:forEach items="${currentMenu }" var="currentMenu" varStatus="index">
			${currentMenu.menuName }
			<c:if test="${not index.last }">></c:if> 
		</c:forEach>
	</h1>
	<hr/>
	<br/>
	
	<h2> ${club.subject } </h2><hr/>
	<p> ${club.content }</p><br/>
	<hr/>
	
	<!-- EL 변수 생성 -->
	<c:set var="depth" value="0" />
		<ul>
			<c:forEach items="${club.replyVO }" var="reply">
			<c:if test="${reply.level < prevLevel }">
					<c:forEach var="i" begin="0" end="${(prevLevel - reply.level) - 1 }" step="1">
						<c:set var="depth" value="${depth - 1 }" />
					</ul>
					</c:forEach>
				</c:if>
				<c:if test="${reply.level > prevLevel }">
					<c:set var="depth" value="${depth + 1 }" />
					<ul>
				</c:if>
				<form class="replyForm" >
					<input type="hidden" name="replyId" class="replyId" value="${reply.replyId }" />
					<input type="button" class="viewWriteReply" value="댓글 작성" />
					
					<c:if test="${sessionScope._MEMBER_.auth == 'ADM' || sessionScope._MEMBER_.memberId == reply.memberId }">
						<input type="button" class="editReply" value="댓글 수정" />
						<input type="button" class="removeReply" value="댓글 삭제" />
					</c:if>
					
					<li>${reply.memberId }<br/>
					<ul>${reply.content }</ul>
				</li>
				</form>
				
				<hr/>
				
				<c:set var="prevLevel" value="${reply.level }" />
			</c:forEach>
		</ul>
		<c:if test="${depth > 0 }">
		<c:forEach var="i" begin="0" end="${depth }" step="1">
			</ul>
		</c:forEach>
		</c:if>
		<form:form commandName="replyWriteForm">
			<input type="hidden" name="memberId" value="${sessionScope._MEMBER_.memberId }"/>
			<input type="hidden" name="articleId" value="${club.articleId }"/>
			<textarea name="content" id="content" rows="5" cols="100"></textarea>
			<input type="button" id="commentBtn" value="comment" style="color: red"/>
		</form:form>
		<a id="listA" href="<c:url value="/club/${menuId }"/>" style="text-decoration: none; color: black;">목록보기</a>
		<c:if test="${sessionScope._MEMBER_.auth == 'ADM' || sessionScope._MEMBER_.memberId == club.memberId }">
			<a id="edit" href="<c:url value="/club/modify/${menuId }/${articleId }" />" style="text-decoration: none; color: black;">수정하기</a>
			<a id="edit" href="<c:url value="/club/delete/${articleId }" />" style="text-decoration: none; color: black;">삭제하기</a>
		</c:if>
	</div>
	
	<div style="display:none;">
		<c:import url="http://localhost:3000"/>
	</div>
	
</body>
</html>