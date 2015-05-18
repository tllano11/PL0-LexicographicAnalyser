package co;

import org.antlr.v4.runtime.*;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlAntlrLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class MaintlAntlrLexer {

    private static boolean standarInput = true;
    private static final String[] types = {"ERROR", "separator", "operator",
                                           "keyword", "identifier", "integer",
                                           "withespace"};

    private static void printToken(GenericToken t) {
        System.out.println("tipo: " + types[t.getType()] +
                           " valor: " + '"' + t.getLex() + '"' +
                           " fila: " + t.getLine() +
                           " col: " + t.getCol());
    }

    private static void list (GenericLexer lexer) throws TokenException,
                              IOException, RecognitionException {

        GenericToken t = lexer.getToken();

        while(t.getType() != -1) { //-1 = EOF
            switch (t.getType()) {
                case 4:
                    //Check if the ID has more than 32 characters
                    if (t.getLex().length() > 32) {
                       throw new TokenException(t);
                    } else {
                        printToken(t);
                    }
                    break;
                case 5:
                    //Make sure the Int is < 2^31
                    if(Long.parseLong(t.getLex()) > 2147483648L){
                        throw new TokenException(t);
                    } else {
                        printToken(t);
                    }
                    break;
                default:
                    printToken(t);
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
        InputStream is = null;
        Reader r = null;
        GenericLexer lexer = null;
        String myFile = null;
        
        for (String arg : list) {
            myFile = arg;
            
            try {
                if (!arg.equals("-")) {
                    System.out.println("fichero: " + arg);
                    is = new FileInputStream(arg);
                    r = new InputStreamReader(is, "utf-8");
                    afs = new ANTLRInputStream(r);
                } else {
                    if (standarInput) {
                        System.out.println("Ingrese el texto a analizar");
                        r = new InputStreamReader(standarInput(),"utf-8");
                        afs = new ANTLRInputStream(r);
                        System.out.println("fichero: entrada estandar");
                        standarInput = false;
                    } else {
                        System.out.println("Error, only one standar input" +
                                           " is accepted per execution");
                        continue;
                    }
                }

                lexer = new GenericLexer(new tlAntlrLexer(afs));
                list(lexer);
            } catch (FileNotFoundException ex) {
                System.err.println("The File: " + myFile + " was not found");
                continue;
	    } catch (RecognitionException ex) {
		continue;
            } catch (TokenException tok) {
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
