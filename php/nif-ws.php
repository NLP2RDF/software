<?php
/********
 * Configuration
 * *****/

$dynamicPrefix = false;
$defaultPrefix = "http://localhost/text/" ;
$defaultLogPrefix = "" ;
$defaultPrefix = "http://" . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI']."#";

/*******
 * Implementation
 * *****/
 
//informat default: turtle 	Determines in which format the input is given. NIF-SI must accept values "text", "turtle", "rdfxml", "ntriples". NIF-SI may additionally accept "json", "html", "pdf" and others.
$informat = (isset($_REQUEST['informat']))?$_REQUEST['informat']:"turtle";

//TODO
$outformat = (isset($_REQUEST['outformat']))?$_REQUEST['outformat']:"turtle";

/*intype NIF-CLI default: file NIF-WS default: direct 	Determines how the input is accessed or retrieved. Possible values are:
*    direct - input is read from stdin or HTTP parameter (GET) or body (POST).
*    url - NIF-SI retrieves the input from the URL.
*    file - input is read from a local file (relative or absolute path) .
NIF-WS must accept values "url" and "direct" */
$intype = (isset($_REQUEST['intype']))?$_REQUEST['intype']:"direct";
//input required 	Determines the input in accordance with the intype and informat parameter.
if(!isset($_REQUEST['input'])){
	die("todo error");
}else{
	$input = $_REQUEST['input'];
}

//urischeme default: RFC5147String 	Determines the urischeme to be used. Must be the full URI of the scheme of the OWL class. In case the namespace is "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#" it can be omitted for brevity. Allowed values: "RFC5147String", "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#RFC5147String", etc.
//TODO implement this
$urischeme = (isset($_REQUEST['urischeme']))?$_REQUEST['urischeme']:"RFC5147String";
//urischemeparameters optional 	Some parameters for certain urischemes, e.g. contextlength 

//prefix 	A prefix, which is used to create any URIs. Therefore the client should ensure, that the prefix is valid, when used at the start of the uri, e.g. http://test.de/test#. It is recommended that the client matches the prefix to the previously used prefix. If the parameter is missing it should be substituted by a sensible default (e.g. the web service uri).
$prefix = (isset($_REQUEST['prefix']))?$_REQUEST['prefix']:$defaultPrefix;
$logPrefix = (isset($_REQUEST['logPrefix']))?$_REQUEST['logPrefix']:$defaultLogPrefix;

if($dynamicPrefix === true && $informat==="text"){
	$prefix .= urlencode($input)."#";
}


//notimplemented
if($intype != "direct" || $urischeme != "RFC5147String" ) {
	die("only intype=direct and urischeme=RFC5147String implemented at the moment.") ;
	}

include("ARC2/ARC2.php");

$nifdata = null;
$text = null;
$parser = null;
$triples = null;
if($intype === "direct") {
	if($informat==="text"){ 
		$length = strlen($input);
		$nifdata = "@prefix nif: <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#>.
		<".$prefix."char=0,$length> 
			a nif:RFC5147String , nif:Context ;
			nif:beginIndex \"0\";
			nif:endIndex \"$length\";
			nif:isString \"\"\"$input\"\"\". ";

		$parser = ARC2::getTurtleParser();
		$parser->parse($prefix, $nifdata);
		$triples = $parser->getTriples();
		$text = $input;
	}else if($informat==="turtle"){
		$parser = ARC2::getTurtleParser();
		$parser->parse($prefix, $input);
		$triples = $parser->getTriples();
		$text = getTextFromTriples($triples);
	}else if($informat==="rdfxml"){
		$parser = ARC2::getRDFXMLParser();
		$parser->parse($prefix, $input);
		$triples = $parser->getTriples();
		$text = getTextFromTriples($triples);
	} 
}

/****
 * Out
 * ****/
 $ns = array(
       'prefix' => trim($prefix) ,
       'nif' => 'http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#',
       'rdf' => 'http://www.w3.org/1999/02/22-rdf-syntax-ns#',
       'owl' => 'http://www.w3.org/2002/07/owl#'
      );
 
 
if ($outformat == "turtle"){
        $ser = ARC2::getTurtleSerializer(array('ns' => $ns));
        $output = $ser->getSerializedTriples($triples);
        header("Content-Type: text/turtle");

        echo $output;
}
else if ($outformat == "rdfxml"){
        $ser = ARC2::getRDFXMLSerializer(array('ns' => $ns));
        $output = $ser->getSerializedTriples($triples);
        header("Content-Type: application/rdf+xml");
	
        echo $outformat;
}
else if ($outformat == "json"){
        $ser = ARC2::getRDFJSONSerializer(array('ns' => $ns));
        $output = $ser->getSerializedTriples($triples);
        header("Content-Type: application/json");
	
        echo $output;
}
else if ($outformat == "ntriples"){
        $ser = ARC2::getNTriplesSerializer(array('ns' => $ns));
        $output = $ser->getSerializedTriples($triples);
        header("Content-Type: text/plain");
	
        echo $output;
}else if ($outformat == "text"){
        header("Content-Type: text/plain");
	
        echo $text;
}



function getTextFromTriples($triples){
	foreach( $triples as $t ){
			if($t['p'] === "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#isString"){
				return 	$t['o'];
			}
	}
}
