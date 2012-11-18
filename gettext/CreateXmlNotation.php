#!/usr/bin/php
<?php

  function decode_notation_char($matches) {
    return html_entity_decode('&#'.$matches[1].';', ENT_QUOTES, 'UTF-8');
  }

  function decode_notation_string($source) {
  	return preg_replace_callback('/№([0-9]+);/', 'decode_notation_char', $source);
  }

  function create_string($dom, $root, $lexeme, $dfn) {
    $definition = $dom->createElement('definition');
    $definition->setAttribute('lexeme', $lexeme);
    $root->appendChild($definition);
    $string = $dom->createElement('string');
    $string->setAttribute('value', decode_notation_string(substr($dfn, 1, -1)));
    $definition->appendChild($string);
  }

  function create_chars($dom, $root, $lexeme, $dfn) {
    $definition = $dom->createElement('definition');
    $definition->setAttribute('lexeme', $lexeme);
    $root->appendChild($definition);
    $char = $dom->createElement('char');
    $min = substr($dfn, 1, strpos($dfn, ':') - 2);
    $max = substr($dfn, strpos($dfn, ':') + 2, -1);
    $char->setAttribute('min', decode_notation_string($min));
    $char->setAttribute('max', decode_notation_string($max));
    $definition->appendChild($char);
  }

  function create_choice($dom, $root, $lexeme, $dfn) {
    $definition = $dom->createElement('definition');
    $definition->setAttribute('lexeme', $lexeme);
    $root->appendChild($definition);
    $choice = $dom->createElement('choice');
    $definition->appendChild($choice);
    $list = explode('/', $dfn);
    foreach ($list as $item) {
      $item = trim($item);
      if (substr($item, 0, 1) == '%') {
        $ignore = 'true';
        $item = substr($item, 1);
      } else {
        $ignore = 'false';
      }
      $lexeme_node = $dom->createElement('lexeme');
      $lexeme_node->setAttribute('name', $item);
      $lexeme_node->setAttribute('ignore', $ignore);
      $choice->appendChild($lexeme_node);
    }
  }

  function create_repeat_all($dom, $root, $lexeme, $dfn) {
    $dfn = str_replace('*', '', $dfn);
    $definition = $dom->createElement('definition');
    $definition->setAttribute('lexeme', $lexeme);
    $root->appendChild($definition);
    $repeat = $dom->createElement('repeat');
    $repeat->setAttribute('generosity', (strpos($dfn, '!') !== FALSE)?'true':'false');
    $dfn = str_replace('!', '', $dfn);
    $definition->appendChild($repeat);
    $ranges = $dom->createElement('ranges');
    $repeat->appendChild($ranges);
    $range = $dom->createElement('range');
    $range->setAttribute('min', 0);
    $range->setAttribute('max', 0);
    $ranges->appendChild($range);
    $body = $dom->createElement('body');
    $repeat->appendChild($body);
    $lexeme_node = $dom->createElement('lexeme');
    $lexeme_node->setAttribute('name', $dfn);
    $lexeme_node->setAttribute('ignore', 'false');
    $body->appendChild($lexeme_node);
    $glue = $dom->createElement('glue');
    $repeat->appendChild($glue);
  }

  function create_repeat_glue($dom, $root, $lexeme, $dfn) {
    $definition = $dom->createElement('definition');
    $definition->setAttribute('lexeme', $lexeme);
    $root->appendChild($definition);
    $repeat = $dom->createElement('repeat');
    $repeat->setAttribute('generosity', (strpos($dfn, '!') !== FALSE)?'true':'false');
    $dfn = str_replace('!', '', $dfn);
    $definition->appendChild($repeat);
    $ranges = $dom->createElement('ranges');
    $repeat->appendChild($ranges);
    $dfn = substr($dfn, 1, -3);
    $range = $dom->createElement('range');
    $range->setAttribute('min', 2);
    $range->setAttribute('max', 0);
    $ranges->appendChild($range);
    $body_lexeme = trim(substr($dfn, 0, strpos($dfn, '_')));
    $body_lexeme_ignore = (strpos($body_lexeme, '%') !== FALSE)?'true':'false';
    $body_lexeme = str_replace('%', '', $body_lexeme);
    $glue_lexeme = trim(substr($dfn, strpos($dfn, '_') + 1));
    $glue_lexeme_ignore = (strpos($glue_lexeme, '%') !== FALSE)?'true':'false';
    $glue_lexeme = str_replace('%', '', $glue_lexeme);
    $body = $dom->createElement('body');
    $repeat->appendChild($body);
    $lexeme_node = $dom->createElement('lexeme');
    $lexeme_node->setAttribute('name', $body_lexeme);
    $lexeme_node->setAttribute('ignore', $body_lexeme_ignore);
    $body->appendChild($lexeme_node);
    $glue = $dom->createElement('glue');
    $repeat->appendChild($glue);
    $lexeme_node = $dom->createElement('lexeme');
    $lexeme_node->setAttribute('name', $glue_lexeme);
    $lexeme_node->setAttribute('ignore', $glue_lexeme_ignore);
    $glue->appendChild($lexeme_node);
  }

  function create_repeat_minus($dom, $root, $lexeme, $dfn) {
    $dfn = str_replace('-', '', $dfn);
    $definition = $dom->createElement('definition');
    $definition->setAttribute('lexeme', $lexeme);
    $root->appendChild($definition);
    $repeat = $dom->createElement('repeat');
    $repeat->setAttribute('generosity', (strpos($dfn, '!') !== FALSE)?'true':'false');
    $dfn = str_replace('!', '', $dfn);
    $definition->appendChild($repeat);
    $ranges = $dom->createElement('ranges');
    $repeat->appendChild($ranges);
    $range = $dom->createElement('range');
    $range->setAttribute('min', 0);
    $range->setAttribute('max', 1);
    $ranges->appendChild($range);
    $body = $dom->createElement('body');
    $repeat->appendChild($body);
    $lexeme_node = $dom->createElement('lexeme');
    $lexeme_node->setAttribute('name', $dfn);
    $lexeme_node->setAttribute('ignore', 'false');
    $body->appendChild($lexeme_node);
    $glue = $dom->createElement('glue');
    $repeat->appendChild($glue);
  }

  function create_repeat_plus($dom, $root, $lexeme, $dfn) {
    $dfn = str_replace('+', '', $dfn);
    $definition = $dom->createElement('definition');
    $definition->setAttribute('lexeme', $lexeme);
    $root->appendChild($definition);
    $repeat = $dom->createElement('repeat');
    $repeat->setAttribute('generosity', (strpos($dfn, '!') !== FALSE)?'true':'false');
    $dfn = str_replace('!', '', $dfn);
    $definition->appendChild($repeat);
    $ranges = $dom->createElement('ranges');
    $repeat->appendChild($ranges);
    $range = $dom->createElement('range');
    $range->setAttribute('min', 1);
    $range->setAttribute('max', 0);
    $ranges->appendChild($range);
    $body = $dom->createElement('body');
    $repeat->appendChild($body);
    $lexeme_node = $dom->createElement('lexeme');
    $lexeme_node->setAttribute('name', $dfn);
    $lexeme_node->setAttribute('ignore', 'false');
    $body->appendChild($lexeme_node);
    $glue = $dom->createElement('glue');
    $repeat->appendChild($glue);
  }

  function create_lexeme($dom, $root, $lexeme, $dfn) {
    $definition = $dom->createElement('definition');
    $definition->setAttribute('lexeme', $lexeme);
    $root->appendChild($definition);
    $lexeme_node = $dom->createElement('lexeme');
    $lexeme_node->setAttribute('name', $dfn);
    $lexeme_node->setAttribute('ignore', 'false');
    $definition->appendChild($lexeme_node);
  }

  function create_list($dom, $root, $lexeme, $dfn) {
    $definition = $dom->createElement('definition');
    $definition->setAttribute('lexeme', $lexeme);
    $root->appendChild($definition);
    $list = $dom->createElement('list');
    $definition->appendChild($list);
    $items = explode(' ', $dfn);
    foreach ($items as $item) {
      $item = trim($item);
      if (substr($item, 0, 1) == '%') {
        $ignore = 'true';
        $item = substr($item, 1);
      } else {
        $ignore = 'false';
      }
      $lexeme_node = $dom->createElement('lexeme');
      $lexeme_node->setAttribute('name', $item);
      $lexeme_node->setAttribute('ignore', $ignore);
      $list->appendChild($lexeme_node);
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
        if (substr_count($dfn, '"') == 2) {
          create_string($dom, $root, $lexeme, $dfn);
        } elseif (substr_count($dfn, '"') == 4) {
          create_chars($dom, $root, $lexeme, $dfn);
        } elseif (strpos($dfn, '/') !== FALSE) {
          create_choice($dom, $root, $lexeme, $dfn);
        } elseif (strpos($dfn, '_') !== FALSE) {
          create_repeat_glue($dom, $root, $lexeme, $dfn);
        } elseif (strpos($dfn, '*') !== FALSE) {
          create_repeat_all($dom, $root, $lexeme, $dfn);
        } elseif (strpos($dfn, '-') !== FALSE) {
          create_repeat_minus($dom, $root, $lexeme, $dfn);
        } elseif (strpos($dfn, '+') !== FALSE) {
          create_repeat_plus($dom, $root, $lexeme, $dfn);
        } elseif (substr_count($dfn, ' ') == 0) {
          create_lexeme($dom, $root, $lexeme, $dfn);
        } else {
          create_list($dom, $root, $lexeme, $dfn);
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

