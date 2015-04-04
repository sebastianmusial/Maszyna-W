<div class="control-panel">
	<input type="checkbox" name="handControls" id="handControls" value="0">
	<label for="handControls">sterowanie ręczne</label>
</div>

<div id="central-unit" class="central-unit" ng-controller="cpuController">
	<div id="central-unit__extension-top" class="central-unit__extension">
		<div id="extension-ws" class="extension-ws">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
	</div>
	<div id="central-unit__base" class="central-unit__base">
		<div id="counter" class="counter">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="address" class="address">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="10">
		</div>
		<div id="acumulator" class="acumulator">
			<input type="text" class="acumulator__data js-only-numbers js-quick-edit" value="20">
		</div>
		<div id="memory" class="memory">
			<input type="text" class="memory__address js-only-numbers js-quick-edit" value="0">
			<input type="text" class="memory__verbal js-only-numbers js-quick-edit" value="0">
			<div class="memory__table">

				<% for(int i = 0; i < 64; ++i) { %>
				<div id="memory-row-<%= i %>" class="memory__table--row">
					<div class="memory__table--cell"><%= i %></div>
					<input id="memory-cell-<%= i %>-value" class="memory__table__data js-only-numbers js-quick-edit" value="0">
					<div id="memory-cell-<%= i %>-text" class="memory__table--cell">stp 0</div>
				</div>
				<% } %>

			</div>
		</div>
		<div id="bus-connection" class="bus-connection"></div>
		<div id="aritmetical-operations" class="aritmetical-operations"></div>
		<div id="logical-operations" class="logical-operations"></div>
		<div id="extension-iak" class="extension-iak"></div>
		<div id="extension-dak" class="extension-dak"></div>
		<div id="extension-f" class="extension-f">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
	</div>
	<div id="central-unit__extension-bottom" class="central-unit__extension">
		<div id="register-x" class="register-x">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="register-y" class="register-y">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="extension-rb" class="extension-rb">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="extension-g" class="extension-g">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
	</div>
</div>

<div class="control-panel">
	<div class="type">
		<h5>Typ:</h5>
		<input type="radio" name="type" id="type0" class="js-type">
		<label for="type0">W</label>
		<br>
		<input type="radio" name="type" id="type1" class="js-type">
		<label for="type1">W+</label>
		<br>
		<input type="radio" name="type" id="type2" class="js-type">
		<label for="type2">L</label>
		<br>
		<input type="radio" name="type" id="type3" class="js-type">
		<label for="type3">EW</label>
	</div>
	<div class="extensions">
		<h5>Składniki:</h5>
		<input type="checkbox" name="extension0" id="extension0" class="js-extension" value="0">
		<label for="extension0">połączenie międzymagistralowe</label>
		<br>
		<input type="checkbox" name="extension7" id="extension7" class="js-extension" value="0">
		<label for="extension7">inkrementacja i dekrementacja akumulatora</label>
		<br>
		<input type="checkbox" name="extension6" id="extension6" class="js-extension" value="0">
		<label for="extension6">operacje logiczne w JAL</label>
		<br>
		<input type="checkbox" name="extension5" id="extension5" class="js-extension" value="0">
		<label for="extension5">rozszerzone operacje arytmetyczne w JAL</label>
		<br>
		<input type="checkbox" name="extension4" id="extension4" class="js-extension" value="0">
		<label for="extension4">obsługa stosu</label>
		<br>
		<input type="checkbox" name="extension1" id="extension1" class="js-extension" value="0">
		<label for="extension1">rejestr X</label>
		<br>
		<input type="checkbox" name="extension2" id="extension2" class="js-extension" value="0">
		<label for="extension2">rejestr Y</label>
		<br>
		<input type="checkbox" name="extension3" id="extension3" class="js-extension" value="0">
		<label for="extension3">wejście/wyjście</label>
		<br>
		<input type="checkbox" name="extension8" id="extension8" class="js-extension" value="0">
		<label for="extension8">dodatkowe znaczniki</label>
	</div>
</div>