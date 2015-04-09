<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ include file="header.jsp" %>
<%@ include file="view/navbar.jsp" %>

<sql:setDataSource var="snapshot" 
	driver="${initParam['DB_DRIVER']}"
    url="${initParam['DB_URL']}"
    user="${initParam['DB_USER']}"  
    password="${initParam['DB_PASS']}"/>

<sql:query dataSource="${snapshot}" var="result">
	SELECT * FROM Topics;
</sql:query>

<div class="container-fluid wrap">
	<section class="forum">	
		<table class="table table-category">
		
			<thead>
		        <tr>
		            <th colspan="3" class="category-header"><h3>Spolecznosc Maszyny W</h3></th>
		        </tr>
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
							<footer class="topic-footer">Napisany przez Moridin , 11 wrz 2014</footer>
						</td>
						
						
						<sql:query dataSource="${snapshot}" var="resCount">
							SELECT COUNT(replyID) AS count FROM Reply WHERE ${row.topicID} = Reply.topicID;
						</sql:query>
												
						<td class="col-views">   
							<c:forEach items="${resCount.rows}" var="result">
   								<p>${result.count}</p>
							</c:forEach>
						</td>
						
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