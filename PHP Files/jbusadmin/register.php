<!DOCTYPE HTML>
<HTML>
	<HEAD>
		<h1>Admin Management</h1>
		<link rel="stylesheet" href="css/style.css">
		</HEAD>
		
		<ul>
  <li><a href="emp_data.php">Employee Data</a></li>
  <li><a href="register.php">Register Employee</a></li>
  <li><a href="transactions.php">Budget/Transactions</a></li>
  <li><a href="contact.php">Contact</a></li>
</ul> 

<!-- Page content -->
<div class="main">
		<body id="register">
			<form id="reg" method="POST" action="reg_eval.php">
				<h2>Please Enter the Employee Details</h2>
				</br>
				
				Enter Name<input type="text" name="name1" id="name1"></input></br></br>
				Email ID  <input type="text" name="email" id="email"></input></br></br>
				Contact No.<input type="text" name="phnum" id="phnum"></input></br></br>
				Age<input type="text" name="age" id="age"></input></br></br>
				Username<input type="text" name="username1" id="username1"></input></br></br>
				Password<input type="password" name="password" id="password"></input></br>
				</br></br></br>
				<input type="Submit" name="Submit" value="Register"></input>
				</form>
				</div>
			</body>
</html>
