<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<nav id="navbar" class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid wrap">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			  <span class="sr-only">Toggle navigation</span>
			  <span class="icon-bar"></span>
			  <span class="icon-bar"></span>
			  <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">Maszyna W</a>
		</div>

		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-main">
				<li class="active"><a href="index.jsp">Symulator <span class="sr-only">(current)</span></a></li>
				<li><a href="guestbook.jsp">Księga gości</a></li>
				<li><a href="forum.jsp">Forum</a></li>

				<c:if test="${sessionScope.loggedUser != null}">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Lista akcji<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
				    	<li><a href="create_topic.jsp"><span class="glyphicon glyphicon-plus"></span>Utwórz temat</a></li>
					</ul>
				</li>
				</c:if>
			</ul>

			<ul class="nav navbar-nav navbar-right">
			
				<c:if test="${sessionScope.loggedUser == null}">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						  <span class="glyphicon glyphicon-user"></span>
						  Witaj nieznajomy!<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu">
						  <li><a href="login.jsp">Logowanie</a></li>
						  <li><a href="register.jsp">Rejestracja</a></li>
						</ul>
					</li>				
				</c:if>
				
				<c:if test="${sessionScope.loggedUser != null}">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						  <span class="glyphicon glyphicon-user"></span>
						  Witaj ${sessionScope.loggedUser}!<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu">
						  <li>
						  	<a href="LogoutUser">Wyloguj się</a>
						  </li>
						</ul>
					</li>				
				</c:if>
				
			</ul>
		</div><!-- /.navbar-collapse -->

	</div> <!-- /.wrap -->
</nav>