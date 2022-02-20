<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['ID_uporabnika']) && isset($_POST['ID_fitnesa']) && isset($_POST['datum'])) {
    if ($db->dbConnect()) {
        if ($db->reservation("termin", $_POST['ID_uporabnika'], $_POST['ID_fitnesa'], $_POST['datum'])) 
		{
				http_response_code(204); 		//OK with no content
				header("location:uspesnarezervacija.php");
			}
			else
			{
				http_response_code(500);  		//Internal Server Error
				if($DEBUG)	
				{
					pripravi_odgovor_napaka(mysqli_error($zbirka));
				}
			}
    } else echo "Povezovanje s podatkovnim strežnikom ni uspelo:";
} else echo "Vsa polja so obvezna";
?>