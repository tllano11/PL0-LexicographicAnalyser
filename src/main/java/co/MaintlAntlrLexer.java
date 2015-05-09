package co;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.Token;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlAntlrLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.GenericLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.Parser;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.ParserException;
import java.io.IOException;

public class MaintlAntlrLexer {
    
    public static void main (String args[]) {
        if (args.length != 1) {
            System.err.println("Error: Uso MaintlAntlerLexer Files");
            System.exit(1);
        }

        ANTLRFileStream afs = null;
        GenericLexer lexer = null;
	Parser parser = new Parser();

	for (int i = 0; i <  args.length; i++) {
            try {
                afs = new ANTLRFileStream(args[i]);
                lexer = new GenericLexer(new tlAntlrLexer(afs));
                parser.analyze(lexer);
            } catch (IOException | ParserException ex) {
                System.err.println("Error: " + ex.getMessage());
                System.exit(1);
	    }
	} 
    }
}
