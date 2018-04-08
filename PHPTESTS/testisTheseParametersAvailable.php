<?php
require_once("C:/wamp64/www/" . 'simpletest/autorun.php');
require_once("C:/wamp64/www/" . "isTheseParametersAvailable.php");
class TestParameters extends UnitTestCase{
	function testfortrue(){
		$_POST['existent'] = '1';
		$_POST[' '] = "1";
		$_POST['a2!a2'] = "1";
		$_POST['1234567'] = '1';
		$_POST['sql_attack_1'] = " OR ";
		$_POST['sql_attack_2'] = " = ";
		$_POST['sql_attack_3'] = " =A";
		$_POST['sql_attack_4'] = " DROP TABLE=";
		$_POST['sql_attack_5'] = " =DROP TABLE";
		$_POST['sql_attack_6'] = " DROP TABLE";
		print ("POST CONTENTS:"  . "<br>");
		print ("\$_POST[\'1234567\'] = \'1\';"  . "<br>");
		print ("\$_POST[\'a2!a2\'] = \'1\';"  . "<br>");
		print ("\$_POST[\' \'] = \"1\";"  . "<br>");
		print ("\$_POST[\'existent\'] = \'1\';"  . "<br>");
		print ("\$_POST['sql_attack_1'] = \" OR \";"  . "<br>");
		print ("\$_POST['sql_attack_2'] = \" = \";"  . "<br>");
		print ("\$_POST['sql_attack_3'] = \" =A\";"  . "<br>");
		print ("\$_POST['sql_attack_4'] = \" DROP TABLE=\";"  . "<br>");
		print ("\$_POST['sql_attack_5'] = \" =DROP TABLE\";"  . "<br>");
		print ("\$_POST['sql_attack_6'] = \" DROP TABLE\";"  . "<br>");
		print ("<br><br><br><br>");
		print ("TESTS: " . "<br>");
		$this ->assertTrue(isTheseParametersAvailable(array('existent')));
		print ("assertTrue(isTheseParametersAvailable(array(\'existent\')))" . " --> EXPECTED: TRUE" . "<br>");
		$this ->assertTrue(isTheseParametersAvailable(array(' ')));
		print ("assertTrue(isTheseParametersAvailable(array(\' \')));" . " --> EXPECTED: TRUE" ."<br>");
		$this ->assertTrue(isTheseParametersAvailable(array('a2!a2')));
		print ("assertTrue(isTheseParametersAvailable(array(\'a2!a2\')))" . " --> EXPECTED: TRUE" ."<br>");
		$this ->assertTrue(isTheseParametersAvailable(array('1234567')));
		print ("assertTrue(isTheseParametersAvailable(array(\'1234567\')));" . " --> EXPECTED: TRUE" ."<br>");
		$this ->assertFalse(isTheseParametersAvailable(array('')));
		print ("assertFalse(isTheseParametersAvailable(array(\'\')));" . " --> EXPECTED: FALSE" ."<br>");
		$this -> assertFalse(isTheseParametersAvailable(array('non_existent')));
		print ("assertFalse(isTheseParametersAvailable(array(\'non_existent\')));" ." --> EXPECTED: FALSE" . "<br>");
		$this ->assertTrue(isTheseParametersAvailable(array('existent','1234567','a2!a2')));
		print ("assertTrue(isTheseParametersAvailable(array(\'existent\',\'1234567\',\'a2!a2\')));" . " --> EXPECTED: TRUE" ."<br>");
		$this-> assertFalse(isTheseParametersAvailable(array('non_existent','')));
		print ("assertFalse(isTheseParametersAvailable(array(\'non_existent\',\'\')));" . " --> EXPECTED: FALSE" ."<br>");
		$this-> assertFalse(isTheseParametersAvailable(array('non_existent','existent')));
		print ("assertFalse(isTheseParametersAvailable(array(\'non_existent\',\'existent\')));" ." --> EXPECTED: FALSE" . "<br>");
		$this -> assertFalse(isTheseParametersAvailable(array('sql_attack_1')));
		print("assertFalse(isTheseParametersAvailable(array(\'sql_attack_1\')));". " --> EXPECTED: FALSE" ."<br>");
		$this -> assertFalse(isTheseParametersAvailable(array('sql_attack_2')));
		print("assertFalse(isTheseParametersAvailable(array(\'sql_attack_2\')));". " --> EXPECTED: FALSE" ."<br>");
		$this -> assertFalse(isTheseParametersAvailable(array('sql_attack_3')));
		print("assertFalse(isTheseParametersAvailable(array(\'sql_attack_3\')));". " --> EXPECTED: FALSE" ."<br>");
		$this -> assertFalse(isTheseParametersAvailable(array('sql_attack_4')));
		print("assertFalse(isTheseParametersAvailable(array(\'sql_attack_4\')));". " --> EXPECTED: FALSE" ."<br>");
		$this -> assertFalse(isTheseParametersAvailable(array('sql_attack_5')));
		print("assertFalse(isTheseParametersAvailable(array(\'sql_attack_5\')));". " --> EXPECTED: FALSE" ."<br>");
		$this -> assertFalse(isTheseParametersAvailable(array('sql_attack_6')));
		print("assertFalse(isTheseParametersAvailable(array(\'sql_attack_6\')));". " --> EXPECTED: FALSE" ."<br>");
	}
	
}
?>