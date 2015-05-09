package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.GenericLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.ParserException; 
import java.io.IOException;

/** Tokens type:
    SEPARATORS=1
    OPERATORS=2
    KEYWORDS=3
    ID=4
    INT=5
    WHITESPACE=6
 */

public class Parser {
   
    private int state;
    private GenericToken token;
    private GenericLexer lexer;
    private String EOF = ".";
    
    public Parser() {}
    
    private void reset() {
        state = 1;

	try {
            token = lexer.getToken();
        } catch (IOException ioe) {
            System.err.println("Error: " + ioe);
            System.exit(1);
        }
    }
    
    public void analyze (GenericLexer lexer) throws ParserException {
        this.lexer = lexer;
        reset();
	
	try {
	    program();
	} catch (IOException ioe) {
            System.err.println("Error: " + ioe);
            System.exit(1);
        }
        
    }

    private void program () throws ParserException {
	block();
	if (!token.getLex.compare(EOF)) throw new ParserException(token);
    }

    private void block () throws ParserException {
	defConst();
	defVar();
	defProc();
	instruction();
    }

    private void defConst () throws ParserException {
	if (token.getLex().compare("const")) {
	    token = lexer.getToken();
	    boolean var = false;
	    
            while (!var) {
		if (token.getType == 4) {
		    token = lexer.getToken();
		    if (!token.getLex().compare(":")) throw new ParserException(token);

		    token = lexer.getToken();
		    if (token.getType != 5) throw new ParserException(token);

		    token = lexer.getToken();
		    if (token.getLex().compare(";")) var = true;
		    else if (!token.getLex().compare(",")) throw new ParserException(token);
		    else token = lexer.getToken();
		} else {
		    throw new ParserException(token); 
		}
	    }
	}
    }

    private void defVar() throws ParserException {
	if (token.getLex().compare("var")) {
	    token = lexer.getToken();
	    boolean var = false;

	    while (!var) {
		if (token.getType == 4) {
		    token = lexer.getToken();
		    if (token.getLex().compare(";")) var = true;
		    else if (!token.getLex().compare(",")) throw new ParserException(token);
		    else token = lexer.getToken();
		} else {
		    throw new ParserException(token); 
		}
	    }
	}
    }

    private void defProc() throws ParserException {
	if (token.getLex().compare("procedure")) {
	    token = lexer.getToken();
	    if (token.getType == 4) {
	        token = lexer.getToken();
	        if (token.getLex().compare(";")) block();
		if (!token.getLex().compare(";")) throw new ParserException(token);
	    } else {
		    throw new ParserException(token);
	    }
	}
    }

    private void expr() throws ParserException {
	//Here we check if the following tokens form a expression
	 RestExpr(term());
    }    
}
