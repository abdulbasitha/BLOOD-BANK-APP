<?php
		include_once('db.php');
		$conn=db();
		$result=array();
		$name=strtolower($_GET['name']);
		$phno=strtolower($_GET['phno']);
		$gp=strtolower($_GET['gp']);
		$place=strtolower($_GET['place']);
		$age=strtolower($_GET['age']);
// 		echo($year);
		$submit=mysqli_query($conn,"SELECT* from blood_bank_app_myl where gp='$gp' AND phno='$phno'");
		$chk=mysqli_affected_rows($conn);
		
	// Creating date object.
		
		if(!$conn ||($gp ==NULL)){
				$result[]="DBER";
				//$result['msg']="Sorry ! this feature is temporarily blocked";
				
		}
		else{
			if($chk!=0){
			
				$result[]="ALRDREG";
			//	$result['msg']="Warning ! You are already registered";
			}
			else{
			$submit=mysqli_query($conn,"INSERT INTO `blood_bank_app_myl` (`id`, `name`, `phno`, `gp`, `place`, `age`) VALUES (NULL, '$name', '$phno', '$gp', '$place', '$age');");
			if($submit==1){
				
				$result[]="successfully";
			//	$result['msg']="Success! Thank you for your support";
			}
			else
			{
				$result[]="error";
				//$result['msg']="Error! Oops something went wrong-(#P031)";
			}
		}
		}

echo json_encode($result);





?>