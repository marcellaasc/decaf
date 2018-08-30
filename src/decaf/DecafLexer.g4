lexer grammar DecafLexer;

@header {
package decaf;
}

options
{
  language=Java;
}

tokens
{
  TK_class
}

LCURLY : '{';
RCURLY : '}';

CHAR : '\'' (ESC|'a'..'z' |'A'..'Z'| NUMBER|~'\'') '\'';

ID  :
  ('a'..'z' | 'A'..'Z' | '_')('a'..'z' | 'A'..'Z' | '_' | NUMBER)*;

WS_ : (' '|'\n'|'\t') -> skip;

SL_COMMENT : '//' (~'\n')* '\n' -> skip;

STRING : '"' (ESC|~'"')* '"';

fragment
ESC :  '\\' ('n'|'"'|'t'|'\\'|'\'');

NUMBER :
  ('0'..'9');
