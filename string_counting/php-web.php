<?php

$input = $_REQUEST['input'];

echo "Encoding: ".mb_detect_encoding($input)."\n";
echo " is normalized NFC? ".Normalizer::isNormalized($input,Normalizer::FORM_C)."\n";
echo " is normalized NFD? ".Normalizer::isNormalized($input,Normalizer::FORM_D)."\n";
echo " is normalized NFKC? ".Normalizer::isNormalized($input,Normalizer::FORM_KC)."\n";
echo " is normalized NFKD? ".Normalizer::isNormalized($input,Normalizer::FORM_KD)."\n";
 printme($input, Normalizer::FORM_C);
 printme($input, Normalizer::FORM_D);
 printme($input, Normalizer::FORM_KC);
 printme($input, Normalizer::FORM_KD);



/**
$input = Normalizer::normalize($input,Normalizer::FORM_C);
echo "$input|\n";
echo "strlen:".strlen($input)."|\n";
echo "strlen_dec:".strlen(utf8_decode($input))."|\n";
echo "count:".count($input)."|\n";
echo "NFC?:".Normalizer::isNormalized($input,Normalizer::FORM_C)."|\n";
var_dump ( $input);

$input = Normalizer::normalize($input, Normalizer::FORM_D);
echo "$input|\n";
echo "strlen:".strlen($input)."|\n";
echo "strlen_dec:".strlen(utf8_decode($input))."|\n";
echo "count:".count($input)."|\n";
echo "NFC?:".Normalizer::isNormalized($input,Normalizer::FORM_C)."|\n";
var_dump ( $input);

*/
function printme($input, $nf){
	$input = Normalizer::normalize($input,$nf);
	switch($nf){
		case Normalizer::FORM_C: $nf = "NFC"; break;
		case Normalizer::FORM_D: $nf = "NFD"; break;
		case Normalizer::FORM_KC: $nf = "NFKC"; break;
		case Normalizer::FORM_KD: $nf = "NFKD"; break;
	}
	
	echo "/***".$nf."***".$input."***\n";
var_dump ( $input);
echo "strlen_dec:".strlen(utf8_decode($input))."\n";
echo "count_chars:".count_chars_unicode($input)."\n";

	echo "\n";
	}

function count_chars_unicode($str, $x = false) {
	$chr=array();
    $tmp = preg_split('//u', $str, -1, PREG_SPLIT_NO_EMPTY);
    foreach ($tmp as $c) {
        
       echo "aaa   $c   aaa\n";
    }
    return count($tmp);
}

function count_chars_unicode2($str, $x = false) {
	$chr=array();
    $tmp = preg_split('//u', $str, -1, PREG_SPLIT_NO_EMPTY);
    foreach ($tmp as $c) {
        $chr[$c] = isset($chr[$c]) ? $chr[$c] + 1 : 1;
    }
    return is_bool($x)
        ? ($x ? $chr : count($chr))
        : $chr[$x];
}
