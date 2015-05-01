<%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,pl.polsl.architecture.*"%>

<div class="container-fluid control-panel">
	<div class="row">
		<div class="col-xs-5">
			<div class="row">
				<div class="col-xs-6 col-hand-controls">
					<label class="collapse-headline hand-controls"> <input
						type="checkbox" name="handControls" id="handControls" value="0">
						<span id="MANUAL_CONTROL_CHECK_BOX"> sterowanie ręczne </span>
					</label>
				</div>
				<div class="col-xs-6 btn-controls">
					<button id="run-tact" type="button"
						class="btn btn-default btn-tact tip" data-toggle="tooltip"
						title="takt (F7)">
						<i class="glyphicon glyphicon-fast-forward"></i>
					</button>
					<button id="run-command" type="button"
						class="btn btn-default btn-tact tip" data-toggle="tooltip"
						title="rozkaz (F8)">
						<i class="glyphicon glyphicon-step-forward"></i>
					</button>

					<button id="run-program" type="button"
						class="btn btn-default btn-tact tip" data-toggle="tooltip"
						title="program (F9)">
						<i class="glyphicon glyphicon-play"></i>
					</button>
				</div>
			</div>
			<ul class="nav nav-tabs" id="leftTab">
				<li class="active"><a href="#settings"><i
						class="glyphicon glyphicon-cog"> </i> Ustawienia</a></li>
				<li><a href="#inOutConsole"><i
						class="glyphicon glyphicon-transfer"> </i> Konsola wejścia-wyjścia</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane fade in active" id="settings">
					<div class="row">
						<div class="col-xs-12">
							<div class="panel-group" id="accordion" role="tablist"
								aria-multiselectable="true">
								<div class="panel panel-default" style="display: none;">
									<div class="panel-heading panel-heading--clear" role="tab"
										id="headingZero">
										<h4 class="panel-title">
											<a id="TRACKING_LEVEL_PANEL" data-toggle="collapse"
												data-parent="#accordion" href="#collapseZero"
												aria-expanded="true" aria-controls="collapseZero"
												class="collapse-headline"> Poziom śledzenia </a>
										</h4>
									</div>
									<div id="collapseZero" class="panel-collapse collapse"
										role="tabpanel" aria-labelledby="headingZero">
										<div class="panel-body">
											<div class="radio">
												<label> <input id="track-program" type="radio"
													name="trace-lavel" class="js-type"> <span
													id="TRACKING_LEVEL_LOW_OPTION"> niski (program) </span>
												</label>
											</div>
											<div class="radio">
												<label> <input id="track-command" type="radio"
													name="trace-lavel" class="js-type"> <span
													id="TRACKING_LEVEL_MEDIUM_OPTION"> średni (rozkaz) </span>
												</label>
											</div>
											<div class="radio">
												<label> <input id="track-tact" type="radio"
													name="trace-lavel" class="js-type"> <span
													id="TRACKING_LEVEL_HIGH_OPTION"> wysoki (takt) </span>
												</label>
											</div>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading panel-heading--clear" role="tab"
										id="headingOne">
										<h4 class="panel-title">
											<a id="TYPES_AND_COMPONENTS_PANEL" data-toggle="collapse"
												data-parent="#accordion" href="#collapseOne"
												aria-expanded="true" aria-controls="collapseOne"
												class="collapse-headline"> Typ i składniki </a>
										</h4>
									</div>
									<div id="collapseOne" class="panel-collapse collapse"
										role="tabpanel" aria-labelledby="headingOne">
										<div class="panel-body">
											<div class="col-xs-3">
												<div class="radio">
													<label> <input id="architecture-w" type="radio"
														name="type" class="js-type"> <span
														id="TYPE_W_RADIO_BUTTON">W</span>
													</label>
												</div>
												<div class="radio">
													<label> <input id="architecture-w-plus"
														type="radio" name="type" id="type1" class="js-type">
														<span id="TYPE_W_PLUS_RADIO_BUTTON">W+</span>
													</label>
												</div>
												<div class="radio">
													<label> <input id="architecture-l" type="radio"
														name="type" class="js-type"> <span
														id="TYPE_L_RADIO_BUTTON">L</span>
													</label>
												</div>
												<div class="radio">
													<label> <input id="architecture-ew" type="radio"
														name="type" class="js-type"> <span
														id="TYPE_EW_RADIO_BUTTON">EW</span>
													</label>
												</div>
											</div>
											<div class="col-xs-9">
												<div class="checkbox">
													<label> <input type="checkbox" name="extension0"
														id="extension0" class="js-extension" value="0"> <span
														id="BUS_CONNECTION_CHECK_BOX">połączenie
															międzymagistralowe</span>
													</label>
												</div>
												<div class="checkbox">
													<label> <input type="checkbox" name="extension7"
														id="extension7" class="js-extension" value="0"> <span
														id="INC_DEC_ACCUMULATOR_CHECK_BOX">inkrementacja i
															dekrementacja akumulatora</span>
													</label>
												</div>
												<div class="checkbox">
													<label> <input type="checkbox" name="extension6"
														id="extension6" class="js-extension" value="0"> <span
														id="LOGICAL_OPERATIONS_ALU_CHECK_BOX">operacje
															logiczne w JAL</span>
													</label>
												</div>
												<div class="checkbox">
													<label> <input type="checkbox" name="extension5"
														id="extension5" class="js-extension" value="0"> <span
														id="EXTENDED_ARYTHMETICAL_ALU_OPERATIONS_CHECK_BOX">rozszerzone
															operacje arytmetyczne w JAL</span>
													</label>
												</div>
												<div class="checkbox">
													<label> <input type="checkbox" name="extension4"
														id="extension4" class="js-extension" value="0"> <span
														id="STACK_MANAGEMENT_CHECK_BOX">obsługa stosu</span>
													</label>
												</div>
												<div class="checkbox">
													<label> <input type="checkbox" name="extension1"
														id="extension1" class="js-extension" value="0"> <span
														id="X_REGISTER_CHECK_BOX">rejestr X</span>
													</label>
												</div>
												<div class="checkbox">
													<label> <input type="checkbox" name="extension2"
														id="extension2" class="js-extension" value="0"> <span
														id="Y_REGISTER_CHECK_BOX">rejestr Y</span>
													</label>
												</div>
												<div class="checkbox">
													<label> <input type="checkbox" name="extension3"
														id="extension3" class="js-extension" value="0"> <span
														id="INPUT_OUTPUT_CHECK_BOX">wejście/wyjście</span>
													</label>
												</div>
												<div class="checkbox">
													<label> <input type="checkbox" name="extension8"
														id="extension8" class="js-extension" value="0"> <span
														id="ADDITIONAL_MARKS_CHECK_BOX">dodatkowe znaczniki</span>
													</label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading panel-heading--clear" role="tab"
										id="headingTwo">
										<h4 class="panel-title">
											<a id="ARCHITECTURE_TAB" data-toggle="collapse"
												data-parent="#accordion" href="#collapseTwo"
												aria-expanded="false" aria-controls="collapseTwo"
												class="collapsed collapse-headline"> Architektura </a>
										</h4>
									</div>
									<div id="collapseTwo" class="panel-collapse collapse"
										role="tabpanel" aria-labelledby="headingTwo">
										<div class="panel-body">
											<div class="row">
												<div class="col-xs-6">
													<span id="ADDRESS_BIT_COUNT_INPUT">Liczba bitów
														adresowych</span> <input id="address-bit-count" type="number"
														class="form-control js-only-numbers" value="5" min="5"
														max="9" />
												</div>
												<div class="col-xs-6">
													<span id="CODE_BIT_COUNT_INPUT">Liczba bitów kodu</span> <input
														id="op-code-bit-count" type="number"
														class="form-control js-only-numbers" value="3" min="3"
														max="8" />
												</div>
											</div>
											<div class="row">
												<div class="col-xs-12">
													<input id="accept-bit-count-change" type="button"
														value="Zatwierdź zmiany" class="btn btn-accept" />
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading panel-heading--clear" role="tab"
										id="headingThree">
										<h4 class="panel-title">
											<a id="ADDRESSES_TAB" data-toggle="collapse"
												data-parent="#accordion" href="#collapseThree"
												aria-expanded="false" aria-controls="collapseThree"
												class="collapsed collapse-headline"> Adresy </a>
										</h4>
									</div>
									<div id="collapseThree" class="panel-collapse collapse"
										role="tabpanel" aria-labelledby="headingThree">
										<div class="panel-body">
											<table class="table table-hover">
												<thead>
													<th></th>
													<th id="ADDRESS_INPUT_LABEL">Wejście</th>
													<th id="ADDRESS_OUTPUT_LABEL">Wyjście</th>
												</thead>
												<tbody>
													<tr>
														<td id="ADRESS_LABEL">Adres (port)</td>
														<td><input id="input-port-address" type="text"
															class="form-control js-only-numbers" value="1"></td>
														<td><input type="text"
															class="form-control js-only-numbers" value="3"></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane fade" id="inOutConsole">konsolka</div>
			</div>
		</div>

		<div class="col-xs-7">
			<ul class="nav nav-tabs" id="rightTab">
				<li class="active"><a href="#commands"><i
						class="glyphicon glyphicon-book"> </i> Rozkazy</a></li>
				<li><a href="#programs"><i
						class="glyphicon glyphicon-education"> </i> Programy</a></li>
			</ul>
			<div class="tab-content clear-bottom">
				<div class="tab-pane fade in active" id="commands">
					<%@include file="commands.jsp" %>
				</div>
				<div class="tab-pane fade" id="programs">
					<!--  tymczasowa kopia wyglądu taba rozkazów -->
					<%@include file="commands.jsp" %>
				</div>
			</div>
		</div>
	</div>
</div>