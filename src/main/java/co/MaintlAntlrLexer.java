package co;

import org.antlr.v4.runtime.*;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlAntlrLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class MaintlAntlrLexer {

    private static final String[] types = {"ERROR", "SEPARATORS", "OPERATORS", "KEYWORD", "ID", "INT", "WITHESPACE", "EOF"};

    private static void list (GenericLexer lexer) throws TokenException {
        GenericToken t = null;
        
        try {
           t = lexer.getToken();

            while(t.getType() != -1) { //-1 = EOF
                switch (t.getType()) {
                    case 6:
                        //If is a white space, ignore
                        break;
                    default:
                        System.out.println("tipo: " + types[t.getType()] +
                                           " valor: " + '"' + t.getLex() + '"' +
                                           " fila: " + t.getLine() +
                                           " col: " + t.getCol());
                        break;
                }
                
                t = lexer.getToken();
            } 
        } catch (Exception ex) { //AGREGAR LA EXECPCIÓN ESPECIFICA DE ANTLR4
            throw new TokenException(t);
        }
    }

    private static InputStream standarInput() {
        String tmp = "";
        Scanner s = new Scanner(System.in);
        
        while (s.hasNext()) {
            tmp += s.nextLine();
            tmp += "\n\r";
        }

        return new ByteArrayInputStream(tmp.getBytes(Charset.forName("UTF-8")));
    }
    
    public static void main (String args[]) {
        if (args.length < 1) {
            System.err.println("Error: Uso MaintlAntlerLexer Files");
            System.exit(1);
        }

        ANTLRInputStream afs = null;
        GenericLexer lexer = null;
        //Parser parser = new Parser();

        for (int i = 0; i <  args.length; i++) {
            try {
                if (!args[i].equals("-")) {
                    System.out.println("fichero: " + args[i]);
                    afs = new ANTLRFileStream(args[i]);
                } else {
                    System.out.println("Digite texto a analizar");
                    afs = new ANTLRInputStream(standarInput());
                    System.out.println("fichero: entrada estandar");
                }
                
                lexer = new GenericLexer(new tlAntlrLexer(afs));
                list(lexer);
                System.out.println("-------------------------------\n\r");
                //parser.analyze(lexer)
            } catch (IOException ex) {
                System.err.println("Un expected exceptio: " + ex.getMessage());
                System.exit(1);
            } catch (TokenException tok) {
                System.err.println(tok.getMessage());
                continue;
            }
        } 
    }
}
