<?php

// Database configuration


// Connect with the database
$db =  mysqli_connect("host_name","username","password","database_name");

// Get search term
$searchTerm = $_GET['term'];

// Get matched data from skills table
$query = mysqli_query($db,"SELECT * FROM blood_bank WHERE place LIKE '%".$searchTerm."%' ORDER BY place ASC");

// Generate skills data array
$skillData = array();
if(mysqli_affected_rows($db)>0){
    while($row = mysqli_fetch_array($query)){
    		if(in_array($row['place'], $skillData))
    				continue;
 array_push($skillData,$row['place']);
    }
}

// Return results as json encoded array
echo json_encode($skillData);


    
?>
