#!/usr/bin/php
<?php

  function get_msgstr($po, $msgid) {
    $is_id = false;
    foreach ($po as $line) {
      if (substr($line, 0, 7) == 'msgid "') {
        $id = substr($line, 7, -2);
        if ($id == $msgid) {
          $is_id = true;
        }
      } elseif (substr($line, 0, 8) == 'msgstr "') {
        if ($is_id) {
          $str = substr($line, 8, -2);
          return $str;
        }
      }
    }
    return '';
  }

  $pot = file('Notation.pot');
  foreach (glob('translations/*.po') as $filename) {
    $po = file($filename);
    $new = array();
    $msgstr = '';
    foreach ($pot as $line) {
      if (substr($line, 0, 7) == 'msgid "') {
        $msgid = substr($line, 7, -2);
        $msgstr = get_msgstr($po, $msgid);
        $new[] = $line;
      } elseif (substr($line, 0, 8) == 'msgstr "') {
        $new[] = "msgstr \"$msgstr\"\n";
      } else {
        $new[] = $line;
      }
    }
    file_put_contents($filename . '.new', $new);
  }

