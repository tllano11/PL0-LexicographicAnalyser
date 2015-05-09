package co;

import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlJFlexLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.GenericLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.Parser;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.ParserException;
import java.io.*;

public class MaintlJFlexLexer {

    public static void main (String args[]) {
        if (args.length != 1) {
            System.err.println("Error: Uso MainJFlexLexer Files");
            System.exit(1);
        }

	File file = null;
        InputStream afs = null;
        GenericLexer lexer = null;
	Parser parser = new Parser();

	for (int i = 0; i <  args.length; i++) {
            try {
		file = new File(args[i]);
                afs = new FileInputStream(file);
                lexer = new GenericLexer(new tlJFlexLexer(afs));
                parser.analyze(lexer);
            } catch (IOException | ParserException ex) {
                System.err.println("Error: " + ex.getMessage());
                System.exit(1);
	    }
	} 
    }
}
