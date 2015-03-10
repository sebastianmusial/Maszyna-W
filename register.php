<?php 
	//register.php

	// booleans
	$is_forum = false;

	// includes
	require "header.php";
	include "view/navbar.php";
?>

<div class="container-fluid wrap">
	<section class="register-form">

<?php 
echo '<h3 class="signup">Zarejestruj się</h3>';
 
if($_SERVER['REQUEST_METHOD'] != 'POST') {

	// echo - start
    echo ' 
        <form method="post" action="">
        		<table class="form-container">

    	    		<tr class="form-field username">
    		        	<td>Nazwa użytkownika:</td>
    		        	<td><input type="text" name="user_name" /></td>
    		        </tr>

    		        <tr class="form-field password">
    		        	<td>Hasło:</td> 
    		        	<td><input type="password" name="user_pass"></td>
    		        </tr>

    		        <tr class="form-field password">
    		        	<td>Powtórz hasło:</td>
    		        	<td><input type="password" name="user_pass_check"></td>
    		       	</tr>

    		       	<tr class="form-field email">
    		       		<td>E-mail:</td> 
    		       		<td><input type="email" name="user_email"></td>
    		       	</tr>

    	        </table>
    	    <input class="register-button" type="submit" value="Rejestracja" />
         </form>'; 
     // echo - end
}
else {
    $errors = array();
     
    if(isset($_POST['user_name']))
    {
        //the user name exists
        if(!ctype_alnum($_POST['user_name']))
        {
            $errors[] = 'The username can only contain letters and digits.';
        }
        if(strlen($_POST['user_name']) > 30)
        {
            $errors[] = 'The username cannot be longer than 30 characters.';
        }
    }
    else
    {
        $errors[] = 'The username field must not be empty.';
    }
     
     
    if(isset($_POST['user_pass']))
    {
        if($_POST['user_pass'] != $_POST['user_pass_check'])
        {
            $errors[] = 'The two passwords did not match.';
        }
    }
    else
    {
        $errors[] = 'The password field cannot be empty.';
    }
     
    if(!empty($errors)) /*check for an empty array, if there are errors, they're in this array (note the ! operator)*/
    {
        echo 'Uh-oh.. a couple of fields are not filled in correctly..';
        echo '<ul>';
        foreach($errors as $key => $value) /* walk through the array so all the errors get displayed */
        {
            echo '<li>' . $value . '</li>'; /* this generates a nice error list */
        }
        echo '</ul>';
    }
    else
    {
        //the form has been posted without, so save it
        //notice the use of mysql_real_escape_string, keep everything safe!
        //also notice the sha1 function which hashes the password

        // TODO
        $sql = "";
                         
        $result = mysql_query($sql);
        if(!$result)
        {
            //something went wrong, display the error
            echo 'Something went wrong while registering. Please try again later.';
            //echo mysql_error(); //debugging purposes, uncomment when needed
        }
        else
        {
            echo 'Successfully registered. You can now <a href="signin.php">sign in</a> and start posting! :-)';
        }
    }
}
 
?>

	</section>
</div>

<?php require "footer.php"; ?>
