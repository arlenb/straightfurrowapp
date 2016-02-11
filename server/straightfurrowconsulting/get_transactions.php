<?php
	//read params
	$id = $_REQUEST['user_id'];
	$username = $_REQUEST['username'];
	$cust_id = $_REQUEST['customer_id'];
	if(strlen($id)==0 || strlen($username)==0){
		echo "ERROR: parameter reception";
		exit();
	}

	//form and check db connection
	$mysqli = new mysqli("localhost", 'arlenb_sfc_user', 'LYJJ?N66g$-6qBpg', "arlenb_straightfurrow");
	if ($mysqli->connect_errno) {
		printf("Connect failed: %s\n", $mysqli->connect_error);
		exit();
	}
	
	//validate username and id params
    $sql = "Select * from `users` where `id` = ".$id." and `username` = '".$username."'";
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
		echo "ERROR: invalid parameters";
		exit;
	}
	
	//fetch transactions for the validated user.
    $sql = "Select * from `transactions` where `owner_id` = ".$id." and `customer_id` = '".$cust_id."'";
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
	echo json_encode($myArray);

	if($result->num_rows != 0)$result->close();
	$mysqli->close();
?>