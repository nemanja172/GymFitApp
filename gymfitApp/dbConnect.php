<?php

function dbConnect(){
	$servername = "localhost";
	$username = "seminar";
	$password = "seminar";
	$dbname = "gymfit";

	// Ustvarimo povezavo do podatkovne zbirke
	$zbirka = mysqli_connect($servername, $username, $password, $dbname);
	mysqli_set_charset($zbirka,"utf8");

	// Preverimo uspeh povezave
	if (mysqli_connect_error()) {
		printf("Povezovanje s podatkovnim strežnikom ni uspelo: %s\n", mysqli_connect_error());
		exit();
	} 	
	return $zbirka;
}

?>