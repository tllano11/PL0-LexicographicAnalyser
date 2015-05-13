package co;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.Token;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlAntlrLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.GenericLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.Parser;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.ParserException;
import java.io.IOException;

public class MaintlAntlrLexer {

    private void list (GenericLexer lexer) {
        try {
            Generic Token t = lexer.nextToken();
            Token eof = lexer.getEOF();

            while(t.getType() != eof.getType()) {
                switch (t.getType()) {
                    case 6:
                        //If is a white space, ignore
                        break;
                    default:
                        System.out.println("tipo: " + t.getType() +
                                           " valor: " + t.getLex() +
                                           " fila: " + t.getLine() +
                                           " col: " + t.getCol);
                        break;
                }
                
                t = lexer.nextToken();
        } catch (IOException ex) {
                System.err.println("Error: " + ex.getMessage());       
        }
    }
    
    public static void main (String args[]) {
        if (args.length != 1) {
            System.err.println("Error: Uso MaintlAntlerLexer Files");
            System.exit(1);
        }

        ANTLRFileStream afs = null;
        GenericLexer lexer = null;
        //Parser parser = new Parser();

        for (int i = 0; i <  args.length; i++) {
            try {
                afs = new ANTLRFileStream(args[i]);
                lexer = new GenericLexer(new tlAntlrLexer(afs));
                list(lexer);
                //parser.analyze(lexer)
            } catch (IOException ex) {
                System.err.println("Error: " + ex.getMessage());
                System.exit(1);
            }
        } 
    }
}
