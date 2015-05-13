package co;

import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlJFlexLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.*;
import java.io.*;

public class MaintlJFlexLexer {
        private static void list (GenericLexer lexer) {
        try {
            GenericToken t = lexer.getToken();
            GenericToken eof = lexer.getEOF();

            while(t.getType() != eof.getType()) {
                switch (t.getType()) {
                    case 6:
                        //If is a white space, ignore
                        break;
                    default:
                        System.out.println("tipo: " + t.getType() +
                                           " valor: " + t.getLex() +
                                           " fila: " + t.getLine() +
                                           " col: " + t.getCol());
                        break;
                }
                
                t = lexer.getToken();
            }
        } catch (IOException ex) {
                System.err.println("Error: " + ex.getMessage());       
        }
}

    public static void main (String args[]) {
        if (args.length != 1) {
            System.err.println("Error: Uso MainJFlexLexer Files");
            System.exit(1);
        }

        File file = null;
        InputStream afs = null;
        GenericLexer lexer = null;
        //Parser parser = new Parser();

        for (int i = 0; i <  args.length; i++) {
            try {
                file = new File(args[i]);
                afs = new FileInputStream(file);
                lexer = new GenericLexer(new tlJFlexLexer(afs));
                list(lexer);
                //parser.analyze(lexer);
            } catch (IOException ex) {
                System.err.println("Error: " + ex.getMessage());
                System.exit(1);
            }
        } 
    }
}
