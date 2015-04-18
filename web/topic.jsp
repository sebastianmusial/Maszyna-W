<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="header.jsp" %>
<%@ include file="view/navbar.jsp" %>

<sql:setDataSource var="snapshot" 
	driver="${initParam['DB_DRIVER']}"
    url="${initParam['DB_URL']}"
    user="${initParam['DB_USER']}"  
    password="${initParam['DB_PASS']}"/>
    
<sql:query dataSource="${snapshot}" var="topicInfo">
	SELECT concat(t.topicName,'')    AS topicName, 
		   concat(t.date,'')         AS topicDate, 
		   concat(u.login,'')        AS topicAuthor,
		   COUNT(DISTINCT r.replyID) AS postCount
	FROM Topics AS t, 
		 Users AS u,
		 Reply AS r
	WHERE t.topicID = ${param.id} AND 
		  t.topicID = r.topicID AND
		  t.userID = u.userID
</sql:query>

<sql:query dataSource="${snapshot}" var="results">
	SELECT concat(u.login,'')     AS postAuthor,
		   concat(r.date,'')      AS postDate,
		   concat(r.replyText,'') AS postBody
	FROM Reply AS r,
		 Topics AS t,
		 Users AS u
	WHERE t.topicID = ${param.id} AND
		  t.topicID = r.topicID AND
		  r.userID = u.userID
</sql:query>

<div class="container-fluid wrap">
	<%@ include file="view/navbar-forum.jsp" %>

	<section class="topic">	
		<header>
			<c:forEach var="topic" items="${topicInfo.rows}">
				<span class="glyphicon glyphicon-list-alt"></span>
					<h2>${topic.topicName}</h2>
					<fmt:parseDate value="${topic.topicDate}" var="date"/>
					<p>Rozpoczęty przez <strong>${topic.topicAuthor}</strong>, dnia <fmt:formatDate pattern="dd-MM-yyyy" value="${date}"/></p>				
				<div class="post-count">${topic.postCount} odpowiedzi w tym temacie</div>
			</c:forEach>
		</header>
		<main>
			<c:forEach var="post" items="${results.rows}">
				<div class="post">
					<div class="post-author col-md-2">
						<img src="assets/img/icon.png"/>
						${post.postAuthor}
					</div>
					<div class="post-body col-md-10">
						<p>Napisano: ${post.postDate}</p>
						<article>${post.postBody}</article>
					</div>
				</div>
			</c:forEach>
		</main>
	</section>
</div>

<%@ include file="footer.jsp" %>