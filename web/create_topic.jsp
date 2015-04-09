<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<%@ include file="header.jsp" %>
<%@ include file="view/navbar.jsp" %>

<div class="container-fluid wrap">
	<section class="forum create-topic">
		<h4>Utwórz nowy temat</h4>
		<hr />
		<form name="createTopic" method="POST" action="CreateTopic" onsubmit="return formValidate()">
			<label>Tytuł:</label><input type="text" name="topic_name" id="topic-title"/><br />
			<label>Treść postu:</label>
			<textarea name="topic_body" class="form-control" rows="5" id="topic-body"></textarea>	
			<input class="create-topic-button" type="submit" value="Utwórz" />	
		</form>
	</section>
</div>

<%@ include file="footer.jsp" %>