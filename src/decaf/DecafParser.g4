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

field_decl: (LCURLY type ID | type ID LCONCHETE int_literal RCONCHETE RCURLY)+ VIRG PONTOVIRG;

type: (INT | BOOLEAN);

int_literal: INT;
