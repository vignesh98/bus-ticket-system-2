<?php
$con = mysqli_connect("localhost","root","error@404","employee101");
if (isset($_POST["ticketstring"])) {
$ticketdata1=$_POST["ticketstring"];
}
// $strticket = strval( $ticketdata1); 


$sql = "SELECT * FROM journey_details WHERE ticketdata ='$ticketdata1'";
$query=mysqli_query($con,$sql);
$rowcount=mysqli_num_rows($query);
//echo $rowcount;
if($rowcount!=0){
	
		echo "valid";
	//	echo $ticketdata1;
   
	}
	else{
		echo "invalid";
		}
mysqli_close($con);
?>
