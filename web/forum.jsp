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
SELECT 
	CONCAT(t.topicID,'')      AS topicID, 
	CONCAT(t.userID,'')       AS authorID, 
	CONCAT(t.topicName,'')    AS topicName, 
	CONCAT(t.DATE,'')         AS topicDate, 
	CONCAT(u.login,'')        AS topicAuthor, 
	COUNT(DISTINCT r.replyID) AS postCount, 
	CONCAT(u2.login,'') 	  AS lastUser, 
	CONCAT(r2.DATE, '') 	  AS lastDate
FROM Topics AS t,
	Users AS u,
	Reply AS r,
	Users AS u2,
	Reply AS r2
WHERE t.userID = u.userID AND
	t.topicID = r.topicID AND
	t.topicID = r2.topicID AND
	u2.userID = r2.userID AND
	r2.replyID = 
 	(
    SELECT MAX(r3.replyID)
    FROM Reply r3
    WHERE r3.topicID = t.topicID
  	)
GROUP BY topicID
ORDER BY r2.DATE DESC; 
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
							<fmt:parseDate value="${row.topicDate}" var="date1"/>
	   						<footer class="topic-footer">Założony przez <strong>${row.topicAuthor}</strong>, w dniu <fmt:formatDate pattern="yyyy-MM-dd" value="${date1}"/></footer>
						</td>																						
						<td class="col-views">
							<p>${row.postCount}</p>
						</td>					
						<td class="col-post">   										
							<strong>${row.lastUser}</strong>
							<fmt:parseDate value="${row.lastDate}" var="date2"/>
							<footer class="post-footer"><fmt:formatDate pattern="dd-MM-yyyy" value="${date2}"/></footer>
						</td>
					</tr>
				</c:forEach>				
			</tbody>
			
		</table>
	</section>
</div>

<%@ include file="footer.jsp" %>