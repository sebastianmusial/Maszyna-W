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

<sql:query dataSource="${snapshot}" var="result">
	SELECT concat(t.topicID,'')      AS topicID, 
		   concat(t.userID,'')       AS authorID, 
		   concat(t.topicName,'')    AS topicName, 
		   concat(t.date,'')         AS topicDate, 
		   concat(u.login,'')        AS topicAuthor,
		   COUNT(DISTINCT r.replyID) AS postCount,
		   concat(u2.login,'')       AS lastUser, 
		   max(r2.date)              AS lastDate 
	FROM Topics AS t, 
		 Users AS u, 
		 Reply AS r, 
		 Users as u2, 
		 Reply as r2
	WHERE t.userID = u.userID AND 
		  t.topicID = r.topicID AND 
		  t.topicID = r2.topicID AND 
		  r2.userID = u2.userID
	GROUP BY topicID
	ORDER BY r2.date DESC;
</sql:query>

<div class="container-fluid wrap">
	<%@ include file="view/navbar-forum.jsp" %>

	<section class="forum">	
		<table class="table table-category">
		
			<thead>
		        <tr><th colspan="3" class="category-header"><h3>Spoleczność Maszyny W</h3></th></tr>
		        <tr>
		            <th class="title-header">Tytuł wątku</th>
		            <th class="count-header">Liczba postów</th>
		            <th class="last-header">Ostatnio napisał</th>
		        </tr>
	    	</thead>
	    	
			<tbody>			
				<c:forEach var="row" items="${result.rows}">
					<tr>
						<td class="col-content">  
							<h4 class="topic-headline"><a href="topic.jsp?id=${row.topicID}"><span class="glyphicon glyphicon-list-alt"></span>${row.topicName}</a></h4>	
							<fmt:parseDate value="${row.topicDate}" var="date"/>
	   						<footer class="topic-footer">Napisany przez <strong>${row.topicAuthor}</strong>, w dniu <fmt:formatDate pattern="yyyy-MM-dd" value="${date}"/></footer>
						</td>																						
						<td class="col-views">
							<p>${row.postCount}</p>
						</td>					
						<td class="col-post">   										
							<strong>${row.lastUser}</strong>
							<footer class="post-footer"><fmt:formatDate pattern="dd-MM-yyyy, HH:mm" value="${row.lastDate}"/></footer>
						</td>
					</tr>
				</c:forEach>				
			</tbody>
			
		</table>
	</section>
</div>

<%@ include file="footer.jsp" %>