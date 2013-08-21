#!/usr/bin/php
<?
include("ARC2/ARC2.php");
include("lib/Validator.php");
$parser = ARC2::getRDFParser();

$actions=array(
	"help" => "print help",
	"validate" => "validates NIF input with SPARQL Construct queries: nif validate <file|url> "
);

$action = $argv[1];
unset( $argv[0]);
unset( $argv[1]);
$argv = array_values($argv);


print_r($argv);

if(!in_array($action, array_keys($actions) )){
	help($actions);
	die;
	}

switch($action){
	case "help": 
		help($actions); 
		die; 
	case "validate":
		$val = new Validator();
		//$parser->parse($argv[0]);
		$val->check_vars($arr);
	}



function help($actions){
	foreach($actions as $key=>$value){
		echo "$key: $value\n";
		}
	}
