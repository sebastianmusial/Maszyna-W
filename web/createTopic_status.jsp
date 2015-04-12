<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<%@ include file="header.jsp" %>
<%@ include file="view/navbar.jsp" %>

<section class="createTopic-successful wrap">
  	
	<c:choose> 
	  <c:when test="${error}">
		<div class="alert alert-danger" role="alert">
			<span class="glyphicon glyphicon-alert"></span><strong>Wystąpił błąd podczas dodawania tematu.</strong><br />
			Spróbój ponownie wracając do formularza <a href="create_topic.jsp">tworzenia tematu</a>.
		</div>
	  </c:when>
	  <c:otherwise>
	    <div class="alert alert-success" role="alert">
			<span class="glyphicon glyphicon-ok-sign"></span><strong>Nowy temat pomyślnie utworzony</strong><br />
			Za chwilę nastąpi przekierowanie do forum.</div>
			<% response.setHeader("Refresh", "5;url=forum.jsp"); %>
	  </c:otherwise>
	</c:choose>
</section>

<%@ include file="footer.jsp" %>