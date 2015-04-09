<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<%@ include file="header.jsp" %>
<%@ include file="view/navbar.jsp" %>

<section class="register-successful wrap">
  	
	<c:choose> 
	  <c:when test="${error == 1}">
		<div class="alert alert-danger" role="alert">
			<span class="glyphicon glyphicon-alert"></span><strong>Wystąpił błąd podczas rejestracji.</strong><br />
			Spróbój ponownie wracając do formularza <a href="register.jsp">rejestracji</a>.
		</div>
	  </c:when>
	  <c:otherwise>
	    <div class="alert alert-success" role="alert">
			<span class="glyphicon glyphicon-ok-sign"></span><strong>Rejestracja przebiegła pomyślnie.</strong><br />
			Od teraz możesz się <a href="login.jsp">zalogować</a> używając danych wpisanych podczas rejestracji.
		</div>
	  </c:otherwise>
	</c:choose>
</section>

<%@ include file="footer.jsp" %>