parser grammar DecafParser;

@header {
package decaf;
}

options
{
  language=Java;
  tokenVocab=DecafLexer;
}


program: CLASS PROGRAM LCURLY (field_decl)* (method_decl)*   RCURLY EOF; 

field_decl: (type ID (VIRG type ID)*| type ID LCOLCHETE int_literal RCOLCHETE (VIRG type ID LCOLCHETE int_literal RCOLCHETE)*)? PONTOVIRG;

type: (INT | BOOLEAN);

int_literal: INT;

method_decl:(type|VOID) ID LPARENTESES (type ID (VIRG type ID)*) RPARENTESES block;

block: LCURLY (var_decl)* (statement)* RCURLY; 

var_decl: type ID (VIRG (type ID|ID))* PONTOVIRG;

statement: location assign_op expr PONTOVIRG 
	| method_call PONTOVIRG 
	| IF LPARENTESES expr RPARENTESES block (ELSE block)
	| FOR ID OP_IGUAL expr VIRG expr block
	| RETURN (expr)? PONTOVIRG
	| BREAK PONTOVIRG
	| CONTINUE PONTOVIRG
	| block;

location: ID | (ID LCOLCHETE expr RCOLCHETE);

assign_op: OP_IGUAL | PLUS OP_IGUAL| MINUS OP_IGUAL;

expr: location | method_call | literal | expr bin_op expr | MINUS expr | EXCLAMACAO expr | LPARENTESES expr RPARENTESES;

method_call: ID LPARENTESES (expr (VIRG expr)*)? RPARENTESES 
	     |  CALLOUT LPARENTESES STRING ((VIRG (expr | STRING))*) RPARENTESES;

literal: INT| CHARLITERAL | BOOLEANLIT;

bin_op: (PLUS | MINUS | OP_ART) | OP_REL | OP_COMP | OP_COND;


