
/* ------------------------------------------------------------------
	REGISTRATION FORM
--------------------------------------------------------------------- */

var form = "registration";
var alert = $(".alert:not(.alert-info)");


function validateEmail(email) {
   var email_field = email;
   var atpos = email_field.indexOf("@");
   var dotpos = email_field.lastIndexOf(".");
   if (atpos < 1 || ( dotpos - atpos < 2 )) {
       	return false;
   }
}

function formValidate() {
	var user_name       = document.forms[form]["user_name"].value;
	var user_pass       = document.forms[form]["user_pass"].value;
	var user_pass_check = document.forms[form]["user_pass_check"].value;
	var user_email		= document.forms[form]["user_email"].value;
	var login_found     = true;
	
	for(var i = 0; i < document.forms[form].length - 1; i++) {
		var field = document.forms["registration"][i].value;
		if (field === null || field === "") {
	    	$(alert).text("Jedno lub więcej pól jest nieuzupełnionych!");
	    	$(alert).fadeOut();
	    	$(alert).fadeIn();
	    	return false;			
		}
	}
		
	jQuery.ajax({
		  type: "GET",
		  url: 'FindLogin',
		  data: {login:user_name},
		  success: function (responseText) {
			if (responseText === "found") {
				login_found = true;
			} else {
				login_found = false;
			}
		  }, 
		  async: false
	});
	
	if (login_found) {
    	$(alert).text("Podany login jest już zajęty.");
    	$(alert).fadeOut();
    	$(alert).fadeIn();
    	return false;
	} else if (user_pass.length < 6) {
    	$(alert).text("Hasło powinno składać się z minimum 6 znaków!");
    	$(alert).fadeOut();
    	$(alert).fadeIn();
    	document.forms[form]["user_pass"].focus() ;
    	return false;			
	} else if (user_pass !== user_pass_check) {
    	$(alert).text("Hasła nie zgadają się!");
    	$(alert).fadeOut();
    	$(alert).fadeIn();
    	document.forms[form]["user_pass_check"].focus() ;
    	return false;			
	} else if (validateEmail(user_email) == false) {
       	$(alert).text("Niepoprawny adres email!");
       	$(alert).fadeOut();
    	$(alert).fadeIn();
       	document.forms[form]["user_email"].focus() ;
		return false;
	}
}


/* ------------------------------------------------------------------
	CREATE TOPIC FORM
--------------------------------------------------------------------- */

function createTopicValidate() {
	
	var field;
	
	// check if all fields are filled
	for(var i = 0; i < document.forms["createTopic"].length - 1; i++) {
		field = document.forms["createTopic"][i].value;
		if (field === null || field === "") {
	    	$(alert).text("Jedno lub więcej pól jest nieuzupełnionych!");
	    	$(alert).fadeOut();
	    	$(alert).fadeIn();
	    	return false;			
		}
	}
}

/* ------------------------------------------------------------------
	CREATE POST FORM
--------------------------------------------------------------------- */

function createPostValidate() {
	
	var field;
	
	// check if all fields are filled
	for(var i = 0; i < document.forms["createPost"].length - 1; i++) {
		field = document.forms["createPost"][i].value;
		if (field === null || field === "") {
	    	$(alert).text("Pole treści postu jest nieuzupełnione!");
	    	$(alert).fadeOut();
	    	$(alert).fadeIn();
	    	return false;			
		}
	}
}