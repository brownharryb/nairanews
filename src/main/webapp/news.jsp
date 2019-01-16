<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title></title>

 
<link href='<spring:url value="/static/css/main.css"/>' rel="stylesheet" />
<link rel="icon" href="images/favicon.png">
</head>

<body>	

<nav>
	<select class="newspaper-select">
		<c:forEach items="${newsPapers}" var="item">
			<option <c:if test = "${currentPaper.getId() == item.getId()}">Selected</c:if> value="${item.getId()}">${item.getName()}</option>
		</c:forEach>
	</select>
	
</nav>

<div class="container-wrapper">
<c:forEach items="${news}" var="item">	
<div class="container">
	
  <div class="column">
    <div class="post-module">
      <div class="thumbnail">
        <img src="${item.imageUrl}"/>
      </div>
      <!-- Post Content-->
      <div class="post-content">
        <div class="category">${item.newsPaper.name}</div>
        <a href="${item.sourceUrl}">
        <h1 class="title">${item.headline}</h1>
        </a>
        <p class="description">${item.content}</p>
        <div class="post-meta"><span class="timestamp"><fmt:formatDate value="${item.date}" pattern="MMMM dd, yyyy"/></span></div>
      </div>
    </div>
  </div>
</div>
</c:forEach>
<div>

  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script type="text/javascript" src='<spring:url value="/static/js/main.js"/>'></script>
</body>

</html>