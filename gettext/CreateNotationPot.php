#!/usr/bin/php
<?php

  function unicode_ord ($source) {
    $source = mb_convert_encoding($source, 'UCS-2', 'UTF-8');
    $r = 0;
    for ($i = 0; $i < strlen($source); $i++) {
      $c = substr($source, $i, 1);
      $r *= 256;
      $r += ord($c);
    }
    return $r;  
  }

  function split_cyr_words($source) {
    $cyr = '';
    $pos = 0;
    $r = array();
    for ($i = 0; $i < mb_strlen($source, 'UTF-8'); $i++) {
      $c = mb_substr($source, $i, 1, 'UTF-8');
      $o = unicode_ord($c);
      if (($o >= 1024) && ($o <= 1319)) {
        if ($cyr == '') {
          $pos = $i + 1;
        }
        $cyr .= $c;
      } else {
        if ($cyr != '') {
          $r[$cyr][] = $pos;
          $cyr = '';
        }
      }      
    }
    if ($cyr != '') {
      $r[$cyr][] = $pos;
    }
    return $r;
  }

  $notation = file('../Notation.nuss');
  $line_number = 1;
  $cyrs = array();
  foreach ($notation as $line) {
    if ((trim($line) != '') && (substr($line, 0, 2) != '//')) {
      $r = split_cyr_words($line);
      foreach ($r as $cyr => $poss) {
        foreach ($poss as $pos) {
          $cyrs[$cyr][] = array('line' => $line_number, 'pos' => $pos);
        }
      }
    }
    $line_number += 1;
  }
  
  $res = '';
  foreach ($cyrs as $cyr => $poss) {
    $res .= "#: Notation.nuss: ";
    foreach ($poss as $pos) {
      $res .= $pos['line'].','.$pos['pos'].' ';
    }
    $res .= "\n";
    $res .= 'msgid "'.$cyr.'"'."\n";
    $res .= 'msgstr ""'."\n";
    $res .= "\n";
  }
  file_put_contents('Notation.pot', $res);
  
