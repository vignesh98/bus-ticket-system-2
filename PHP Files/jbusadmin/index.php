<!DOCTYPE HTML>
<HTML>
	<HEAD>
        <link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/mdb.css">
		<link rel="stylesheet" href="css/style.css">
		</HEAD>
<body id="indexfile">
<form id="login" method="POST" action="login_eval.php">
	<?php
	if(isset($_GET['error']))
	{
		echo "<h2 style='color:red'>Invalid Password</h2>";
	}
	
	?>
	<p id="logdet">Enter Login Details</p></br></br></br>
	
Enter UserName:  <input type="text" name="username" id="username"></input></br></br>
Enter Password: 	<input type="password" name="password" id="password"></input></br></br></br>
<input id="button" type="Submit" value="Login" name="submit"></input>
</form></br></br>
<p id="signup">
<a id="sign" href="register.php">  Register Here</a>

</p></body>
<html>
