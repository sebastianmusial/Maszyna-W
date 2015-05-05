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
	SELECT concat(r.replyID,'')   AS postID,
		   concat (u.userID,'')   AS postAuthorID,
		   concat(u.login,'')     AS postAuthor,
		   concat(r.date,'')      AS postDate,
		   concat(r.replyText,'') AS postBody
	FROM Reply AS r,
		 Topics AS t,
		 Users AS u
	WHERE t.topicID = ${param.id} AND
		  t.topicID = r.topicID AND
		  r.userID = u.userID
	ORDER BY r.date ASC; 
</sql:query>

<div class="container-fluid wrap">
	<%@ include file="view/navbar-forum.jsp" %>

	<section class="topic">	
		<header>
			<c:forEach var="topic" items="${topicInfo.rows}">
				<span class="glyphicon glyphicon-list-alt"></span>
				<h2>${topic.topicName}</h2>
				<p>Rozpoczęty przez <strong>${topic.topicAuthor}</strong>, dnia ${topic.topicDate}</p>				
				<div class="post-count">${topic.postCount} odpowiedzi w tym temacie</div>
				<c:set var="count" value="${topic.postCount}"/>
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
					
					<c:if test="${sessionScope.loggedUser != null && sessionScope.loggedID == post.postAuthorID && sessionScope.privileges >= 10 && count != 1}">
						<form action="RemovePost" method="POST">	
							<input type="hidden" name="post_id" value="${post.postID}">
							<input type="hidden" name="topic_id" value="${param.id}">		
							<button type="submit" class="remove">Usuń post <span class="glyphicon glyphicon-remove"></span></button>
						</form>
					</c:if>
				</div>
			</c:forEach>
		</main>
	</section>
	
	<c:if test="${sessionScope.loggedUser != null}">
		<section class="create-post">
			<h3>Napisz odpowiedź</h3>
			<form name="createPost" method="POST" action="CreatePost" onsubmit="return createPostValidate()">
				<textarea name="post_body" class="form-control" rows="15" id="post_body"></textarea>
				<input type="hidden" name="topic_id" value="${param.id}">	
				<input class="create-post-button" type="submit" value="Utwórz" />	
			</form>
			<div class="alert alert-danger" role="alert"></div>
		</section>
	</c:if>	
	
	<c:if test="${sessionScope.loggedUser == null}">
		<section class="not-logged-warning">
			<div class="alert alert-info" role="alert"><span class="glyphicon glyphicon-warning-sign"></span> <a href="login.jsp">Zaloguj się</a> aby napisać odpowiedź.</div>
		</section>
	</c:if>
</div>

<%@ include file="footer.jsp" %>