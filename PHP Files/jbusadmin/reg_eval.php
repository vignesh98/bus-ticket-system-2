<?php
//~ include "config1.php";
$fname=$_POST["name1"];
$email=$_POST["email"];
$mobnum=$_POST["phnum"];
$age=$_POST["age"];
$usrname=$_POST["username1"];
$pwd=$_POST["password"];

$servername = "localhost";
$username = "root";
$password = "error@404";
$dbname = "employee101";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "INSERT INTO employee_data (name, email, contact,age,username,password)
VALUES ('$fname', '$email', '$mobnum','$age','$usrname','$pwd')";

if ($conn->query($sql) === TRUE) {
    header("Location:emp_data.php");
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>
