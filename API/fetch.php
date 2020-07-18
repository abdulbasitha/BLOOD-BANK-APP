<?php
include_once('db.php');
$conn=db();
$gp=strtolower($_GET['gp']);
$place=strtolower($_GET['place']);

		$result=array();
	
		
 		if(!$conn){
        
        
        $result [] ="failure";
 			
        
 		}
 		else
 		{ 
 		    

 			if((($place=="null")||($place==" ")||( preg_match('/\s/',$place))))
 			{
 				
 				$query=mysqli_query($conn,"SELECT * from blood_bank_app_myl where gp='$gp' and expiry < CURRENT_DATE ORDER BY name");
 			}
 			else
 			{
 				$query=mysqli_query($conn,"SELECT * from blood_bank_app_myl where gp='$gp' and expiry < CURRENT_DATE and place LIKE '%".$place."%' ORDER BY name");
 				
 			}
 			$i=1;
 			 $coun=mysqli_affected_rows($conn);
 			 if($coun!=0){
 			 
 			 while($row=mysqli_fetch_assoc($query))
 			 {

 			 	$result[]=$row;
 			 	$i++;
                

 			 }
 			 }
 			 else{
 			     
 			     	$result[]="failure";
 			     
 			 }
 					


 					
						


 			

 		}
 		
		
echo json_encode($result);




?>