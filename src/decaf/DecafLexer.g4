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

IF : 'if';
MINUS: '-';

ID  :
  ('a'..'z' | 'A'..'Z' | '_')('a'..'z' | 'A'..'Z' | '_' | NUMBER_AUX)*;


CHARLITERAL : '\'' (ESC|'a'..'z' |'A'..'Z'| NUMBER_AUX|'\\t'|'\\\\') '\'';

STRING : '"'(ESC|ESC_STRING|~('"'))* '"';

SL_COMMENT : '//' (~'\n')* '\n' -> skip;

HEXLIT:'0x'('a'..'f'| 'A'..'F'| '0'..'9')+;

NUMBER:
 ('0'..'9')+ (~('a'..'z'));


OP_ART:('+'|'*');
OP_REL:('<'|'<=');
OP_COMP:('!=');
OP_COND:('&&');

fragment
ESC :  '\\'('n'|'"'|'t'|'\\'|'\'');

fragment
ESC_STRING: ~('\'');

fragment
NUMBER_AUX :
  ('0'..'9');

WS_ : (' '|'\n'|'\t') -> skip;
