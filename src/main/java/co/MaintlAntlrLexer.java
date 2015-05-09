package co;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.Token;
import java.io.IOException;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlAntlrLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.GenericLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.GenericToken;

public class MaintlAntlrLexer {
    
    public static void main (String args[]) {
	if (args.length != 1) {
	    System.err.println("Error: Uso JayAntlrMain File");
	    System.exit(1);
	}

	ANTLRFileStream afs = null;
        GenericToken t;
	GenericLexer lexer;

	try {
	    afs = new ANTLRFileStream(args[0]);
	    lexer = new GenericLexer(new tlAntlrLexer(afs));
	    t  = lexer.getToken();
	} catch (IOException ioe) {
	    System.err.println("Error: " + ioe);
	    System.exit(1);
	}


	//Token eof = lexer.emitEOF();

	while(t.getType() != 1) { //eof.getType()) {
	    switch(t.getType()) {
	        case tlAntlrLexer.WHITESPACE:
		    break;
	        default:
		    System.out.println("Token " + t.getLex() +
				       " fila: " + t.getLine() +
				       " columna: " + t.getCol() +
				       " tipo: " + t.getType());
		    break;
	    }
	    t = lexer.getToken();
	}
    }
}
