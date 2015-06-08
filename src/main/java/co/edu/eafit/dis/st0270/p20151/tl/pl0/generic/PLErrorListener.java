package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import org.antlr.v4.runtime.*;

/**
 * @class Use to detect the lexer and parser errors
 *        And throw a new RuntimeException
 */
public class PLErrorListener extends BaseErrorListener {

  public static final PLErrorListener INSTANCE = new PLErrorListener();

  @Override
  public void syntaxError (Recognizer<?, ?> recognizer, Object offendingSmbl,
                           int line, int charPositionInLine, String msg,
                           RecognitionException e) throws RuntimeException {

    throw new RuntimeException("Line: " + line + " Column:"
                               + charPositionInLine + " " + msg);
  }
}
