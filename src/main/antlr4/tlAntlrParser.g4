/*
 * "grammar" specifies that tlAntlrParser.g4 is a "combined grammar" file, 
 * which means it accepts "parser grammar" and "lexer grammar" syntax.
 * Remark: Lexer rules start with a capital letter and parser rules start 
 * with a lowercase letter. 
 */
grammar tlAntlrParser;

//Package to which java class (tlAntlrParserParser.java) belongs
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
   * @method Check if the integer is lower than 2^31
   *         If not, throw a RuntimeException
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
   * @method Check if the id's length is lower than 31 characters
   *         If not, throw a RuntimeException
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

//Add exception condition
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
