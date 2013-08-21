<?php
include("../ARC2/ARC2.php");

$prefix = (isset($_REQUEST['prefix'] ))?$_REQUEST['prefix'] :"http://prefix.given.by/theClient#";
$urirecipe = (isset($_REQUEST['urirecipe'] ))?$_REQUEST['urirecipe'] :"offset";
$text = $_REQUEST['text'];
$format = $_REQUEST['format'];
   
$meta = ""; 
$output = "";
$alltriples = array();
foreach (@$_REQUEST['service'] as $service){
	if(trim($service) == "") continue;
	//retrieve
	$time_start = microtime(true);
	$uri = $service."?input-type=text&nif=true&prefix=".urlencode($prefix)."&urirecipe=$urirecipe&input=".urlencode($_REQUEST['text']); 
	$data = file_get_contents($uri);
	$time_end = microtime(true);
	$time_service_needed = round ($time_end - $time_start,2);
	
	//parsing
	$time_start = microtime(true);
	$parser = ARC2::getRDFXMLParser();
	$parser->parse($prefix, $data);
	$triples = $parser->getTriples();
	$alltriples = array_merge($alltriples, $triples);
	$time_end = microtime(true);
	$time_arc2_needed = round ($time_end - $time_start,2);
	
	$meta .= "
	<p>Sevice was $service:</p>
	<ul class=\"checks\">
	  <li>NLP component needed: $time_service_needed seconds.</li>
	  <li>ARC2 RDF Parser overhead: $time_arc2_needed seconds.</li>
	  <li><a href=\"$uri\" >request url</a> </li>
	  <li>Text size: ".strlen($_REQUEST['text'])."</li>
	  <li>Triples: ".count($triples)."</li>
	</ul>";
	
	}
	
$ns = array(
       'p' => trim($prefix) ,
       'rdf' => 'http://www.w3.org/1999/02/22-rdf-syntax-ns#',
       'rdfs' => 'http://www.w3.org/2000/01/rdf-schema#',
       'owl' => 'http://www.w3.org/2002/07/owl#',
       'sso' => 'http://nlp2rdf.lod2.eu/schema/sso/',
       'str' => 'http://nlp2rdf.lod2.eu/schema/string/',
       'topic' => 'http://nlp2rdf.lod2.eu/schema/topic/',
       'error' => 'http://nlp2rdf.lod2.eu/schema/error/',
       'olia' => 'http://purl.org/olia/olia.owl#',
       'olia-top' => 'http://purl.org/olia/olia-top.owl#',
       'olia_system' => 'http://purl.org/olia/system.owl#',
       'penn' => 'http://purl.org/olia/penn.owl#',
       'penn-syntax' => 'http://purl.org/olia/penn-syntax.owl#',
       'stanford' => 'http://purl.org/olia/stanford.owl#',
       'brown' => 'http://purl.org/olia/brown.owl#',
       'dbo' => 'http://dbpedia.org/ontology/' ,
       'dbpedia' => 'http://dbpedia.org/resource/' ,
       'nerd' => 'http://nerd.eurecom.fr/ontology#' ,
       'scms' => 'http://ns.aksw.org/scms/' ,
       'spotlight' => 'http://dbpedia.org/spotlight/' 
      );
       
