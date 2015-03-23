<?php 
/****************************************************** 
* connection.php 
* konfiguracja połączenia z bazą danych
* http://s24.hekko.pl/phpmyadmin/ 
******************************************************/ 

function config() { 
    // host
    $mysql_host = "mysql3.hekko.net.pl"; 
    // nazwa użytkonika
    $mysql_user = "sssebuss_bd"; 
    // hasło
    $mysql_pass = "maszynaW2015"; 
    // nazwa bazy danych
    $mysql_db = "sssebuss_bd"; 
    // nawiązujemy połączenie z serwerem MySQL 
    @mysql_connect($mysql_host, $mysql_user, $mysql_pass) 
    or die('Brak połączenlocia z serwerem MySQL.'); 
    // łączymy się z bazą danych 
    @mysql_select_db($mysql_db) 
    or die('Błąd wyboru bazy danych.'); 
} 

?>