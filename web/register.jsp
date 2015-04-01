<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>




<%@ include file="../header.jsp" %>
<%@ include file="../view/navbar.jsp" %>

<sql:setDataSource 
	var="database" 
	driver="com.mysql.jdbc.Driver" 
	url="jdbc:mysql://localhost:3306/test" 
	user="root" 
	password=""/>

<section class="register-form wrap">
    <h3 class="signup">Zarejestruj się</h3>

    <form name="registration" method="post" action="RegisterUser" onsubmit="return formValidate()"> 
          
        <div class="alert alert-info" role="alert">* To pole jest wymagane</div>
        <div class="alert alert-danger" role="alert"></div>
        
        <table class="form-container">
            <tr class="form-field username">
                <td>Nazwa użytkownika *</td>
                <td><input type="text" name="user_name" /></td>
            </tr>

            <tr class="form-field password">
                <td>Hasło *</td> 
                <td><input type="password" name="user_pass"></td>
            </tr>

            <tr class="form-field password">
                <td>Powtórz hasło *</td>
                <td><input type="password" name="user_pass_check"></td>
            </tr>
            
            <tr class="form-field email">
                <td>E-mail *</td> 
                <td><input type="email" name="user_email"></td>
            </tr>      
        </table>
        
        <input class="register-button" type="submit" value="Rejestracja" />        
    </form>
</section>

<%@ include file="../footer.jsp" %>