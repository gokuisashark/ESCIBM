<?php
$servername ="localhost";
$username = "root";
$password = "";
$database = "initialusertest";

$conn = new mysqli($servername,$username,$password,$database);

if($conn->connect_error){
 die( "Connection Failure ._." . $conn->connect_error);
}
?>
