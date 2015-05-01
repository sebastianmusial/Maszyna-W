<%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,pl.polsl.architecture.*"%>
<%
	WMachine machine = (WMachine) session.getAttribute("wmachine");
%>

<div id="loader" class="loader"></div>
<div id="central-unit">
	<%@include file="central-unit.jsp" %>
	<%@include file="settings.jsp" %>
</div>