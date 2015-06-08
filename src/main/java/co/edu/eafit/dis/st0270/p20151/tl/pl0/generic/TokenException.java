package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import java.lang.*;

/**
 * @class Bad token Exception for Lexer
 */
public class TokenException extends Exception {

  /**
   * @method Exception constructor with a message
   */
  public TokenException (GenericToken tok) {
    super ("Error: " + tok.getLex() + " fila: " + tok.getLine() + " col: "
           + tok.getCol());
  }
}
