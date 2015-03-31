<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
				<li><a href="guestbook.php">Księga gości</a></li>
				<li><a href="forum.php">Forum</a></li>

				<!--<?php if($is_forum): ?>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Lista akcji<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
				     <li><a href="create_topic.php"><span class="glyphicon glyphicon-plus"></span>UtwÃ³rz temat</a></li>
				     <li><a href="create_category.php"><span class="glyphicon glyphicon-plus"></span>UtwÃ³rz kategoriÄ</a></a></li>
					</ul>
				</li>
				<?php endif; ?>-->
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
					  <span class="glyphicon glyphicon-user"></span>
					  Witaj nieznajomy!<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu">
					  <li><a href="login.php">Logowanie</a></li>
					  <li><a href="register.php">Rejestracja</a></li>
					</ul>
				</li>
			</ul>
		</div><!-- /.navbar-collapse -->

	</div> <!-- /.wrap -->
</nav>