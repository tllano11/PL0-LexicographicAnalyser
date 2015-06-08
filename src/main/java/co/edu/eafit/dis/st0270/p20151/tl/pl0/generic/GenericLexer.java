package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlAntlrLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlJFlexLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.GenericToken;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.VerboseListener;
import java.io.IOException;

/**
 * @class Lexer for the PL0 programming Language
 *        Generic implementation for auto generated lexer antlr and jflex
 */
public class GenericLexer {

  //type of lexer; antlr = 1 | jflex = 2
  private int type;

  private tlAntlrLexer antlrLexer;
  private tlJFlexLexer jFlexLexer;

  /**
   * @method Constructor for antlr4
   * @param antlr lexer
   */
  public GenericLexer (tlAntlrLexer antlrLexer) {
    //The verbose listener is add the the antlr lexer
    antlrLexer.removeErrorListeners();
    antlrLexer.addErrorListener(VerboseListener.INSTANCE);
    this.antlrLexer = antlrLexer;
    this.jFlexLexer = null;
    this.type = 1;
  }

  /**
   * @method Constructor for jflex
   * @param jflex lexer
   */
  public GenericLexer (tlJFlexLexer jFlexLexer) {
    this.antlrLexer = null;
    this.jFlexLexer = jFlexLexer;
    this.type = 2;
  }

  /**
   * @method get the next token
   * @return generic token
   */
  public GenericToken getToken() throws IOException {
    if (type == 1) return new GenericToken(antlrLexer.nextToken());
    if (type == 2) return new GenericToken(jFlexLexer.getNextToken());
    return null;
  }
}
