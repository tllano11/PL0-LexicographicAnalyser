package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import org.antlr.v4.runtime.*;

/**
 * @class Used to throw a new RuntimeException when lexer and parser errors
 *        are detected.
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
