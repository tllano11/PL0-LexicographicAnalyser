package co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens;

/**
 * @class JFlex token for the lexer
 */
public class JFToken {

  private int type;
  //Token type
  //SEPARATORS=1
  //OPERATORS=2
  //KEYWORDS=3
  //ID=4
  //INT=5
  //WHITESPACE=6
  //EOF = -1
  //BADTOKEN = 7
  //BADID = 8

  //Token atributes
  private int line;
  private int column;
  private String text;


  /**
   * @method constructor
   * @param text Lexema of the token
   * @param line line of the token
   * @param column column of the token
   * @param type identifier of the token
   */
  public JFToken(String text, int line, int column, int type) {
    this.line   = line + 1;
    this.column = column;
    this.text = text;
    this.type = type;
  }

  //Getters and setters of the class
  public void setLine(int line) {
    this.line = line;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public String getText(){
    return text;
  }

  public int getType(){
    return type;
  }
  //------------------------------------------------------------------------
  /**
   * @method Get the information of the token in one string
   */
  public String toString() {
    return "linea: " + line + " columna: " + column + " lex: " + text;
  }
}
