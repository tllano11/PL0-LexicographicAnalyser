/* Analizador lexico para el lenguaje PL/0 */
package co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer;

import co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens.JFToken;
import java.io.IOException;

%%
%class tlJFlexLexer
%unicode
%line
%column
%function getNextToken
%type JFToken
%public

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\n\f]

Identifier = [:jletter:]([:jletterdigit:]|"_")*

DecIntegerLiteral = 0 | [1-9][0-9]*

Keyword           = "const" | "var" | "procedure" | "call" | "begin" | "end" | "if" | "then" | "while" | "do" | "odd"

%%


<YYINITIAL> {
   {Keyword}                     { return new JFToken(yytext(), yyline, yycolumn, 3); }
   {Identifier}                  { return new JFToken(yytext(), yyline, yycolumn, 4); }
   {DecIntegerLiteral}           { return new JFToken(yytext(), yyline, yycolumn, 5); }
   "(" | ")" | ";" | "," | "."
                                 { return new JFToken(yytext(), yyline, yycolumn, 1); }
   ":=" | "+" | "-" | "*" | "/" | "<" | "<=" | ">" | ">=" | "=" | "<>"
                                 { return new JFToken(yytext(), yyline, yycolumn, 2); }
   {WhiteSpace}                  { return new JFToken(yytext(), yyline, yycolumn, 6); }
}

<<EOF>> {
   return new JFToken("", yyline, 0, -1);
}

.                             {java.lang.System.out.println("Error: " + yytext() +
							    " fila: " + (yyline+1) +
							    " col: " + (yycolumn+1)); 
			      	throw new IOException();
			      }
