parser grammar DecafParser;

@header {
package decaf;
}

options
{
  language=Java;
  tokenVocab=DecafLexer;
}


program: CLASS PROGRAM LCURLY  field_decl* method_decl* RCURLY EOF; 

field_decl: (type ID) (COMMA type ID)* SEMICOLON 
	  | type ID LBRACKET int_literal RBRACKET (COMMA type ID LBRACKET int_literal RBRACKET)* SEMICOLON;

field: type ID;


method_decl:(type|VOID) ID LPARENTHESES (type ID (COMMA type ID)*)* RPARENTHESES block;


type: INT | BOOLEAN;

int_literal: decimal_literal | hex_literal;

decimal_literal: INTLIT;

hex_literal: HEXLIT;

string_literal: STRING;


block: LCURLY (var_decl)* (statement)* RCURLY; 

var_decl: type ID (COMMA (type ID|ID))* SEMICOLON;

statement: location assign_op expr SEMICOLON 
	| method_call SEMICOLON 
	| IF LPARENTHESES expr RPARENTHESES block (ELSE block)*
	| FOR ID OP_EQUAL expr COMMA expr block
	| RETURN (expr)* SEMICOLON
	| BREAK SEMICOLON
	| CONTINUE SEMICOLON
	| block;

location: ID | (ID LBRACKET expr RBRACKET);

assign_op: OP_EQUAL | PLUS OP_EQUAL| MINUS OP_EQUAL;

expr: location | method_call | literal | expr bin_op expr | MINUS expr | EXCLAMATION expr | LPARENTHESES expr RPARENTHESES;

method_name: ID;

callout_arg: expr
	   | string_literal;

method_call: method_name LPARENTHESES (expr (COMMA expr)*)* RPARENTHESES 
	     |  CALLOUT LPARENTHESES STRING ((COMMA (expr | STRING))*) RPARENTHESES;

literal: int_literal| CHARLITERAL | BOOLEANLIT;

arith_op: OP_ARIT
	 | MINUS
	 | PLUS;

real_op: OP_REL;

cond_op: OP_COND;

eq_op: OP_COMP;

bin_op: arith_op| real_op | eq_op | cond_op;



