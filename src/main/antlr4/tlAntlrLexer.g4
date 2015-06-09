lexer grammar tlAntlrLexer;

//Package to which java class (tlAntlrLexer.java) belongs.
@lexer::header{
  package co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer;
}

//Add exception condition
@lexer::rulecatch {
   catch (RecognitionException e) {
      throw e;
   }
}

//Lexer rules.

SEPARATORS
        : '('
        | ')'
        | '.'
        | ';'
        | ','
        ;

OPERATORS
        : '='
        | '+'
        | '-'
        | '*'
        | '/'
        | '<'
        | '<='
        | '>'
        | '>='
        | ':='
        | '<>'
        ;

KEYWORDS
    : 'const'
    | 'var'
    | 'procedure'
    | 'call'
    | 'begin'
    | 'end'
    | 'if'
    | 'then'
    | 'while'
    | 'do'
    | 'odd'
    ;

ID  :   ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|
         '\u00C0' .. '\u00FF')*
    ;

INT :   '0' | ('1'..'9')+('0'..'9')*
    ;


WHITESPACE
    :   (' ' | '\t' | '\r' | '\n' | '\f') -> skip
    ;
