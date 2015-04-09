<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<%@ include file="header.jsp" %>
<%@ include file="view/navbar.jsp" %>

<div class="container-fluid wrap">
	<section class="forum">
	
		<table class="table table-category">
			<th colspan="3" class="category-header"><a href="category.php?id="><h3>Nazwa kategorii</h3></a></th>
				<tr>
					<td class="col-content">           
						<h4 class="topic-headline"><a href="topic.php?id=">Nazwa tematu</a></h4>
						<footer class="topic-footer">Napisany przez Moridin , 11 wrz 2014</footer>
					</td>
					<td class="col-views">   
						<p>7 odpowiedzi</p>
					</td>
					<td class="col-post">   
						<a href="topic.php?id=">rybak47</a>
						<footer class="post-footer">09 lut 2015</footer>
					</td>
				</tr>
		</table>
	</section>
</div>

<%@ include file="footer.jsp" %>