<?php
		function db(){
			$conn=mysqli_connect("host_name","username","password","database_name");
			return $conn;

		}


?>