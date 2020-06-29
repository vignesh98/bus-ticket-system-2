<?php
include "config.php";
$id=$_GET["id"];
$sql="SELECT * FROM employee_data WHERE id='$id'";
$query=mysqli_query($db_conn,$sql);
$result=mysqli_fetch_array($query);
$empname=$result["username"];
//~ echo "<br>";
//~ echo "Your FirstName is ".$result["firstname"];echo "<br>";
//~ echo "Your LastName is ".$result["lastname"];echo "<br>";
//~ echo "Your PhoneNumber is " .$result["phonenumber"];echo "<br>";
//~ echo "Your Email is " .$result["email"];echo "<br>";
//~ echo "Your UserName is " .$result["username"];echo "<br>";
//~ echo '<a href="register.php?id='.$id.'">Change Profile Details</a>';
?>
<html>
	<head>
	 <link rel="stylesheet" href="css/style.css">
	 </head>
<h1>Admin Management</h1>
<ul>
  <li><a href="emp_data.php">Employee Data</a></li>
  <li><a href="register.php">Register Employee</a></li>
  <li><a href="transactions.php">Budget/Transactions</a></li>
  <li><a href="contact.php">Contact</a></li>
</ul> 

<!-- Page content -->
<div class="main">
  ...
</div>
</html>
