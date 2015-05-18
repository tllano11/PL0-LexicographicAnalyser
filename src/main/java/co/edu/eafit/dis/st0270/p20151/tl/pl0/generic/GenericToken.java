package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import org.antlr.v4.runtime.Token;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens.JFToken;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class GenericToken {
    
    private int type;
    private int line;
    private int col;
    private String lex;
    private Token antlrToken; 
    private JFToken jFlexToken;
    //private String rex = "[\\w().,;:=<>\\+\\-\\*\\/]"; //Regular Expresion
    private String rex = "\\p{ASCII}";    

    public GenericToken(Token antlrToken) {     
        this.antlrToken = antlrToken;
        this.jFlexToken = null;
        this.type = antlrToken.getType();
        this.line = antlrToken.getLine();
        this.col = antlrToken.getCharPositionInLine();
        this.lex = antlrToken.getText();
    }
    
    public GenericToken(JFToken jFlexToken) {
        this.antlrToken = null;
        this.jFlexToken = jFlexToken;
        this.type = jFlexToken.getType();
        this.line = jFlexToken.getLine();
        this.col = jFlexToken.getColumn();
        this.lex = jFlexToken.getText();
    }
        
    public int getType() {
        return type;
    }
    
    public int getLine() {
        return line;
    }
    
    public int getCol() {
        return col;
    }
    
    public String getLex() {
        Pattern p = Pattern.compile(rex);
        Matcher m;
        String printableLex = "";
        String ch;
        int ascii;
        
        for (char c : lex.toCharArray()) {
            ch = Character.toString(c);
            m = p.matcher(ch);
            
            if (m.matches()) {
                printableLex += ch;
            } else {
                ascii = (int) c;
                printableLex += String.valueOf(ascii);
            }
        }
        
        return printableLex;
    }
}
