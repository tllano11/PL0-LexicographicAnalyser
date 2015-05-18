package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import org.antlr.v4.runtime.Token;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens.JFToken;

public class GenericToken {
    
    private int type;
    private int line;
    private int col;
    private String lex;
    private Token antlrToken; 
    private JFToken jFlexToken;
    
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
        return lex;
    }
}
