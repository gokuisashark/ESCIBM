<?php
function deductor($sourceAccountAmount,$amounttransferred,$targetAccountmoney){
	 if(!is_numeric($sourceAccountAmount)){throw new \Exception("INVALID AMOUNT");}
	 if(!is_numeric($amounttransferred)){throw new \Exception("INVALID AMOUNT");}
	 if(!is_numeric($targetAccountmoney)){throw new \Exception("INVALID AMOUNT");}
	 $Sourcenewamout=  $sourceAccountAmount - $amounttransferred;
 	 $Targetnewamount = $targetAccountmoney +$amounttransferred;
	 $responder['Sourcenewamount'] = $Sourcenewamout;
	 $responder['Targetnewamount'] = $Targetnewamount;
	 return $responder;
 }
 ?>