<?php
$con = mysqli_connect("localhost","root","error@404","employee101");
if (isset($_POST["username"])) {
$nam=$_POST["username"];
}

if (isset($_POST["password"])) {
$pass=$_POST["password"];
}
$sql = "SELECT * FROM user_details WHERE name='$nam' AND password='$pass'";
$query=mysqli_query($con,$sql);
$rowcount=mysqli_num_rows($query);
if($rowcount!=0){
	
		echo "truephp";
   
	}
	else{
		echo "dontloginfromphp";
		}
mysqli_close($con);

ini_set('display_errors', '1');
ini_set('error_reporting', E_ALL);

?>
