<!DOCTYPE html>
<html lang="pl">
<head>
	<meta charset="UTF-8">
	<title>Maszyna W dla PKW</title>
	<script src="assets/vendor/jquery/dist/jquery.min.js"></script>
	<script src="assets/vendor/angular/angular.min.js"></script>
	<link rel="stylesheet" href="assets/css/style.css">
	<link href='http://fonts.googleapis.com/css?family=Lato&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
</head>
<body>
	Na razie brzydko to wygląda bo robie funkcjonalność<br><br>

	<div class="control-panel">
		<input type="checkbox" name="handControls" id="handControls" value="0">
		<label for="handControls">sterowanie ręczne</label>
	</div>

	<div id="central-unit" class="central-unit" ng-controller="cpuController">
		<div id="counter" class="counter">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="address" class="address">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="acumulator" class="acumulator">
			<input type="text" class="acumulator__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="memory" class="memory">
			<input type="text" class="memory__address js-only-numbers js-quick-edit" value="0">
			<input type="text" class="memory__verbal js-only-numbers js-quick-edit" value="0">
			<div class="memory__table">

				<!-- TODO: wyświetlanie danych za pomocą angular js -->
				<?php 
					 $i = 0;
					 while($i++ < 32) :
				 ?>
				<div class="memory__table--row">
					<div class="memory__table--cell"><?php echo $i; ?></div>
					<input class="memory__table__data js-only-numbers js-quick-edit" value="0">
					<div class="memory__table--cell">stp 0</div>
				</div>
				<?php endwhile; ?>

			</div>
		</div>
	</div>

	<script src="assets/vendor/raphael/raphael-min.js"></script>
	<script src="assets/js/_main.js"></script>
</body>
</html>