<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>

<div class="wrap">
	<footer class="footer-site">
		Developed by Michał Rakoczy, Sebastian Musiał, Dawid Poloczek, <br /> 
		Tomasz Rzepka, Józef Flakus <br />
		Have fun! <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %>
	</footer>
</div>
	<script src="assets/vendor/raphael/raphael-min.js"></script>
	<script src="assets/vendor/jquery/dist/jquery.min.js"></script>
	<script src="assets/vendor/angular/angular.min.js"></script>
	<script src="assets/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
	
	<script src="assets/js/_mappings.js"></script>
	<script src="assets/js/_view.js"></script>
	<script src="assets/js/_registration.js"></script>
	<script src="assets/js/interaction/_registers.js"></script>
	<script src="assets/js/interaction/_signals.js"></script>
	<script src="assets/js/interaction/_memory.js"></script>
	<script src="assets/js/interaction/_settings.js"></script>
	<script src="assets/js/interaction/_interaction.js"></script>
	<script src="assets/js/interaction/_runner.js"></script>
	<script src="assets/js/_ready.js"></script>
</body>
</html>