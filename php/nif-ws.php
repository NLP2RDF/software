<?php
define("NIF","http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#");
define("RLOG","http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#");
define("RDF","http://www.w3.org/1999/02/22-rdf-syntax-ns#");

// example:
// curl  -H "Content Type: text/plain" -H "Accept: application/rdf+xml" "http://localhost/nif-ws.php?input=This+is+a+sentence."

/********
 * Configuration
 * *****/
$defaultPrefix = "http://" . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI']."#";
$now = microtime(true);

/******
 * INFO or HELP
 */
$help = "This is the NIF 2.0 implementation of http://persistence.uni-leipzig.org/nlp2rdf/specification/api.html . Note that the implementation is neither complete nor correct:
Implemented:
- outformat=html will produce some data categories from ITS 2.0 
- in/out can also be ntriples/rdfxml/json



Not implemented:
- only urischeme=RFC5147String is implemented
- for intype=url , informat=text is not implemented
- ld+json
 ";
 //- rewrite = true replace the prefix, please use oldprefix parameter: str_replace($oldprefix,$prefix,$uri);
 
if(isset($_REQUEST['help']) || isset($_REQUEST['h']) || isset($_REQUEST['info']) ){
	die($help)	;
}
/*******
 * Implementation
 * *****/
 
//Normalize paramenters to long form
$_REQUEST['informat'] = (isset($_REQUEST['f']))?$_REQUEST['f']:@$_REQUEST['informat'];
$_REQUEST['input'] = (isset($_REQUEST['i']))?$_REQUEST['i']:@$_REQUEST['input'];
$_REQUEST['intype'] = (isset($_REQUEST['t']))?$_REQUEST['i']:@$_REQUEST['intype'];
$_REQUEST['outformat'] = (isset($_REQUEST['o']))?$_REQUEST['o']:@$_REQUEST['outformat'];
$_REQUEST['urischeme'] = (isset($_REQUEST['u']))?$_REQUEST['u']:@$_REQUEST['urischeme'];
$_REQUEST['prefix'] = (isset($_REQUEST['p']))?$_REQUEST['p']:@$_REQUEST['prefix'];
 
 
//defaults
$informat = (isset($_REQUEST['informat']))?$_REQUEST['informat']:"turtle";
$intype = (isset($_REQUEST['intype']))?$_REQUEST['intype']:"direct";
$outformat = (isset($_REQUEST['outformat']))?$_REQUEST['outformat']:"turtle";
$urischeme = (isset($_REQUEST['urischeme']))?$_REQUEST['urischeme']:"RFC5147String";
$prefix = (isset($_REQUEST['prefix']))?$_REQUEST['prefix']:$defaultPrefix;
//$rewrite = (isset($_REQUEST['rewrite']))?$_REQUEST['rewrite']:false;
//$oldprefix = (isset($_REQUEST['oldprefix']))?$_REQUEST['oldprefix']:;

if (stristr(@$_SERVER['HTTP_ACCEPT'], "text/plain")){
	$outformat = "text";
}if (stristr(@$_SERVER['HTTP_ACCEPT'], "text/html")){
	$outformat = "html";
}else if(stristr(@$_SERVER['HTTP_ACCEPT'], "text/turtle")){
	$outformat = "turtle";
}else if(stristr(@$_SERVER['HTTP_ACCEPT'], "application/rdf+xml")){
	$outformat = "rdfxml";
}else if(stristr(@$_SERVER['HTTP_ACCEPT'], "application/ld+json")){
	die($help);
}else if(stristr(@$_SERVER['HTTP_ACCEPT'], "application/n-triples")){
	$outformat = "ntriples";
}

if (stristr(@$_SERVER['HTTP_CONTENT_TYPE'], "text/plain")){
	$informat = "text";
}if (stristr(@$_SERVER['HTTP_CONTENT_TYPE'], "text/html")){
	$informat = "html";
	die($help);
}else if(stristr(@$_SERVER['HTTP_CONTENT_TYPE'], "text/turtle")){
	$informat = "turtle";
}else if(stristr(@$_SERVER['HTTP_CONTENT_TYPE'], "application/rdf+xml")){
	$informat = "rdfxml";
}else if(stristr(@$_SERVER['HTTP_CONTENT_TYPE'], "application/ld+json")){
	die($help);
}else if(stristr(@$_SERVER['HTTP_CONTENT_TYPE'], "application/n-triples")){
	$informat = "ntriples";
}

// some validation
if(!isset($_REQUEST['input'])){
	die("input not set see: http://persistence.uni-leipzig.org/nlp2rdf/specification/api.html");
}
if($urischeme != "RFC5147String" ){
	die($help);
}
if($intype === "url" && $informat==="text" ){
	die($help);	
}

$input = $_REQUEST['input'];

include("ARC2/ARC2.php");
$logmessage = "";
// treat text
if($intype === "direct" && $informat==="text"){ 
	$length = strlen($input);
	$input = "@prefix nif: <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#>.
	@prefix prefix: <".$prefix."> .
	<".$prefix."char=0,$length> 
		a nif:RFC5147String , nif:Context ;
		nif:beginIndex \"0\";
		nif:endIndex \"$length\";
		nif:isString \"\"\"$input\"\"\". ";
	$logmessage.="Converted text to NIF.\n";
}
//now it's turtle
$informat = "turtle";
$parser = ARC2::getRDFParser();;
if($intype === "direct") {
	$triples = $parser->parse($prefix, $input);
}else if ($intype === "url"){
	$triples = $parser->parse($input);
	}
