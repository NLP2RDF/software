<?
$querydir = "../sparqltests";




class Validator {
	// find sparqltests | xargs | sed 's/^sparqltests /"/;s/ /","/g;s/$/"/'
	var $queries = array("sparqltests/beginEndIndexAreNonNegativeInteger.sparql","sparqltests/contextHasIsString.sparql","sparqltests/isStringLength.sparql","sparqltests/anchorOfShouldMatchisStringSubstr.sparql","sparqltests/hasReferenceContext.sparql","sparqltests/RFC5147StringHasBeginIndex.sparql","sparqltests/RFC5147StringHasEndIndex.sparql","sparqltests/beginIndexOfContextShouldBeZero.sparql","sparqltests/contextTypedAsRFC5147String.sparql","sparqltests/RFC5147StringMisspelling.sparql");
	
	function check_vars($arr){
		
		$
		
		foreach ($this->queries as $q){
			echo "file exists ".file_exists($q);
		}
		
	}
	
	function validate($aaa){
		
		}

}
