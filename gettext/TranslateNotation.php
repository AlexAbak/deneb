#!/usr/bin/php
<?php

  function cmp_po($a, $b) {
    $a = strlen($a);
    $b = strlen($b);
    return $b - $a;
  }

  function load_po ($filename) {
    $res = array();
    $po = file($filename);
    $id = '';
    $str = '';
    foreach ($po as $line) {
      if (substr($line, 0, 7) == 'msgid "') {
        $id = substr($line, 7, -2);
      } elseif (substr($line, 0, 8) == 'msgstr "') {
        $str = substr($line, 8, -2);
        $res[$id] = $str;
      }
    }
    uksort($res, 'cmp_po');
    return $res;
  }

  function translate_line($line, $po) {
    foreach ($po as $id => $str) {
      $line = str_replace($id, $str, $line);
    }
    return $line;
  }

  function translate_notation($notation, $lang, $po) {
    $res = array();
    foreach ($notation as $line) {
      if ((substr($line, 0, 2) == '//') || (trim($line) == '')) {
        $res[] = $line;
      } else {
        $res[] = translate_line($line, $po);
      }
    }
    file_put_contents('../Notation.'.$lang.'.nuss', $res);
  }

  $notation = file('../Notation.nuss');
  foreach (glob('translations/*.po') as $filename) {
    $lang = substr($filename, strrpos($filename, '.', -5) + 1, strlen($filename) - strrpos($filename, '.'));
    $po = load_po($filename);
    translate_notation($notation, $lang, $po);
  }

