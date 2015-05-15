package co;

import org.antlr.v4.runtime.*;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlAntlrLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class MaintlAntlrLexer {

    private static final String[] types = {"ERROR", "SEPARATORS", "OPERATORS", "KEYWORD", "ID", "INT", "WITHESPACE", "EOF"};

    private static void list (GenericLexer lexer) throws TokenException, BadIDException, IOException {
        GenericToken t = lexer.getToken();

        while(t.getType() != -1) { //-1 = EOF
            switch (t.getType()) {
            case 6:
                //If is a white space, ignore
                break;
            case 7:
                throw new TokenException(t);
            case 8:
                throw new BadIDException(t);
            default:
                System.out.println("tipo: " + types[t.getType()] +
                                   " valor: " + '"' + t.getLex() + '"' +
                                   " fila: " + t.getLine() +
                                   " col: " + t.getCol());
                break;
            }
                
            t = lexer.getToken();
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

    private static void lexer (String list[]) {
        ANTLRInputStream afs = null;
        GenericLexer lexer = null;
        String myFile = null;
        
        for (String arg : list) {
            myFile = arg;
            
            try {
                if (!arg.equals("-")) {
                    System.out.println("fichero: " + arg);
                    afs = new ANTLRFileStream(arg);
                } else {
                    System.out.println("Ingrese el texto a analizar");
                    afs = new ANTLRInputStream(standarInput());
                    System.out.println("fichero: entrada estandar");
                }

                lexer = new GenericLexer(new tlAntlrLexer(afs));
                list(lexer);
                System.out.println("--------------------------------------- \n\r");
            } catch (FileNotFoundException ex) {
                System.err.println("The File: " + myFile + " was not found");
                continue;
            } catch (TokenException | BadIDException tok) {
                System.err.println(tok.getMessage());
                continue;
            } catch (IOException ioex) {
                System.err.println("Unexpected error: " + ioex.getMessage());
                System.exit(1);
            }
        }
    }
    
    public static void main (String args[]) {
        if (args.length == 0) {
            String tmp [] = {"-"};
            lexer(tmp);
        } else {
            lexer(args);
        }
    }
}