if ($format == "turtle"){
        $ser = ARC2::getTurtleSerializer(array('ns' => $ns));
        $output = $ser->getSerializedTriples($alltriples);
        header("Content-Type: text/turtle");

        echo $output;
}
else if ($format == "rdfxml"){
        $ser = ARC2::getRDFXMLSerializer(array('ns' => $ns));
        $output = $ser->getSerializedTriples($alltriples);
        header("Content-Type: application/rdf+xml");
	
        echo $output;
}
else if ($format == "rdfjson"){
        $ser = ARC2::getRDFJSONSerializer(array('ns' => $ns));
        $output = $ser->getSerializedTriples($alltriples);
        header("Content-Type: application/json");
	
        echo $output;
}
else if ($format == "ntriples"){
        $ser = ARC2::getNTriplesSerializer(array('ns' => $ns));
        $output = $ser->getSerializedTriples($alltriples);
        header("Content-Type: text/plain");
	
        echo $output;
}
else{
        $ser = ARC2::getTurtleSerializer(array('ns' => $ns));
        $output = $ser->getSerializedTriples($alltriples);
?>

<html>
  <head>
	<title>NIF Combinator: Combining NLP Tool Output</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
    <!--[if lt IE 9]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <script type="text/javascript" src="js/prettify.js"></script>                                   <!-- PRETTIFY -->
    <script type="text/javascript" src="js/kickstart.js"></script>                                  <!-- KICKSTART -->
    <link rel="stylesheet" type="text/css" href="css/kickstart.css" media="all" />                  <!-- KICKSTART -->
    <link rel="stylesheet" type="text/css" href="css/style.css" media="all" />
    <script type="text/javascript">
      $(document).ready(function(){
         $('.show_hide').click(function(){
           $("#details").slideToggle();
         });
      });
    </script>
  </head>

  <body>
    <?php //print_r($_REQUEST); ?>
    <h3>NIF Combinator: Combining NLP Tool Output</h3>
    <!--p id="about">This web site show cases and combines available demo nif web services. Note that the list displayed here is not complete. More tools will be added soon. Read the  <a href="http://svn.aksw.org/papers/2012/NIF/EKAW_DEMO/ekaw_demo_public_draft.pdf" target="_blank">full documentation</a>. The code for this demo is available <a href="http://code.google.com/p/nlp2rdf/source/browse/nlp2rdf.lod2.eu/demo.php" target="_blank" >here</a>. This service also allows you to retrieve the raw data. Use the <em>format</em> parameter which supports <em>rdfxml, turtle, rdfjson, ntriples</em> as values. You can also use the <em>Accept:</em> header with the appropriate content-type. Note that this service is currently using NIF-1.0, while the NIF-2.0 specification is in work. Take a look at the <a href="http://nlp2rdf.org/news/nif-roadmap-2012-and-pointers" target="_blank">roadmap</a> for further informations.
    </p-->
    <form>
    <div class="col_4">
      <p id="about">This web site show cases and combines available demo nif web services. Note that the list displayed here is not complete. More tools will be added soon. Read the  <a href="http://svn.aksw.org/papers/2012/NIF/EKAW_DEMO/public_preprint.pdf" target="_blank">full documentation</a> [<a href="http://dblp.uni-trier.de/rec/bibtex/conf/ekaw/HellmannLAN12" target=_blank">Bibtex</a>]. The code for this demo is available <a href="http://code.google.com/p/nlp2rdf/source/browse/nlp2rdf.lod2.eu/demo.php" target="_blank" >here</a>. This service also allows you to retrieve the raw data. Use the <em>format</em> parameter which supports <em>rdfxml, turtle, rdfjson, ntriples</em> as values. You can also use the <em>Accept:</em> header with the appropriate content-type. Note that this service is currently using NIF-1.0, while the NIF-2.0 specification is in work. Take a look at the <a href="http://nlp2rdf.org/news/nif-roadmap-2012-and-pointers" target="_blank">roadmap</a> for further informations.
    </p>
    </div>

    <div class="col_4">
      <fieldset>
	<legend>Action</legend>
	<input type="radio" id="radio_action_1" name="action" value="merge" checked="checked" />
	<label for="radio_action_1">&nbsp;display and merge</label><br />
	<input type="radio" id="radio_action_2" name="action" value="validate" disabled="disabled"/>
	<label for="radio_action_2">&nbsp;validate</label><br />
	<input type="radio" id="radio_action_3" name="action" value="summarize" disabled="disabled"/>
	<label for="radio_action_3">&nbsp;summarize</label>
      </fieldset>
      <fieldset>
	<legend>URI Recipe</legend>
	<input type="radio" id="radio_uri_1" name="urirecipe" value="offset" checked="checked"/>
	<label for="radio_uri_1">&nbsp;offset</label><br />
	<input type="radio" id="radio_uri_2" name="urirecipe" value="context-hash" />
	<label for="radio_uri_2">&nbsp;context-hash</label>
      </fieldset>
      <fieldset>
	<legend>Prefix</legend>
	<input id="text1" type="text" name="prefix" value="<?php echo $prefix; ?>"/>
      </fieldset>
    </div>
    <div class="col_4">
      <fieldset>
	<legend>Tools</legend>
	<?php 
        $serviceCheckboxes=array(
	  "<a href=\"http://nlp2rdf.org/implementations/snowballstemmer\" target=_blank >Snowball Stemmer</a> - NIF 2.0 draft"=>"http://nlp2rdf.lod2.eu/demo/NIFStemmer", 
          "<a href=\"http://nlp2rdf.org/implementations/stanford-corenlp\" target=_blank >Stanford CoreNLP</a> - NIF 2.0 draft</a>"=>"http://nlp2rdf.lod2.eu/demo/NIFStanfordCore",
          //"<a href=\"http://code.google.com/p/nlp2rdf/source/browse/#hg%2Fimplementation%2Fopennlp\" target=_blank >OpenNLP</a>"=>"http://nlp2rdf.lod2.eu/demo/NIFOpenNLP" ,
          "<a href=\"https://github.com/kenda/nlp2rdf.MontyLingua\" target=_blank >MontyLingua</a> - NIF 1.0"=>"http://nlp2rdf.lod2.eu/demo/NIFMontyLingua" ,
          "<a href=\"https://github.com/robbl/node-dbpedia-spotlight-nif\" target=_blank >DBpedia Spotlight</a> - NIF 1.0"=>"http://nlp2rdf.lod2.eu/demo/NIFDBpediaSpotlight" 
        );
        $first = true;
        foreach($serviceCheckboxes as $key=>$value ){
		$checked = "";
		if(isset($_REQUEST['service']) && in_array($value, $_REQUEST['service'])) {
			$checked = "checked";
		}else if(!isset($_REQUEST['service']) && $first){
			$checked = "checked";
		}
		echo "<input type=\"checkbox\" name=\"service[]\" value=\"$value\" $checked /><label for=\"service[]\">&nbsp;$key</label><br/>\n";
		$first = false;
        }
        ?>

	<label for="text2"><p style="display:inline">Other NIF service:</p>&nbsp;</label><input id="text2" type="text" name="service[]" value="" /><br/>
	<p style="display:inline">Coming soon:</p><br/>
  <input type="checkbox" disabled="disabled" />
	<label>&nbsp;<a href="http://code.google.com/p/nlp2rdf/source/browse/#hg%2Fimplementation%2Fopennlp">OpenNLP(not working currently)</a></label><br />
	<input type="checkbox" disabled="disabled" />
	<label>&nbsp;<a href="https://bitbucket.org/gruenerkaktus/uimanif/overview">UIMA</a></label><br />
	<input type="checkbox" disabled="disabled" />
	<label>&nbsp;<a href="https://bitbucket.org/mack/mallet2nif">Mallet</a></label><br />
	<input type="checkbox" disabled="disabled" />
	<label>&nbsp;<a href="https://bitbucket.org/d_cherix/gan/overview">Gate ANNIE</a></label>
      </fieldset>
      <div style="float:right">
	<button type="reset" class="medium red"><span class="icon medium white" data-icon="x"></span> reset</button>
	<button type="submit" class="medium green"><span class="icon medium white" data-icon="C"></span> submit</button>
      </div> 
    </div>

    <hr style="display:none" />

    <div class="col_6">
          
      <fieldset>
	<legend>Input text</legend>
	<textarea name="text" ><?php 
          if (isset($_REQUEST['text'] )) {
	    echo $_REQUEST['text'] ;
          } else { 
            echo "President Obama on Monday will call for a new minimum tax rate for individuals making more than $1 million a year."  ;
          }  ?>
	</textarea>
      </fieldset>
    </div>
  </form>
  <div class="col_6">
    <fieldset>
      <legend>Merged Output</legend>
      <textarea name="output"><?php echo $output;?></textarea>
    </fieldset>
  </div>
  <hr style="display:none"/>
  <?php if ($output != "") echo '
    <span class="icon small gray show_hide" data-icon="s"></span>
    <span class="show_hide checks">Show/Hide Details</span>
    <div id="details">' . $meta .'</div>';
  ?>
  </body>
</html>
<?php } ?>
