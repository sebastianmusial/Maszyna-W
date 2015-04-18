<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,pl.polsl.architecture.*" %>
<%
WMachine machine = (WMachine)session.getAttribute("wmachine");
%>

<div id="central-unit" class="central-unit" style="opacity: 0;">
	<div id="central-unit__extension-top" class="central-unit__extension">
		<div id="extension-ws" class="extension-ws" data-name="WS">
			<input type="text" class="element__data js-only-numbers js-quick-edit" value="0">
			
		<div id="extension-wyls" class="extension-wyls"></div>
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
		<div id="memory" class="memory">
			<div id="memory-address" class="memory-address" data-name="A">
				<input type="text" class="memory__address js-only-numbers js-quick-edit" value="0">
			</div>
			<div id="memory-data" class="memory-data" data-name="S">
				<input type="text" class="memory__verbal js-only-numbers js-quick-edit" value="0">
			</div>
			<div class="memory__table">

				<% for(int i = 0; i < 8; ++i) { %>
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
							<span id="MANUAL_CONTROL_CHECK_BOX">
								sterowanie ręczne
							</span>
						</label>
					</div>	
			    </div>
			</div>
			<div class="panel panel-default">
			    <div class="panel-heading panel-heading--clear" role="tab" id="headingZero">
				    <h4 class="panel-title">
					    <a id="TRACKING_LEVEL_PANEL" data-toggle="collapse" data-parent="#accordion" href="#collapseZero" aria-expanded="true" aria-controls="collapseZero" class="collapse-headline">
						   Poziom śledzenia
				      	</a>
			      	</h4>
			    </div>
			    <div id="collapseZero" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingZero">
			      <div class="panel-body">
			      	<div class="radio">
		      			<label>
							<input id="track-program" type="radio" name="trace-lavel" class="js-type">
							<span id="TRACKING_LEVEL_LOW_OPTION">
								niski (program)
							</span>
						</label>
					</div>
					<div class="radio">
		      			<label>
							<input id="track-command" type="radio" name="trace-lavel" class="js-type">
							<span id="TRACKING_LEVEL_MEDIUM_OPTION">
								średni (rozkaz)
							</span>
						</label>
					</div>
					<div class="radio">
		      			<label>
							<input id="track-tact" type="radio" name="trace-lavel" class="js-type">
							<span id="TRACKING_LEVEL_HIGH_OPTION">
								wysoki (takt)
							</span>
						</label>
					</div>
			      </div>
			    </div>
		    </div>
		  <div class="panel panel-default">
		    <div class="panel-heading panel-heading--clear" role="tab" id="headingOne">
			    <h4 class="panel-title">
				    <a id="TYPES_AND_COMPONENTS_PANEL" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" class="collapse-headline">
					   Typ i składniki
			      	</a>
		      	</h4>
		    </div>
		    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
		      <div class="panel-body">
	      		<div class="col-xs-3">
	      			<div class="radio">
		      			<label>
							<input id="architecture-w" type="radio" name="type" class="js-type">
							<span id="TYPE_W_RADIO_BUTTON">W</span>
						</label>
					</div>
					<div class="radio">
		      			<label>
							<input id="architecture-w-plus" type="radio" name="type" id="type1" class="js-type">
							<span id="TYPE_W_PLUS_RADIO_BUTTON">W+</span>
						</label>
					</div>
					<div class="radio">
		      			<label>
							<input id="architecture-l" type="radio" name="type" class="js-type">
							<span id="TYPE_L_RADIO_BUTTON">L</span>	
						</label>
					</div>
					<div class="radio">
		      			<label>
							<input id="architecture-ew" type="radio" name="type" class="js-type">
							<span id="TYPE_EW_RADIO_BUTTON">EW</span>	
						</label>
					</div>
				</div>
				<div class="col-xs-9">
					<div class="checkbox">
			  			<label>
				  			<input type="checkbox" name="extension0" id="extension0" class="js-extension" value="0">
							<span id="BUS_CONNECTION_CHECK_BOX">połączenie międzymagistralowe</span>
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension7" id="extension7" class="js-extension" value="0">
							<span id="INC_DEC_ACCUMULATOR_CHECK_BOX">inkrementacja i dekrementacja akumulatora</span>
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension6" id="extension6" class="js-extension" value="0">
							<span id="LOGICAL_OPERATIONS_ALU_CHECK_BOX">operacje logiczne w JAL</span>
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension5" id="extension5" class="js-extension" value="0">
							<span id="EXTENDED_ARYTHMETICAL_ALU_OPERATIONS_CHECK_BOX">rozszerzone operacje arytmetyczne w JAL</span>
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension4" id="extension4" class="js-extension" value="0">
							<span id="STACK_MANAGEMENT_CHECK_BOX">obsługa stosu</span>
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension1" id="extension1" class="js-extension" value="0">
							<span id="X_REGISTER_CHECK_BOX">rejestr X</span>
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension2" id="extension2" class="js-extension" value="0">
							<span id="Y_REGISTER_CHECK_BOX">rejestr Y</span>
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension3" id="extension3" class="js-extension" value="0">
							<span id="INPUT_OUTPUT_CHECK_BOX">wejście/wyjście</span>
						</label>
					</div>
					<div class="checkbox">
			  			<label>
							<input type="checkbox" name="extension8" id="extension8" class="js-extension" value="0">
							<span id="ADDITIONAL_MARKS_CHECK_BOX">dodatkowe znaczniki</span>
						</label>
					</div>
				</div>
		      </div>
		    </div>
		  </div>
		  <div class="panel panel-default">
		    <div class="panel-heading panel-heading--clear" role="tab" id="headingTwo">
		      <h4 class="panel-title">
		        <a id="ARCHITECTURE_TAB" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" class="collapsed collapse-headline">
		          Architektura
		        </a>
		      </h4>
		    </div>
		    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
				<div class="panel-body">
				   <div class="col-xs-6">
					    <label>
					    	<span id="ADDRESS_BIT_COUNT_INPUT">Liczba bitów adresowych</span> 
					    	<input id="address-bit-count" type="number" class="form-control js-only-numbers" value="5" min="5" max="16">
					    </label>
					</div>
					<div class="col-xs-6">
					    <label>
					    	<span id="CODE_BIT_COUNT_INPUT">Liczba bitów kodu</span>
					   		<input id="op-code-bit-count" type="number" class="form-control js-only-numbers" value="3">
					    </label>
					 </div>
		    	</div>
		    </div>
		  </div>
		  <div class="panel panel-default">
		    <div class="panel-heading panel-heading--clear" role="tab" id="headingThree">
		      <h4 class="panel-title">
		        <a id="ADDRESSES_TAB" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" class="collapsed collapse-headline">
		          Adresy
		        </a>
		      </h4>
		    </div>
		    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
		      <div class="panel-body">
			      <table class="table table-hover">
			      	<thead>
			      		<th></th>
			      		<th id="ADRESS_LABEL">Adres (port)</th>
			      		<th id="INTERRUPT_LABEL">Przerwanie</th>
			      	</thead>
			      	<tbody>
			      		<tr>
			      			<td id="ADDRESS_INPUT_LABEL">Wejście</td>
			      			<td>
							    <input id="input-port-address" type="text" class="form-control js-only-numbers" value="1">
			      			</td>
			      			<td>
							    <input type="text" class="form-control js-only-numbers" value="2">
			      			</td>
			      		</tr>
			      		<tr>
			      			<td id="ADDRESS_OUTPUT_LABEL">Wyjście</td>
			      			<td>
							    <input type="text" class="form-control js-only-numbers" value="3">
			      			</td>
			      			<td>
							    <input id="output-port-address" type="text" class="form-control js-only-numbers" value="4">
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
