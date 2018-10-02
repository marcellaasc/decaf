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

field_decl: (type ID) (VIRG type ID)* PONTOVIRG 
	  | type ID LCOLCHETE int_literal RCOLCHETE (VIRG type ID LCOLCHETE int_literal RCOLCHETE)* PONTOVIRG;

field: type ID;


method_decl:(type|VOID) ID LPARENTESES (type ID (VIRG type ID)*)* RPARENTESES block;


type: INT | BOOLEAN;

int_literal: decimal_literal | hex_literal;

decimal_literal: NUMBER;

hex_literal: HEXLIT;

string_literal: STRING;


block: LCURLY (var_decl)* (statement)* RCURLY; 

var_decl: type ID (VIRG (type ID|ID))* PONTOVIRG;

statement: location assign_op expr PONTOVIRG 
	| method_call PONTOVIRG 
	| IF LPARENTESES expr RPARENTESES block (ELSE block)*
	| FOR ID OP_IGUAL expr VIRG expr block
	| RETURN expr* PONTOVIRG
	| BREAK PONTOVIRG
	| CONTINUE PONTOVIRG
	| block;

location: ID | (ID LCOLCHETE expr RCOLCHETE);

assign_op: OP_IGUAL | PLUS OP_IGUAL| MINUS OP_IGUAL;

expr: location | method_call | literal | expr bin_op expr | MINUS expr | EXCLAMACAO expr | LPARENTESES expr RPARENTESES;

method_name: ID;

callout_arg: expr
	   | string_literal;

method_call: method_name LPARENTESES (expr (VIRG expr)*)? RPARENTESES 
	     |  CALLOUT LPARENTESES STRING ((VIRG (expr | STRING))*) RPARENTESES;

literal: int_literal| CHARLITERAL | BOOLEANLIT;

arith_op: OP_ART
	 | MINUS
	 | PLUS;

real_op: OP_REL;

cond_op: OP_COND;

eq_op: OP_COMP;

bin_op: arith_op| real_op | eq_op | cond_op;



