<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<%@ include file="header.jsp" %>
<%@ include file="view/navbar.jsp" %>

<div class="container-fluid wrap">
	<section class="forum create-topic">
		<div class="container-fluid">
			<div class="alert alert-info" role="alert">* To pole jest wymagane</div>
			<h4>Utwórz nowy temat</h4>
			<hr />
			<form name="createTopic" method="POST" action="CreateTopic" onsubmit="return createTopicValidate()">
				<label>Tytuł: *</label><input type="text" name="topic_name" placeholder="Tutaj wpisz nazwę tematu" id="topic-title"/><br />
				<label>Treść postu: *</label>
				<textarea name="topic_body" class="form-control" rows="20" id="topic-body"></textarea>	
				<input class="create-topic-button" type="submit" value="Utwórz" />	
			</form>
			<div class="row">
				<div class="col-md-6">
					<div class="alert alert-danger" role="alert"></div>
				</div>
				<div class="col-md-6">
				</div>
			</div>
		</div>
	</section>
</div>
	
<%@ include file="footer.jsp" %>