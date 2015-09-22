<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>

<%@include file="header.jsp" %>
<%@include file="view/navbar.jsp" %>

<div class="wrap">
	<div class="msg-box js-close-msg">
		<div class="msg-box__content">
			<div class="alert alert-dismissible" role="alert">
			  <button type="button" class="close"><span>&times;</span></button>
			  <span class="msg-txt">
			  	Lorem ipsum dolor sit amet enim. etiam ullamcorper. suspendisse a pellentesque dui non felis.
			  </span>
			</div>
		</div>
	</div>
	<section class="w-machine">
        <%@include file="view/simulator.jsp" %>
	</section>
</div>

<%@include file="footer.jsp" %>