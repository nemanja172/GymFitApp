<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['ime']) && isset($_POST['priimek']) && isset($_POST['geslo']) && isset($_POST['Datum_rojstva']) && isset($_POST['Spol']) && isset($_POST['Tel_stevilka']) && isset($_POST['email'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("uporabnik", $_POST['ime'], $_POST['priimek'], $_POST['geslo'], $_POST['Datum_rojstva'], $_POST['Spol'], $_POST['Tel_stevilka'], $_POST['email'])) {
            echo "Uspesna registracija";
        } else echo "Registracija ni uspela";
    } else echo "Napaka: ni konekcije z bazo";
} else echo "Vsa polja so obvezna";
?>
