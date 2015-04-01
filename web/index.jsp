<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>

<%@include file="header.jsp" %>
<%@include file="view/navbar.jsp" %>

<div id="test_interaction">
	<form>
		<input id="run_test_interaction" type="button" value="click" />
	</form>
</div>
<div class="wrap">
	<section class="w-machine">
        <%@include file="view/maszyna-w.jsp" %>
	</section>
</div>

<%@include file="footer.jsp" %>