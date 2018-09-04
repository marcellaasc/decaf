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
BOOLEANLIT: ('false'|'true');
BOOLEAN: 'boolean';
CALLOUT: 'callout';
CLASS: 'class';
ELSE: 'else';
INT: 'int';
RETURN: 'return';
VOID: 'void';
FOR: 'for';
BREAK: 'break';
CONTINUE: 'continue';

MINUS: '-';

ID  :
  ('a'..'z' | 'A'..'Z' | '_')('a'..'z' | 'A'..'Z' | '_' | NUMBER_AUX)*;



CHARLITERAL: '\'' (' '..'!' | '#'..'&' | '('..'[' | ']'..'~'| '\\t'|'\\\\'| ESC) '\'';

STRING : '"'(' '..'!' | '#'..'&' | '('..'[' | ']'..'~'| '\\t'|'\\\\'| ESC)* '"';

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
ESCZ : '\\'( 'n' | 't' |'\\'|'\"' | '\'' );

fragment
ESC_STRING: ~('\'');

fragment
NUMBER_AUX :
  ('0'..'9');

WS_ : (' '|'\n'|'\t') -> skip;
