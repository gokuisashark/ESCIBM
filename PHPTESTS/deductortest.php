<?php 
require_once("C:/wamp64/www/" . 'simpletest/autorun.php');
require_once("C:/wamp64/www/" . "deductorfunction.php");
class testParameters extends UnitTestCase{
	function testfortrue(){
		print ("TESTS: " . "<br>");
		print ("Arithmetic operation -> SourceAmount =10 , AmountMoved = 1 , TargetAmount= 10 <br>");
		print ("Expected output-> NewSourceAccountAmount=9 , NewTargetAccountAmount=11 <br>");
		$calculator = deductor(10,1,10);
		$this ->assertTrue( 9 == $calculator['Sourcenewamount'] and 11 == $calculator['Targetnewamount']);
		print ("Arithmetic operation -> SourceAmount =-1 , AmountMoved = 2 , TargetAmount= -1 <br>");
		print ("Expected output-> NewSourceAccountAmount=-3 , NewTargetAccountAmount=1 <br>");
		$calculator = deductor(-1,2,-1);
		$this ->assertTrue( -3 == $calculator['Sourcenewamount'] and 1 == $calculator['Targetnewamount']);
		print ("Arithmetic operation -> SourceAmount = string , AmountMoved = -1 , TargetAmount= 1 <br>");
		print ("Expected output-> NewSourceAccountAmount=ERROR , NewTargetAccountAmount=ERROR <br>");
		try{
			$this ->assertTrue(deductor("ERROR",-1,1));
			$this ->fail(); 
		}
		catch(Exception $ex){
			$this->pass();
			//passed exception.
		}
		print ("Arithmetic operation -> SourceAmount =1 , AmountMoved = string , TargetAmount= 1 <br>");
		print ("Expected output-> NewSourceAccountAmount=ERROR , NewTargetAccountAmount=ERROR <br>");
		try{
			$this ->assertTrue(deductor(1,"ERROR",1));
			$this ->fail(); 
		}
		catch(Exception $ex){
			$this->pass();
			//passed exception.
		}
		print ("Arithmetic operation -> SourceAmount =1 , AmountMoved = 1 , TargetAmount= string <br>");
		print ("Expected output-> NewSourceAccountAmount=ERROR , NewTargetAccountAmount=ERROR <br>");
		try{
			$this ->assertTrue(deductor(1,1,"ERROR"),"OBTAINED EXPECTED EXCEPTION");
			$this ->fail(); //passed Exception
		}
		catch(Exception $ex){
			$this->pass();
			//passed exception.
		}
		
	}
}
?>