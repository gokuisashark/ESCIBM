<?php
require_once("C:/wamp64/www/" . 'simpletest/autorun.php');
require_once("C:/wamp64/www/" . "getFileName.php");
class testParameters extends UnitTestCase{
	function testfortrue(){
		print("expect: True.". "<br>");
		print("Replace number within test with highest id in database <br>");
		print("Current highest id: " . "14");
		$this -> assertTrue(getFileName()==14);
	
	}
}
?>