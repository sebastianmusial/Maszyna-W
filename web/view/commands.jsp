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
						</select>
						<span class="input-group-btn">
						  	<button id="deleteCommand" type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="usuń"><i class="glyphicon glyphicon-trash"></i></button>
						</span>
					</div>
					<div class="input-group form-space">
						<select id="selectCommandsList" class="form-control" data-selected="-1">
						</select>
						<span class="input-group-btn">
						  	<button id="deleteCommandsList" type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="usuń"><i class="glyphicon glyphicon-trash"></i></button>
						</span>
					</div>
					<div>
						<label><span id="NEW_COMMANDS_LIST">Nowa lista rozkazów</span>:</label>
						<div class="input-group form-space">
							<input id="newCommandsListName" class="form-control" type="text" />
							<span class="input-group-btn">
							  	<button id="createEmptyCommandsList" type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="nowa lista"><i class="glyphicon glyphicon-file"></i></button>
							</span>
							<span class="input-group-btn">
							  	<button id="cloneCurrentCommandsList" type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="kopiuj aktualną"><i class="glyphicon glyphicon-duplicate"></i></button>
							</span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>