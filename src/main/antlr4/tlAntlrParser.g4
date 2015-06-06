/*
 * "grammar" specifies that tlAntlrParser.g4 is a "combined grammar" file, 
 * which means it accepts "parser grammar" and "lexer grammar" syntax.
 * Remark: Lexer rules start with a capital letter and parser rules start 
 * with a lowercase letter. 
 */
grammar tlAntlrParser;

//Package to which java class (tlAntlrParser.java) belongs
@parser::header{
  package co.edu.eafit.dis.st0270.p20151.tl.pl0.parser;
}
//options { tokenVocab=tlAntlrLexer; }

program 
    : block '.' #pBlock
    ;

block
    : defconst? defvar? (defproc)*instruction #bDefBlock
    ;

defconst
    : 'const' ID ':' INT (',' ID ':' INT)* ';' #dcConst
    ;

defvar
    : 'var' ID (',' ID)* ';' #dvVar
    ;

defproc
    : 'procedure' ID ';' block ';' #dpProcedure
    ;

instruction
    : ID ':=' expr #iID
    | 'call' ID #iCall
    | 'begin' instruction (';' instruction)* ';'? 'end' #iBegin
    | 'if' condition 'then' instruction #iIf
    | 'while' condition 'do' instruction #iWhile
    | #iEpsilon
    ;

condition
    : 'odd' expr #cOdd
    | expr COMPARISON expr #cExpr
    ;

expr
    : ('+'|'-')* term (('+'|'-') term)* #eOperators
    ;

term
    : factor (('+'|'-') factor)* #tFactor
    ;

factor
    : ID #fID
    | INT #fInt
    | '(' expr ')' #fExpr
    ;

//Lexer rules
COMPARISON
        : '='
        | '+'
        | '-'
        | '*'
        | '/'
        | '<'
        | '<='
        | '>'
        | '>='
        | '<>'
        ;

ID  :   ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|
         '\u00C0' .. '\u00FF')*
    ;

INT :   '0' | ('1'..'9')+('0'..'9')*
    ;
