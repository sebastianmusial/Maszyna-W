<div id="central-unit" class="central-unit">
	<div id="central-unit__extension-top" class="central-unit__extension">
		<div id="extension-ws" class="extension-ws" data-name="WS">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
	</div>
	<div id="central-unit__base" class="central-unit__base" data-top-bus="magistrala adresowa" data-down-bus="magistrala danych">
		<div id="counter" class="counter" data-name="L">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="address" class="address" data-name="I">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="acumulator" class="acumulator" data-name="AK">
			<input type="text" class="acumulator__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="memory" class="memory" data-top-name="A" data-down-name="S">
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
		<div id="extension-f" class="extension-f" data-name="F">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
	</div>
	<div id="central-unit__extension-bottom" class="central-unit__extension">
		<div id="register-x" class="register-x" data-name="X">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="register-y" class="register-y" data-name="Y">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="extension-rb" class="extension-rb" data-name="RB">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
		<div id="extension-g" class="extension-g" data-name="G">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
		</div>
	</div>
</div>

<div class="container-fluid control-panel">
  <div class="row">
  	<div class="col-xs-6">
  		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  			<div class="panel panel-default">
			    <div class="panel-heading panel-heading--clear" role="tab">
		 			<div class="checkbox checkbox--clear">
				      	<label class="collapse-headline hand-controls">
							<input type="checkbox" name="handControls" id="handControls" value="0">
							sterowanie ręczne
						</label>
					</div>
			    </div>
			</div>
			<div class="panel panel-default">
			    <div class="panel-heading panel-heading--clear" role="tab" id="headingZero">
				    <h4 class="panel-title">
					    <a data-toggle="collapse" data-parent="#accordion" href="#collapseZero" aria-expanded="true" aria-controls="collapseZero" class="collapse-headline">
						   Poziom śledzenia
				      	</a>
			      	</h4>
			    </div>
			    <div id="collapseZero" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingZero">
			      <div class="panel-body">
			      	<div class="radio">
		      			<label>
							<input type="radio" name="trace-lavel" id="trace-lavel0" class="js-type">
							niski (program)
						</label>
					</div>
					<div class="radio">
		      			<label>
							<input type="radio" name="trace-lavel" id="trace-lavel1" class="js-type">
							średni (rozkaz)
						</label>
					</div>
					<div class="radio">
		      			<label>
							<input type="radio" name="trace-lavel" id="trace-lavel2" class="js-type">
							wysoki (takt)
						</label>
					</div>
			      </div>
			    </div>
		    </div>
		  <div class="panel panel-default">
		    <div class="panel-heading panel-heading--clear" role="tab" id="headingOne">
			    <h4 class="panel-title">
				    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" class="collapse-headline">
					   Typ i składniki
			      	</a>
		      	</h4>
		    </div>
		    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
		      <div class="panel-body">
	      		<div class="col-xs-3">
	      			<div class="radio">
		      			<label>
							<input type="radio" name="type" id="type0" class="js-type">
							W
						</label>
					</div>
					<div class="radio">
		      			<label>
							<input type="radio" name="type" id="type1" class="js-type">
							W+
						</label>
					</div>
					<div class="radio">
		      			<label>
							<input type="radio" name="type" id="type2" class="js-type">
							L	
						</label>
					</div>
					<div class="radio">
		      			<label>
							<input type="radio" name="type" id="type3" class="js-type">
							EW	
						</label>
					</div>
				</div>
				<div class="col-xs-9">
					<div class="checkbox">
			  			<label>
				  			<input type="checkbox" name="extension0" id="extension0" class="js-extension" value="0">
							połączenie międzymagistralowe
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension7" id="extension7" class="js-extension" value="0">
							inkrementacja i dekrementacja akumulatora
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension6" id="extension6" class="js-extension" value="0">
							operacje logiczne w JAL
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension5" id="extension5" class="js-extension" value="0">
							rozszerzone operacje arytmetyczne w JAL
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension4" id="extension4" class="js-extension" value="0">
							obsługa stosu
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension1" id="extension1" class="js-extension" value="0">
							rejestr X
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension2" id="extension2" class="js-extension" value="0">
							rejestr Y
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension3" id="extension3" class="js-extension" value="0">
							wejście/wyjście
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension8" id="extension8" class="js-extension" value="0">
							dodatkowe znaczniki
						</label>
					</div>
				</div>
		      </div>
		    </div>
		  </div>
		  <div class="panel panel-default">
		    <div class="panel-heading panel-heading--clear" role="tab" id="headingTwo">
		      <h4 class="panel-title">
		        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" class="collapsed collapse-headline">
		          Architektura
		        </a>
		      </h4>
		    </div>
		    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
				<div class="panel-body">
				   <div class="col-xs-6">
					    <label>
					    	Liczba bitów adresowych 
					    	<input type="number" class="form-control js-only-numbers" value="5">
					    </label>
					</div>
					<div class="col-xs-6">
					    <label>
					    	Liczba bitów kodu
					   		<input type="number" class="form-control js-only-numbers" value="3">
					    </label>
					 </div>
		    	</div>
		    </div>
		  </div>
		  <div class="panel panel-default">
		    <div class="panel-heading panel-heading--clear" role="tab" id="headingThree">
		      <h4 class="panel-title">
		        <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" class="collapsed collapse-headline">
		          Adresy
		        </a>
		      </h4>
		    </div>
		    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
		      <div class="panel-body">
			      <table class="table table-hover">
			      	<thead>
			      		<th></th>
			      		<th>Adres (port)</th>
			      		<th>Przerwanie</th>
			      	</thead>
			      	<tbody>
			      		<tr>
			      			<td>Wejście</td>
			      			<td>
							    <input type="text" class="form-control js-only-numbers" value="1">
			      			</td>
			      			<td>
							    <input type="text" class="form-control js-only-numbers" value="2">
			      			</td>
			      		</tr>
			      		<tr>
			      			<td>Wyjście</td>
			      			<td>
							    <input type="text" class="form-control js-only-numbers" value="3">
			      			</td>
			      			<td>
							    <input type="text" class="form-control js-only-numbers" value="4">
			      			</td>
			      		</tr>
			      	</tbody>
			      </table>
		      </div>
		    </div>
		  </div>
		</div>
  	</div>  	
  	<div class="col-xs-6">
  		<textarea class="form-control area-height" rows="3"></textarea>
  	</div>
  </div>
</div>


