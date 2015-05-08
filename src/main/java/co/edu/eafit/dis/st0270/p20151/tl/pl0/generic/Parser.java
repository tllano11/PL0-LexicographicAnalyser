package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.GenericLexer;
//import ParserException; 

public class Parser {
   
    private int state;
    private GenericToken token;
    private GenericLexer lexer;
    
    public Parser(){}
    
    private void reset(){
        state = 1;
        token = lexer.getToken();
    }
    
    public void analyze(GenericLexer lexer) { //throws ParseException {
        this.lexer = lexer;
        reset();
        
        boolean control = false;
        while(!control){
            switch(state){
                case 0:
                    //throw new ParseException(token);
                    break;
                case 1:
                    break;

            }
        }
        
    }
}
