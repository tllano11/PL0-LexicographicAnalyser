package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import org.antlr.v4.runtime.Token;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens.JFToken;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * @class Generic token for the lexer
 *        Used to control both, Antlr and JFlex token
 */
public class GenericToken {

  //Parameters of the token

  //Type
  private int type;
  //SEPARATORS=1
  //OPERATORS=2
  //KEYWORDS=3
  //ID=4
  //INT=5
  //WHITESPACE=6
  //EOF = -1
  //BADTOKEN = 7
  //BADID = 8

  private int line;
  private int col;
  private String lex;

  private Token antlrToken;
  private JFToken jFlexToken;

  //RE that have all the ASCCI (Printable) characters
  private String rex = "\\p{ASCII}";

  /**
   * @method Constructor for antlr4
   * @param antlr Token
   */
  public GenericToken(Token antlrToken) {
    this.antlrToken = antlrToken;
    this.jFlexToken = null;
    this.type = antlrToken.getType();
    this.line = antlrToken.getLine();
    this.col = antlrToken.getCharPositionInLine();
    this.lex = antlrToken.getText();
  }

  /**
   * @method Constructor for jflex
   * @param JFToken
   */
  public GenericToken(JFToken jFlexToken) {
    this.antlrToken = null;
    this.jFlexToken = jFlexToken;
    this.type = jFlexToken.getType();
    this.line = jFlexToken.getLine();
    this.col = jFlexToken.getColumn();
    this.lex = jFlexToken.getText();
  }

  /**
   * @method get type of the token
   */
  public int getType() {
    return type;
  }

  /**
   * @method get line of the token
   */
  public int getLine() {
    return line;
  }

  /**
   * @method get colum of the token
   */
  public int getCol() {
    return col;
  }

  /**
   * @method get the printable lexema of the token
   */
  public String getLex() {
    //Initilice the variables used to transform the string
    Pattern p = Pattern.compile(rex);
    Matcher m;
    String printableLex = "";
    String ch;
    int ascii;

    //For each character in lix
    for (char c : lex.toCharArray()) {
      ch = Character.toString(c);
      m = p.matcher(ch);

      //Check if the character is printable
      if (m.matches()) {
        //If it's printable; it's concatenated to printableLex
        printableLex += ch;
      } else {
        //If not; it's transformed to it's numerical value
        ascii = (int) c;
        printableLex += String.valueOf(ascii);
      }
    }

    return printableLex;
  }
}
