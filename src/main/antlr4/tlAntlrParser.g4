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
    : block '.'
    ;

block
    : defconst? defvar? (defproc)*instruction
    ;

defconst
    : 'const' ID ':' INT (',' ID ':' INT)* ';'
    ;

defvar
    : 'var' ID (',' ID)* ';'
    ;

defproc
    : 'procedure' ID ';' block ';'
    ;

instruction
    : ID ':=' expr
    | 'call' ID
    | 'begin' instruction (';' instruction)* ';'? 'end'
    | 'if' condition 'then' instruction
    | 'while' condition 'do' instruction
    |
    ;

condition
    : 'odd' expr
    | expr OPERATORS expr
    ;

expr
    : ('+'|'-')* term (('+'|'-') term)*
    ;

term
    : factor (('+'|'-') factor)*
    ;

factor
    : ID
    | INT
    | '(' expr ')'
    ;

