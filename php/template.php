<? 
 $string = "My favorite actress is Natalie Portman!";
 $length = strlen($string);
 echo "<".$prefix."char=0,$length> 
	a nif:RFC5147String , nif:String , nif:Context ;
	nif:beginIndex \"0\";
	nif:endIndex \"$length\";
	nif:isString \"\"\"$string\"\"\". ";
