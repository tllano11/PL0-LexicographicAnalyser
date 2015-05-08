package co;


import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.Token;
import java.io.IOException;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlAntlrLexer;

public class MaintlAntlrLexer{
    public static void main(String args[]){
	if (args.length != 1){
	    System.err.println("Error: Uso JayAntlrMain File");
	    System.exit(1);
	}

	ANTLRFileStream afs = null;

	try {
	    afs = new ANTLRFileStream(args[0]);
	}
	catch (IOException ioe) {
	    System.err.println("Error: " + ioe);
	    System.exit(1);
	}

	tlAntlrLexer lexer = new tlAntlrLexer(afs);

	Token t = lexer.nextToken();
	Token eof = lexer.emitEOF();

	while(t.getType() != eof.getType()) {
	    switch(t.getType()){
	    case tlAntlrLexer.WHITESPACE:
		break;
	    default:
		System.out.println(
				   "Token " + t.getText() +
				   " fila: " + t.getLine() +
				   " columna: " + t.getCharPositionInLine() +
				   " tipo: " + t.getType());
		break;
	    }
	    t = lexer.nextToken();
	}
    }
}
