package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class ParserErrorListener extends BaseErrorListener {

   public static final ParserErrorListener INSTANCE = new ParserErrorListener();

   @Override
   public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
   	       		   int line, int charPositionInLine,
			   String msg, RecognitionException e)

      throws ParseCancellationException {
         throw new ParseCancellationException("*** Fail: Line: " + line + " Column:" +
	       	   			       charPositionInLine + " " + msg + " ***");
      }
}
