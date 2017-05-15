<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/menu.css"/>" />
<!--  
<ul>
	<li>Menu 1</li>
	<li>
		<ul>
			<li>Menu 1-1</li>
			<li>Menu 1-2</li>
			<li>Menu 1-3</li>
		</ul>
	</li>
	<li>Menu 2</li>
	<li>
		<ul>
			<li>Menu 2-1</li>
			<li>Menu 2-2</li>
			<li>Menu 2-3</li>
		</ul>
	</li>
</ul>
-->
<!-- LINK : 방문 이전 링크, VISITED : 방문 이후 링크, HOVER : 마우스 오버, ACTIVE : 클릭시 -->
<ul>
<c:set var="depth" value="0" />

<c:forEach items="${menu}" var="menuItem" varStatus="index">
	<c:if test="${index.first }">
		<li><a href="<c:url value="${menuItem.menuUrl }" />"><strong>${menuItem.menuName }</strong></a></li>	
	</c:if>
	<!-- 하위에서 상위로 갈 때 -->
	<c:if test="${menuItem.level < prevLevel }">
		<c:set var="depth" value="${depth - 1 }" />
		</ul>
		<li><a href="<c:url value="${menuItem.menuUrl }" />"><strong>${menuItem.menuName }</strong></a></li>
	</c:if>
	<!-- 상위에서 하위로 갈 때 -->
	<c:if test="${menuItem.level > prevLevel }">
		<c:set var="depth" value="${depth + 1 }" />
		<ul>
		<li><a style="font-weight: 100; color: #98a0ad;" href="<c:url value="${menuItem.menuUrl }" />">${menuItem.menuName }</a></li>	
	</c:if>
	<c:if test="${menuItem.level == prevLevel }">
		<li><a style="font-weight: 100; color: #98a0ad;" href="<c:url value="${menuItem.menuUrl }" />">${menuItem.menuName }</a></li>	
	</c:if>
	<!-- set : 변수 저장하는 태그 -->
	<c:set var="prevLevel" value="${menuItem.level }" />
</c:forEach>
</ul>
<c:if test="${depth > 0 }">
	<c:forEach var="i" begin="0" end="${depth - 1 }" step="1">
		</ul>
	</c:forEach> 
</c:if>
