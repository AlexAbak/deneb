#!/usr/bin/php
<?php

  function create_choice($dom, $root, $lexeme, $dfn) {
    $choice = $dom->createElement('choice');
    $choice->setAttribute('name', $lexeme);
    $root->appendChild($choice);
    $list = explode('/', $dfn);
    foreach ($list as $item) {
      $item = trim($item);
      if (substr($item, 0, 1) == '%') {
        $ignore = 'true';
        $item = substr($item, 1);
      } else {
        $ignore = 'false';
      }
      $variant = $dom->createElement('variant');
      $variant->setAttribute('name', $item);
      $variant->setAttribute('ignore', $ignore);
      $choice->appendChild($variant);
    }
  }

  foreach (glob('../*.nuss') as $filename) {
    $dom = new DOMDocument('1.0', 'utf-8');
    $root = $dom->createElement('notation');
    $dom->appendChild($root);
  
    $notation = file($filename);
    $lang = substr($filename, -8, 3);
    if (substr($lang, 0, 1) != '.') {
      $lang = '';
      $name = substr($filename, 0, -5);
    } else {
      $lang = substr($lang, -2, 2);
      $name = substr($filename, 0, -8);
    }
    $root->setAttribute('lang', $lang);
    foreach ($notation as $line) {
      if ((trim($line) != '') && (substr($line, 0, 2) != '//')) {
        $pos = strpos($line, ':=');
        $lexeme = trim(substr($line, 0, $pos - 1));
        $dfn = trim(substr($line, $pos + 2, -2));
        if (strpos($dfn, '"') !== FALSE) {
//          create_string($dom, $root, $lexeme, $dfn);
        } elseif (strpos($dfn, '/') !== FALSE) {
          create_choice($dom, $root, $lexeme, $dfn);
        } else {
          echo $dfn."\n";
        }
      }
    }
    if ($lang == '') {
      $filename = $name . '.xml';
    } else {
      $filename = $name . '.' . $lang . '.xml';
    }
    $dom->formatOutput = true;
    $dom->save($filename);
  }

