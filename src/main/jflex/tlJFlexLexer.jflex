/* Analizador lexico para el lenguaje PL/0 */
package co.edu.eafit.dis.st0270.p20151.tl.pl0.jflex;

import co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens.KeywordToken;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens.IdentifierToken;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens.IntegerLiteralToken;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens.SeparatorToken;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens.OperatorToken;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens.Token;

%%
%class tlJFlexLexer
%unicode
%line
%column
%function getNextToken
%type Token
%public

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\n\f]

Identifier = [:jletter:]([:jletterdigit:]|"_")*

DecIntegerLiteral = 0 | [1-9][0-9]*

Keyword           = "const" | "var" | "procedure" | "call" | "begin" | "end" | "if" | "then" | "while" | "do" | "odd"

%%


<YYINITIAL> {
   {Keyword}                     { return new KeywordToken(yytext(), yyline, yycolumn); }
   {Identifier}                  { return new IdentifierToken(yytext(), yyline, yycolumn); }
   {DecIntegerLiteral}           { return new IntegerLiteralToken(yytext(), yyline, yycolumn); }
   "(" | ")" | ";" | "," | "."
                                 { return new SeparatorToken(yytext(), yyline, yycolumn); }
   ":=" | "+" | "-" | "*" | "/" | "<" | "<=" | ">" | ">=" | "=" | "<>"
                                 { return new OperatorToken(yytext(), yyline, yycolumn); }
   {WhiteSpace}                  { /* Ignore */ }
}

.|\n                             { throw new Error("Illegal character <" + yytext() + "> at line: " + (yyline + 1) + " column: " + yycolumn); }
