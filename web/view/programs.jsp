<%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,pl.polsl.architecture.*"%>

<div class="row">
	<div class="tabbable tabs-right">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#newProgram" data-toggle="tab"><i
					class="glyphicon glyphicon-pencil"></i> nowy</a></li>
			<li><a href="#settingsPrograms" data-toggle="tab"><i
					class="glyphicon glyphicon-wrench"></i> ustawienia</a></li>
			<li><a href="#a" data-toggle="tab">program 1</a></li>
			<li><a href="#b" data-toggle="tab">program 2</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane fade in active" id="newProgram">
				<form class="form-horizontal">
					<div class="input-group form-space">
					  <input type="text" class="form-control border-right--clear" placeholder="Nazwa programu">
					  <div class="input-group-addon">
				        <input type="checkbox" class="tip" data-toggle="tooltip"  data-original-title="argument" />
				      </div>
					  <div class="input-group-btn">
					  	<button type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="dodaj"><i class="glyphicon glyphicon-plus"></i></button>
					  </div>
					</div>
					<textarea class="form-control form-space" rows="3" placeholder="Kod programu"></textarea>
				</form>
			</div>
			<div class="tab-pane fade" id="settingsPrograms">
				<form class="form-horizontal">
					<div class="input-group form-space">
						<select class="form-control">
						  <option>Program 1</option>
						  <option>Program 2</option>
						  <option>Wszystko</option>
						</select>
						<div class="input-group-btn">
						  	<button type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="usuń"><i class="glyphicon glyphicon-trash"></i></button>
						  </div>
					</div>
					<div class="input-group form-space">
						<select class="form-control">
						  <option>Program 1</option>
						  <option>Program 2</option>
						</select>
						<div class="input-group-btn">
						  	<button type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="wczytaj"><i class="glyphicon glyphicon-level-up"></i></button>
						  </div>
					</div>
				</form>
			</div>
			<div class="tab-pane fade" id="a">
				<textarea class="form-control form-space" rows="3" placeholder="Kod rozkazu" disabled>Lorem ipsum dolor
				sit amet, charetra varius quam sit amet vulputate. Quisque mauris
				augue, molestie tincidunt condimentum vitae, gravida a
				liberoThirdamuno, ipsum dolor sit amet, consectetur adipiscing elit.
				Duis pharetra varius quam sit amet vulputate. Quisque mauris augue,
				molestie tincidunt condimentum vitae.Thirdamuno, ipsum dolor sit
				amet, consectetur adipiscing elit.
				</textarea>
				<div class="button-group">
					<button type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="Usuń"><i class="glyphicon glyphicon-trash"></i></button>
					<button type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="Edytuj"><i class="glyphicon glyphicon-edit"></i></button>
					<button type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="Zapisz"><i class="glyphicon glyphicon-floppy-saved"></i></button>
				</div>
			</div>
			<div class="tab-pane fade" id="b">
				<textarea class="form-control form-space" rows="3" placeholder="Kod rozkazu" disabled>Lorem ipsum </textarea>
				<div class="button-group">
					<button type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="Usuń"><i class="glyphicon glyphicon-trash"></i></button>
					<button type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="Edytuj"><i class="glyphicon glyphicon-edit"></i></button>
					<button type="button" class="btn btn-default tip" data-toggle="tooltip" data-original-title="Zapisz"><i class="glyphicon glyphicon-floppy-saved"></i></button>
				</div>
			</div>
		</div>
	</div>
</div>