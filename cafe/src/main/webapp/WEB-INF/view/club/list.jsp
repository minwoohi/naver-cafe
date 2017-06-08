<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<jsp:include page="/WEB-INF/resources/template/common_header.jsp" />
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js" />"></script>
<script type="text/javascript">

	$().ready(function(){
		
		$("#searchBtn").click(function(){
			alert(".searchForm");
			$(".searchForm").attr({
				"method" : "get",
				"action" : "<c:url value="/club/${menuId}" />"
			});
			$(".searchForm").submit();
			alert(".searchForm end");
		});
		
		<c:if test="${sessionScope._MEMBER_.auth == 'ADM'}" >
		$("#deleteCheckedArticles").click(function(){
			
			if( $("input[type=checkbox][name=deleteCheckedArticles]:checked").length == 0 ){
			    alert("삭제할 항목을 하나이상 체크해주세요.");
			    return;
			}
				
			$("#checkbox").attr({
				"method" : "post",
				"action" : "<c:url value="/club/deleteArticles/${menuId}" />"
			})
			$("#checkbox").submit();
			
		});
		
		$("#checkAll").click(function(){ // 전체 선택 버튼 클릭시
			var checked = $(this).prop("checked");
		
			if(checked){
				$(":checkbox[name=deleteCheckedArticles]").prop("checked", true);
			} else {
				$(":checkbox[name=deleteCheckedArticles]").prop("checked", false);
			}
		});
		
		</c:if>
	});
</script>
</head>
<body>
	<style>
		div {
			display: inline-block;
		}
		
		#content {
			width : 622px;
			vertical-align: top;
			text-align: center;	
		}
		
		#content > table {
			width: 100%;
			text-align: center;
			vertical-align: top;
			margin: 0 auto;
		}
		
	</style>
	<div id="menubar" >
		<jsp:include page="/WEB-INF/view/common/menu.jsp" />
	</div>
	
	<div id="content" >
		<h1>
			<c:forEach items="${currentMenu }" var="currentMenu" varStatus="index">
				${currentMenu.menuName }
				<c:if test="${not index.last }">></c:if> 
			</c:forEach>
		</h1><br/>
		
		<table>
			<tr>
				<c:if test="${sessionScope._MEMBER_.auth == 'ADM' }">
					<form id="checkbox">
					<th><input type="checkbox" id="checkAll" /></th>
				</c:if>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:if test="${empty clubList }">
				<tr>
					<td colspan="5">등록된 게시글이 존재하지 않습니다.</td>
				</tr>
			</c:if>
			<c:forEach items="${clubList }" var="club" varStatus="index">
				<tr>
					<c:if test="${sessionScope._MEMBER_.auth == 'ADM' }">
						<td><input type="checkbox" name="deleteCheckedArticles" value="${club.articleId }" /></td>
					</c:if>	
					<td>${club.articleId }</td>
					<td><a href="<c:url value="/club/read/${club.menuVO.menuId }/${club.articleId }" />" style="text-decoration: none;" class="w3-bar-item w3-button">${club.subject }</a>  [${club.repliesCount }]</td>
					<td>${club.memberVO.nickName }</td>
					<td>${club.createdDate }</td>
					<td>${club.readCount }</td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty clubList }">
		<form class="searchForm">
				${pager }
				<select name="searchType">
					<option value="1">제목</option>
					<option value="2">작성자</option>
					<option value="3">제목 + 작성자</option>
				</select>
				<input type="text" name="searchKeyword" value="${sessionScope._SEARCH_.searchKeyword }"  />
				<input type="button" class="w3-button w3-white w3-hide-small" value="Search" id="searchBtn" />
				<input type="button" value="검색 초기화" class="w3-button w3-white w3-hide-small" onclick="location.href='<c:url value="/club/${menuId}/init"/>';" />
				<c:if test="${not empty sessionScope._MEMBER_ }">
				<a href="<c:url value="/club/write/${menuId }"/>" style="text-decoration: none;" class="w3-bar-item w3-button">글쓰기</a>
				</c:if>
				<c:if test="${sessionScope._MEMBER_.auth == 'ADM' }">
					<input type="button" name="deleteCheckedArticles" id="deleteCheckedArticles" class="w3-button w3-white w3-hide-small" value="체크 글 삭제"  />
					</form>
				</c:if>
				
		</form>
		</c:if>
		<c:if test="${empty clubList }">
			<form class="searchForm">
				<c:if test="${not empty sessionScope._MEMBER_ }">
				<a href="<c:url value="/club/write/${menuId }"/>" style="text-decoration: none;" class="w3-bar-item w3-button">글쓰기</a>
				</c:if>
			</form>
		</c:if>
		
	</div>
	
<%-- 	 <div style="display:none;">
		<c:import url="http://localhost:3000"/>
	</div>
	
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js" />"></script>
<script type="text/javascript">
	$().ready(function(){
		newPostUrl = "https://www.naver.com";
		$("#hiddenBtn").click();
		
	});
</script> --%>
	
</body>
</html>