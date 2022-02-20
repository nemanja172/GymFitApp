<?php
	
if ($_SERVER["REQUEST_METHOD"] =='POST'){

	$email=$_POST["email"];
	$geslo=$_POST["geslo"];

    include("dbConnect.php");
	$zbirka = dbConnect();

    $sql = "select * from uporabnik where email='".$email."' AND geslo='".$geslo."' ";
	
	$response = mysqli_query($zbirka, $sql);
	
	$result = array();
	$result['login'] = array();
	
	if(mysqli_num_rows($response) === 1 ){
		
		$row = mysqli_fetch_assoc($response);
		$index['Ime'] = $row['Ime'];
		$index['Email'] = $row['Email'];
		$index['Id'] = $row['ID_uporabnika'];
		$index['Tel'] = $row['Tel_stevilka'];
		$index['Spol'] = $row['Spol'];
		$index['Datum'] = $row['Datum_rojstva'];
		$index['Priimek'] = $row['Priimek'];
		
		array_push($result['login'], $index);
		
		$result['success']="1";
		$result['message']="success";
		echo json_encode($result);
		
		mysqli_close($zbirka);
			
		}else{
			
			$result['success']="0";
			$result['message']="error";
			echo json_encode($result);
			
			mysqli_close($zbirka);
		}
	}

?>
