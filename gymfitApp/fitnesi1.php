<?php
	include('dbConnect.php');
	$conn = dbConnect();
	
	if(!$conn) {
		die("Greska u konekciji: ". mysqli_connect_error());
	}
	$response = array();
	
	$sql_query = "SELECT ID_fitnesa, ime, lokacija, naslov, tip FROM fitnes";
	$result = mysqli_query($conn, $sql_query);
	
	if(mysqli_num_rows($result) > 0){
		$response['success'] = 1;
		$fitnesi = array();
		while ($row = mysqli_fetch_assoc($result)) {
			array_push($fitnesi, $row);
		}
		$response['fitnesi'] = $fitnesi;
	}
	else{
		$response['success']= 0;
		$response['message']= 'No data';
	}
	echo json_encode($response);
	mysqli_close($conn);
?>