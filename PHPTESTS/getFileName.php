<?php
function getFileName(){
 define('HOST','localhost');
 define('USER','root');
 define('PASS','');
 define('DB','bankmockup');
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');
 $sql = "SELECT max(id) as id FROM bank";
 $result = mysqli_fetch_array(mysqli_query($con,$sql));

 mysqli_close($con);
 if($result['id']==null)
 return 1;
 else
 return $result['id'];
 }
?>