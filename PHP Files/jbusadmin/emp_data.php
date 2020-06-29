<html>
	<head>
	 <link rel="stylesheet" href="css/style.css">
	 </head>
	 <body>
<h1>Admin Management</h1>
<ul>
  <li><a href="emp_data.php">Employee Data</a></li>
  <li><a href="register.php">Register Employee</a></li>
  <li><a href="transactions.php">Budget/Transactions</a></li>
  <li><a href="contact.php">Contact</a></li>
</ul>
<div class="main">
  <h2>Employee Data</h2>
<?php
$con=mysqli_connect("localhost","root","error@404","employee101");
// Check connection
if (mysqli_connect_errno())
{
echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$result = mysqli_query($con,"SELECT * FROM employee_data");

echo "<table border='2'>
<tr>
<th>Employee ID</th>
<th>Employee Name</th>
<th>Email</th>
<th>Contact Number</th>
<th>Age</th>
<th>Emp username</th>
</tr>";

while($row = mysqli_fetch_array($result))
{
echo "<tr>";
echo "<td>" . $row['id'] . "</td>";
echo "<td>" . $row['name'] . "</td>";
echo "<td>" . $row['email'] . "</td>";
echo "<td>" . $row['contact'] . "</td>";
echo "<td>" . $row['age'] . "</td>";
echo "<td>" . $row['username'] . "</td>";
echo "</tr>";
}
echo "</table>";

mysqli_close($con);
?>
</div>
</body>
</html> 
