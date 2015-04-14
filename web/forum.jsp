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
	SELECT t.topicID AS topicID, 
		   t.userID AS userID, 
		   t.topicName AS topicName, 
		   t.date AS date, 
		   u.login AS login,
		   COUNT(replyID) AS count
	FROM Topics AS t, Users AS u, Reply AS r
	WHERE t.userID = u.userID and t.topicID = r.topicID
	GROUP BY topicID;
</sql:query>

<div class="container-fluid wrap">
	<c:if test="${sessionScope.loggedUser != null}">
		<nav class="nav-forum">
			<div class="container-fluid">
				<div class="row">
					<ul>
						<li>Forum:</li>
						<li><a href="create_topic.jsp">Utwórz temat<span class="glyphicon glyphicon-plus"></span></a></li>
					</ul>					
				</div>
			</div>
		</nav>
	</c:if>	

	<section class="forum">	
		<table class="table table-category">
		
			<thead>
		        <tr><th colspan="3" class="category-header"><h3>Spolecznosc Maszyny W</h3></th></tr>
		        <tr>
		            <th class="title-header">Tytu wątku</th>
		            <th class="count-header">Liczba postów</th>
		            <th class="last-header">Ostatnio napisal</th>
		        </tr>
	    	</thead>
	    	
			<tbody>			
				<c:forEach var="row" items="${result.rows}">
					<tr>
						<td class="col-content">           
							<h4 class="topic-headline"><a href="topic.php?id=${row.topicID}">${row.topicName}</a></h4>							
	   						<footer class="topic-footer">Napisany przez <strong>${row.login}</strong>, w dniu <fmt:formatDate pattern="dd-MM-yyyy, HH:mm" value="${row.date}"/></footer>
						</td>																						
						<td class="col-views"><p>${row.count}</p></td>					
						<td class="col-post">   
							<strong>rybak47</strong>
							<footer class="post-footer">09 lut 2015</footer>
						</td>
					</tr>
				</c:forEach>				
			</tbody>
			
		</table>
	</section>
</div>

<%@ include file="footer.jsp" %>