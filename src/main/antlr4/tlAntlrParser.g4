/*
 * "grammar" specifies that tlAntlrParser.g4 is a "combined grammar" file,
 * which means it accepts "parser grammar" and "lexer grammar" syntax.
 * Remark: Lexer rules' first letter is a capital letter and parser rules'
 * first letter is a lowercase letter.
 */
grammar tlAntlrParser;

//Package to which java class (tlAntlrParserParser.java) belongs.
@parser::header{
  package co.edu.eafit.dis.st0270.p20151.tl.pl0.parser;
}

//Package to which java class (tlAntlrParserLexer.java) belongs
@lexer::header{
  package co.edu.eafit.dis.st0270.p20151.tl.pl0.parser;
}

//User defined functions (Lexer)
@lexer::members {

  /**
   * Checks if the integer is greater than 2^31.
   * If not, it throws a RuntimeException.
   * - Remark: When throwing an exception at parsing time,
   *   it must be RuntimeException or those provided by
   *   antlr.
   * - Remark: Antlr exceptions extends RuntimeException.
   */
  public void evalInt (String num) throws RuntimeException {
    if (Long.parseLong(num) > 2147483647L) {
      throw new RuntimeException("Line:" + getLine() + " Column:"
       			         + getCharPositionInLine()
			         + " Integer is greater than 2^31 at: '"
			         + getText() + "'");
    }
  }

  /**
   * Checks if the id's length is greater than 32 characters.
   * If not, it throws a RuntimeException.
   */
  public void evalId (String id) throws RuntimeException {
    if(id.length() > 32){
      throw new RuntimeException("Line:" + getLine() + " Column:"
       			         + getCharPositionInLine()
			         + " id's length is greater than 32 at: '"
			         + getText() + "'");
    }
  }
}

//Adds exception condition
@lexer::rulecatch {
   catch (RecognitionException e) {
      throw e;
   }
}

//Add Options
options {
  defaultErrorHandler=false;
}

//Parser Grammar
/*
 * Parser grammar.
 *  -Remark: "#..." labels define visitors rules.
 *  -Remark: {...} -> stands for an "action".
 */
program
    : block '.' #pBlock
    ;

block
    : defconst? defvar? (defproc)* instruction #bDefBlock
    ;

defconst
    : 'const' ID '=' INT (',' ID '=' INT)* ';' #dcConst
    ;

defvar
    : 'var' ID (',' ID)* ';' #dvVar
    ;

defproc
    : 'procedure' ID ';' block ';' #dpProc
    ;

instruction
    : ID ':=' expr #iID
    | 'call' ID #iCall
    | 'begin' instruction (';' instruction)* 'end' #iBegin
    | 'if' condition 'then' instruction #iIf
    | 'while' condition 'do' instruction #iWhile
    | #iEpsilon
    ;

condition
    : 'odd' expr #cOdd
    | expr COMPARISON expr #cComp
    ;

expr
    : ('+'|'-')? term (('+'|'-') term)* #eExpr
    ;

term
    : factor (('*'|'/') factor)* #tTerm
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
{
  evalId(getText());
}
;

INT :   ('0' | ('1'..'9')+('0'..'9')*)

{
  evalInt(getText());
}
;

WHITESPACE
    :   (' ' | '\t' | '\r' | '\n' | '\f') -> skip
    ;
