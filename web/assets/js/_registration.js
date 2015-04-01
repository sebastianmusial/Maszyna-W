
/* ------------------------------------------------------------------
	REGISTRATION FORM
--------------------------------------------------------------------- */

var form = "registration";
var alert = $(".alert:not(.alert-info)");


function validateEmail() {
   var email_field = document.forms[form]["user_email"].value;
   var atpos = email_field.indexOf("@");
   var dotpos = email_field.lastIndexOf(".");
   if (atpos < 1 || ( dotpos - atpos < 2 )) {
       	$(alert).text("Niepoprawny adres email!");
       	$(alert).fadeOut();
    	$(alert).fadeIn();
       	document.forms[form]["user_email"].focus() ;
       	return false;
   }
   return true;
}

function formValidate() {
	var field1, field2;

	// check if all fields are filled
	for(var i = 0; i < document.forms[form].length - 1; i++) {
		field1 = document.forms["registration"][i].value;
		if (field1 === null || field1 === "") {
	    	$(alert).text("Jedno lub więcej pól jest nieuzupełnionych!");
	    	$(alert).fadeOut();
	    	$(alert).fadeIn();
	    	return false;			
		}
	}

	field1 = document.forms[form]["user_pass"].value;
	field2 = document.forms[form]["user_pass_check"].value;
	if (field1.length < 6) {
    	$(alert).text("Hasło powinno składać się z minimum 6 znaków!");
    	$(alert).fadeOut();
    	$(alert).fadeIn();
    	document.forms[form]["user_pass"].focus() ;
    	return false;			
	} else if (field1 !== field2) {
    	$(alert).text("Hasła nie zgadają się!");
    	$(alert).fadeOut();
    	$(alert).fadeIn();
    	document.forms[form]["user_pass_check"].focus() ;
    	return false;			
	}

	return validateEmail();
}