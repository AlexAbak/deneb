#!/usr/bin/php
<?php

  function unicode_ord ($source, $encoding) {
    $source = mb_convert_encoding($source, 'UCS-2', $encoding);
    $r = 0;
    for ($i = 0; $i < strlen($source); $i++) {
      $c = substr($source, $i, 1);
      $r *= 256;
      $r += ord($c);
    }
    return $r;
  }

  function parse_characters($stack, $source, $position, $data, $encoding) {
    extract($data);
    if (array_key_exists("$id:$position", $stack)) {
      $result = false;
      return compact('result', 'position');
    }
    $character = mb_substr($source, $position, 1, $encoding);
    $order = unicode_ord($character, $encoding);
    $result = ($order >= $min) && ($order <= $max);
    $position = $position + 1;
    return compact('result', 'position');
  }

  function parse_string($stack, $source, $position, $data, $encoding) {
    extract($data);
    if (array_key_exists("$id:$position", $stack)) {
      $result = false;
      return compact('result', 'position');
    }
    extract($data);
    $string = mb_substr($source, $position, 1, $encoding);
    $result = ($string == $value);
    $position = $position + mb_strlen($string);
    return compact('result', 'position');
  }

  function parse_regexp($stack, $source, $position, $data, $encoding) {
    extract($data);
    if (array_key_exists("$id:$position", $stack)) {
      $result = false;
      return compact('result', 'position');
    }
    mb_regex_encoding($encoding);
    $string = mb_substr($source, $position, mb_strlen($source), $encoding);
    $value = '^' . $value;
    $result = mb_eregi($value, $string);
    if ($result) {
      mb_ereg_search_init($string, $value);
      list($pos, $len) = mb_ereg_search_pos();
      $position += $pos + $len;
    }
    return compact('result', 'position');
  }

  function parse_choice($stack, $source, $position, $data, $encoding) {
    extract($data);
    if (array_key_exists("$id:$position", $stack)) {
      $result = false;
      return compact('result', 'position');
    }
    $stack["$id:$position"] = true;
    foreach ($variants as $variant) {
      $function = 'parse_'.$kind;
      $result = $function($stack, $source, $position, $variant, $encoding);
      if ($result['result']) {
        return $result;
      }
    }
    $result = false;
    return compact('result', 'position');
  }

  function parse_lexeme($stack, $source, $position, $data, $encoding) {
    extract($data);
    if (array_key_exists("$id:$position", $stack)) {
      $result = false;
      return compact('result', 'position');
    }
    $stack["$id:$position"] = true;

  }

  function parse_list($stack, $source, $position, $data, $encoding) {
    extract($data);
    if (array_key_exists("$id:$position", $stack)) {
      $result = false;
      return compact('result', 'position');
    }
    $stack["$id:$position"] = true;

  }

  function parse_repeat($stack, $source, $position, $data, $encoding) {
    extract($data);
    if (array_key_exists("$id:$position", $stack)) {
      $result = false;
      return compact('result', 'position');
    }
    $stack["$id:$position"] = true;

  }

