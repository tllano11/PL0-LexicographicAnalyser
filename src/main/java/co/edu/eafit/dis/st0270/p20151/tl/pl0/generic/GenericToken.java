package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import org.antlr.v4.runtime.Token;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens.JFToken;

public class GenericToken {
    
    private int type;
    private int tokenType;
    private int line;
    private int col;
    private String lexema;
    private Token antlrToken; 
    private JFToken jFlexToken;
    
    public GenericToken(Token antlrToken){
        this.antlrToken = antlrToken;
        this.jFlexToken = null;
        this.type = 1;
    }
    
    public GenericToken(JFToken jFlexToken){
        this.antlrToken = null;
        this.jFlexToken = jFlexToken;
        this.type = 2;
    }
        
    public int getType(){
        if(type == 1) return antlrToken.getType();
        if(type == 2) return jFlexToken.getType();
        return null;
    }
    
    public int getLine(){
        if(type == 1) return antlrToken.getLine();
        if(type == 2) return jFlexToken.getLine();
        return null;
    }
    
    public int getCol(){
        if(type == 1) return antlrToken.getCharPositionInLine();
        if(type == 2) return jFlexToken.getColumn();
        return null;
    }
    
    public int getLexema(){
        if(type == 1) return antlrToken.getText();
        if(type == 2) return jFlexToken.getText();
        return null;
    }
    
}
