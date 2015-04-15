<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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