<?php
	echo "Hashing Example<br><br>";
	
	$str = "123456";
	echo "Let's hash: '".$str."'<br>";
	
	echo "Result:>".hash('sha256', $str)."<";

?>