<?php 
	//register.php

	// booleans
	$is_forum = false;

	// includes
	require "header.php";
	include "view/navbar.php";
?>

<section class="register-form wrap">
    <h3 class="signup">Zarejestruj się</h3>

<?php if($_SERVER['REQUEST_METHOD'] != 'POST'): ?>

    <form name="registration" method="post" action="" onsubmit="return formValidate()">
        <table class="form-container">

            <div class="alert alert-danger" role="alert"></div>

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
    </form>
 
<?php else:
        //the form has been posted without errors

        // TODO //
        $sql = "";
        //////////
                         
        $result = mysql_query($sql);
        if(!$result) {
            echo 'Something went wrong while registering. Please try again later.';
            //echo mysql_error(); //debugging purposes, uncomment when needed
        } else {
            echo 'Successfully registered. You can now <a href="signin.php">sign in</a> and start posting! :-)';
        }
        
endif; ?>

</section>

<?php require "footer.php"; ?>
