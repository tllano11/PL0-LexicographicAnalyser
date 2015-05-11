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
        if (!token.getLex.equals(EOF)) throw new ParserException(token);
    }

    private void block () throws ParserException {
        defConst();
        defVar();
        defProc();
        instruction();
    }

    private void defConst () throws ParserException {
        if (token.getLex().equals("const")) {
            token = lexer.getToken();
            boolean var = false;
            
            while (!var) {
                if (token.getType == 4) {
                    token = lexer.getToken();
                    if (!token.getLex().equals(":")) throw new ParserException(token);

                    token = lexer.getToken();
                    if (token.getType != 5) throw new ParserException(token);

                    token = lexer.getToken();
                    if (token.getLex().equals(";")) var = true;
                    else if (!token.getLex().equals(",")) throw new ParserException(token);
                    else token = lexer.getToken();
                } else {
                    throw new ParserException(token); 
                }
            }
        }
    }

    private void defVar() throws ParserException {
        if (token.getLex().equals("var")) {
            token = lexer.getToken();
            boolean var = false;

            while (!var) {
                if (token.getType == 4) {
                    token = lexer.getToken();
                    if (token.getLex().equals(";")) var = true;
                    else if (!token.getLex().equals(",")) throw new ParserException(token);
                    else token = lexer.getToken();
                } else {
                    throw new ParserException(token); 
                }
            }
        }
    }

    private void defProc() throws ParserException {
        if (token.getLex().equals("procedure")) {
            token = lexer.getToken();
            if (token.getType == 4) {
                token = lexer.getToken();
                if (token.getLex().equals(";")) block();
                if (!token.getLex().equals(";")) throw new ParserException(token);
            } else {
                    throw new ParserException(token);
            }
        }
    }

    private void instruction() throws ParserException{
        if (token.getType() == 4){
            token = lexer.getToken();
            if (token.getLex().equals(":=")) {
                token = lexer.getToken();
                expr();
            } else {
                throw new ParserException(token);
            }
        } else if(token.getLex().equals("call")){
            token = lexer.getToken();
            if (token.getType() != 4){
                throw new ParserException(token);
            }
        } else if (token.getLex().equals("begin")){
            token = lexer.getToken();
            instruction();
            token = lexer.getToken();
            while (!token.getLex().equals("end")){
                if (token.getLex().equals(";")){
                    token = lexer.getToken();
                    instruction();
                } else {
                    throw new ParserException(token);
                }
            }       
        } else if(token.getLex().equals("if")){
            token = lexer.getToken();
            condition();
            token = lexer.getToken();
            if (token.getLex().equals("then")){
                token = lexer.getToken();
                instruction();
            } else{
                throw new ParserException(token);
            }
        }  else if(token.getLex().equals("while")){
            token = lexer.getToken();
            condition();
            token = lexer.getToken();
            if (token.getLex().equals("do")){
                token = lexer.getToken();
                instruction();
            } else{
                throw new ParserException(token);
            }
        } 
    }

    private void condition() throws ParserException {
        if(token.getLex().equals("odd")){
            token = lexer.getToken();
            expr();
	}
    }

    private void expr() throws ParserException {
        //Here we check if the following tokens form a expression
	//RestExpr(term());
    }    
}
