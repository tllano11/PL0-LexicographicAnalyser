package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import org.antlr.v4.runtime.*;
import java.util.regex.Pattern;

public class VerboseListener extends BaseErrorListener {

  public static final VerboseListener INSTANCE = new VerboseListener();

  @Override
  public void syntaxError (Recognizer<?, ?> recognizer,
                           Object offendingSymbol,
                           int line, int charPositionInLine,
                           String msg,
                           RecognitionException e) {

    System.err.println("Error: " + getUnknownChar(msg) +
                       " fila: " + line +
                       " col: " + charPositionInLine);

    throw e;
  }

  /**
   * @method get the printable unknow char
   */
  private  String getUnknownChar (String msg) {
    int ascii;
    String result = null;
    String badChar =  msg.substring(msg.indexOf("'")+1, msg.length()-1);

    if (Pattern.matches("\\p{ASCII}", badChar)) {
      //If it's an Ascii character is printable
      result = badChar;
    } else {
      //If not, get the numerical representation
      ascii = (int) badChar.charAt(0);
      result = String.valueOf(ascii);
    }

    return result;
  }
}
