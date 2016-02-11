<?php
	//read params
	$username = $_REQUEST['username'];
	$pass_hash = $_REQUEST['pass_hash'];
	if(strlen($pass_hash)==0 || strlen($username)==0){
		echo "ERROR: parameter reception";
		exit();
	}

	//form and check db connection
	$mysqli = new mysqli("localhost", 'arlenb_sfc_user', 'LYJJ?N66g$-6qBpg', "arlenb_straightfurrow");
	if ($mysqli->connect_errno) {
		printf("Connect failed: %s\n", $mysqli->connect_error);
		exit();
	}
	
	//validate username and password
    $sql = "Select `id`,`password_hash` from `users` where `username` = '".$username."'";
	$myArray = array();
	$result = $mysqli->query($sql);
	if($result->num_rows == 0){
		echo $mysqli->error;
	}
	else{
		while($row = $result->fetch_array(MYSQL_ASSOC)) {
				$myArray[] = $row;
		}
	}
	if(sizeof($myArray)==0){
		echo "ERROR: user not found";
		exit;
	}
	
	$user_id = $myArray[0]['id'];
	$db_hash = $myArray[0]['password_hash'];
	
	if(strcmp($pass_hash,$db_hash)==0){
		echo json_encode($myArray);
		exit;
	}
	
	echo "ERROR: invalid password";

	if($result->num_rows != 0)$result->close();
	$mysqli->close();
?>