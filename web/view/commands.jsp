<%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,pl.polsl.architecture.*"%>

<div class="row">
	<div class="tabbable tabs-right">
		<ul class="nav nav-tabs">
			<li class="active fixed"><a href="#newCommand" data-toggle="tab"><i
					class="glyphicon glyphicon-pencil"></i> nowy</a></li>
			<li class="fixed"><a href="#settingsCommand" data-toggle="tab"><i
					class="glyphicon glyphicon-wrench"></i> ustawienia</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane fade in active fixed" id="newCommand">
				<form class="form-horizontal">
					<div class="input-group form-space">
					  <input type="text" class="form-control border-right--clear" placeholder="Nazwa rozkazu">
					  <div class="input-group-addon">
				        <input type="checkbox" class="tip" data-toggle="tooltip"  data-original-title="argument" />
				      </div>
					  <div class="input-group-btn">
					  	<button type="button" class="btn btn-default tip btn-right" data-toggle="tooltip" data-original-title="dodaj"><i class="glyphicon glyphicon-plus"></i></button>
					  </div>
					</div>
					<textarea class="form-control form-space" rows="3" placeholder="Kod rozkazu"></textarea>
				</form>
			</div>
			<div class="tab-pane fade fixed" id="settingsCommand">
				<form class="form-horizontal">
					<div class="input-group form-space">
						<select id="selectCommand" class="form-control" data-selected="-1">
							<option class="fixed">Wszystko</option>
						</select>
						<span class="input-group-btn">
						  	<button id="deleteCommand" type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="usuń"><i class="glyphicon glyphicon-trash"></i></button>
						  </span>
					</div>
					<div class="input-group form-space">
						<select id="selectCommandList" class="form-control" data-selected="-1">
						  <option>Lista podstawowa</option>
						  <option>Lista dodatkowa</option>
						  <option>Jakaś jeszcze lista</option>
						</select>
						<span class="input-group-btn">
						  	<button type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="wczytaj"><i class="glyphicon glyphicon-level-up"></i></button>
						  </span>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>