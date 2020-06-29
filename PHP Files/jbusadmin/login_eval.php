<?php
include "config.php";
$usrname=$_POST["username"];
$pwd=$_POST["password"];
//~ $username="user";
//~ $pwd="123";
$sql="SELECT id FROM employee_data WHERE username='{$usrname}' AND password='{$pwd}'";

$query=mysqli_query($db_conn,$sql);
$rowcount=mysqli_num_rows($query);
$result=mysqli_fetch_row($query);
	$id= $result[0];
if($rowcount!=0){
	
		header("Location:home.php?id=$id");

	}
	else{
		$b='invalid passwords';
		header("Location:index.php?error=$b");
		}
?>
