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

CHAR : '\'' (ESC|'a'..'z' |'A'..'Z'| NUMBER|'\\t'|'\\\\') '\'';

ID  :
  ('a'..'z' | 'A'..'Z' | '_')('a'..'z' | 'A'..'Z' | '_' | NUMBER)*;

SL_COMMENT : '//' (~'\n')* '\n' -> skip;

STRING : '"' (ESC|~'"')* '"';

fragment
ESC :  '\\'('n'|'"'|'t'|'\\'|'\'');

fragment
NUMBER :
  ('0'..'9');

WS_ : (' '|'\n'|'\t') -> skip;
