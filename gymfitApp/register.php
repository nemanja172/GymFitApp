<?php

if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $ime = $_POST['ime'];
    $priimek = $_POST['priimek'];
    $geslo = $_POST['geslo'];
	$Datum_rojstva = $_POST['Datum_rojstva'];
	$Spol = $_POST['Spol'];
	$Tel_stevilka = $_POST['Tel_stevilka'];
	$email = $_POST['email'];
	

    require_once 'dbConnect.php';
	

    $sql = "INSERT INTO uporabnik (ime, priimek, geslo, Datum_rojstva, Spol, Tel_stevilka, email) VALUES ('$ime', '$priimek', '$geslo', '$Datum_rojstva', '$Spol', '$Tel_stevilka', '$email')";

    if ( mysqli_query($zbirka, $sql) ) {
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        //mysqli_close($zbirka);

    } else {

        $result["success"] = "0";
        $result["message"] = "error";

        echo json_encode($result);
        //mysqli_close($zbirka);
    }
}

?>