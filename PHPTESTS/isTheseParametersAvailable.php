<?php
function isTheseParametersAvailable($params){
 //traversing through all the parameters
 foreach($params as $param){
 //if the paramter is not available
 if(!isset($_POST[$param])){
 //return false
 return false;
 }
 if(!($param =='face')){if(preg_match('[(?i:\bor\b)]',$_POST[$param])){return;} //disallow or in any posted value as a word by itself. (catches symbols beside it too so =or= triggers, =or, or= also trigger) case insensitive test
 if(preg_match('[=]',$_POST[$param])){return;} //disallow any = in posted value
 if(preg_match('[(?i:\bDROP TABLE\b)]',$_POST[$param])){return;} //Catches both drops. preventing dropping a table.
 }
 }
 //return true if every param is available
 return true;
 }
 ?>