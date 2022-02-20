<?php
	
if ($_SERVER["REQUEST_METHOD"] =='POST'){

	$id=$_POST["ID_uporabnika"];

    include("dbConnect.php");
	$zbirka = dbConnect();

    $sql = "select * from uporabnik where ID_uporabnika='$id' ";
	
	$response = mysqli_query($zbirka, $sql);
	
	$result = array();
	$result['read'] = array();
	
	if(mysqli_num_rows($response) === 1 ){
		
		if($row = mysqli_fetch_assoc($response)){
			$h['Ime'] = $row['Ime'];
			$h['Email'] = $row['Email'];
			$h['Datum'] = $row['Datum_rojstva'];
			$h['Spol'] = $row['Spol'];
			$h['Tel'] = $row['Tel_stevilka'];
			$h['Priimek'] = $row['Priimek'];
			
			array_push($result['read'], $h);
			
			$result['success']="1";
			echo json_encode($result);
		}
	}
}
else
{	
	$result['success']="0";
	$result['message']="error";
	echo json_encode($result);
	
	//mysqli_close($zbirka);
}

?>
