package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.GenericLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.ParserException; 
import java.io.IOException;

public class Parser {
   
    private int state;
    private GenericToken token;
    private GenericLexer lexer;
    
    public Parser(){}
    
    private void reset(){
        state = 1;
        try{
            token = lexer.getToken();
        } catch (IOException ioe) {
            System.err.println("Error: " + ioe);
            System.exit(1);
        }
    }
    
    public void analyze(GenericLexer lexer) throws ParserException {
        this.lexer = lexer;
        reset();
        boolean control = false;
	
	try{
	    while (!control) {
		switch (state) {
		    case 0:
			throw new ParserException(token);
                    case 1:
			token = lexer.getToken();
                        break;
		}
	    }
	} catch (IOException ioe) {
            System.err.println("Error: " + ioe);
            System.exit(1);
        }
        
    }
}
