<?php
$con = mysqli_connect("localhost","root","error@404","employee101");
if (isset($_POST["username"])) {
$nam=$_POST["username"];
}

if (isset($_POST["password"])) {
$pass=$_POST["password"];
}
$sql = "SELECT * FROM employee_data WHERE username='$nam' AND password='$pass'";
$query=mysqli_query($con,$sql);
$rowcount=mysqli_num_rows($query);
if($rowcount!=0){
	
		echo "login";
   
	}
	else{
		echo "dontlogin";
		}
mysqli_close($con);
?>