$triples = $parser->getTriples();
$logmessage.="Retrieved ".count($triples)." triples. ";
$newtriples = array();
$newtriples = expand($parser);
//write a log
$logmessage.="Created ".count($newtriples)." new triples. Used ".round(memory_get_peak_usage()/1000000,2)."mb max memory. Needed ".round((microtime(true)-$now),2)."ms";
$loguri =  uniqid ("urn:php:",true);
$log=array();
$log[]=addTripleAllURIs($loguri,RLOG.'level',RLOG.'DEBUG' );
$log[]=addTripleLiteral($loguri,RLOG.'message',$logmessage );
$log[]=addTripleLiteral($loguri,'http://purl.org/dc/elements/1.1/creator',"http://" . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'] );
$log[]=addTripleAllURIs($loguri,RDF.'type',RLOG.'Entry' );
$objDateTime = new DateTime('NOW');
$time= $objDateTime->format(DateTime::W3C ); 
$log[]=array('type'=>'triple','s'=>$loguri, 'p'=>NIF."date",'o'=>$time, 's_type' => 'uri', 'p_type'=> 'uri', 'o_type' => 'literal', 'o_datatype'=>'http://www.w3.org/2001/XMLSchema#dateTime');

$triples = array_merge($triples, $newtriples,$log);

/****
 * Out
 * ****/
 $ns = array(
       'p' => trim($prefix) ,
       'nif' => NIF,
       'rdf' => 'http://www.w3.org/1999/02/22-rdf-syntax-ns#',
       'rdfs' => 'http://www.w3.org/2000/01/rdf-schema#',
       'owl' => 'http://www.w3.org/2002/07/owl#',
       'rlog' => 'http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#',
       'dc'  => 'http://purl.org/dc/elements/1.1/' 
      );
      

$ser = null;
switch ($outformat){
	case "turtle": $ser = ARC2::getTurtleSerializer(array('ns' => $ns)); break;
	case "rdfxml":  $ser = ARC2::getRDFXMLSerializer(array('ns' => $ns)); break;
	case "json":  $ser = ARC2::getRDFJSONSerializer(array('ns' => $ns)); break;
	case "ntriples":  $ser = ARC2::getNTriplesSerializer(array('ns' => $ns)); break;
	
} 
 
if ($outformat == "turtle"){
        $output = $ser->getSerializedTriples($triples);
        header("Content-Type: text/turtle");
}else if ($outformat == "rdfxml"){
        $output = $ser->getSerializedTriples($triples);
        header("Content-Type: application/rdf+xml");
}else if ($outformat == "json"){
        $ser = ARC2::getRDFJSONSerializer(array('ns' => $ns));
        $output = $ser->getSerializedTriples($triples);
}else if ($outformat == "ntriples"){
        $output = $ser->getSerializedTriples($triples);
        header("Content-Type: text/plain");
}else if ($outformat == "text"){
		$output = getTextFromTriples($triples);
        header("Content-Type: text/plain");
}
 echo $output;


function getTextFromTriples($triples){
	foreach( $triples as $t ){
			if($t['p'] === NIF."isString"){
				return 	$t['o'];
			}
	}
}

function expand($parser){
	$index = $parser->getSimpleIndex(0);
	$triples=array();
	$sentences=array();
	foreach ($index as $uri=>$array){
		if(isset($array[NIF.'beginIndex'])){
			$beginIndex = $array[NIF.'beginIndex'][0]['value'];
			$endIndex = $array[NIF.'endIndex'][0]['value'];
			$referenceContext = $array[NIF.'referenceContext'][0]['value'];
			$anchorOf =  substr($index[$referenceContext][NIF.'isString'][0]['value'],$beginIndex,$endIndex-$beginIndex);
			$triples[]=addTripleLiteral($uri, NIF.'anchorOf',$anchorOf); 
			
			if(isset($array[NIF.'sentence'])){
				$sentence = $array[NIF.'sentence'][0]['value'];
				$sentences[$sentence][$beginIndex] = $uri; 
			}	
		}
	}
	
	// sort them
	foreach ($sentences as $sentence=>$words){
			ksort($words);
			$sentences[$sentence]=$words;
	}
	
	foreach ($sentences as $sentence=>$words){
		$first=true;
		$previous = "";
		foreach($words as $beginIndex=>$uri){
			
			$triples[]=addTripleAllURIs($sentence, NIF."word",$uri);
			if(!$first){
				$triples[]=addTripleAllURIs($previous, NIF."nextWord",$uri);
				$triples[]=addTripleAllURIs($uri, NIF."previous",$previous);
			}else{
				$triples[]=addTripleAllURIs($sentence, NIF."firstWord",$uri);
				$first=false;
			}
			$previous=$uri;
		}
		$triples[]=addTripleAllURIs($sentence, NIF."lastWord",$uri);
	}
	return $triples;
}

function addTripleAllURIs($s_uri, $p_uri, $o_uri){
		return array('type'=>'triple','s'=>$s_uri, 'p'=>$p_uri,'o'=>$o_uri, 's_type' => 'uri', 'p_type'=> 'uri', 'o_type' => 'uri');
	}

function addTripleLiteral($s_uri, $p_uri, $literal){
		return array('type'=>'triple','s'=>$s_uri, 'p'=>$p_uri,'o'=>$literal, 's_type' => 'uri', 'p_type'=> 'uri', 'o_type' => 'literal');
	}




