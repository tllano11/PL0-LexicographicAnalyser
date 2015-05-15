package co;

import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlJFlexLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.*;
import java.util.Scanner;
import java.io.*;
import java.nio.charset.Charset;

public class MaintlJFlexLexer {
    private static final String[] types = {"ERROR", "SEPARATORS", "OPERATORS", "KEYWORD", "ID", "INT", "WITHESPACE", "EOF"};

    private static void printToken(GenericToken t) {
        System.out.println("tipo: " + types[t.getType()] +
                           " valor: " + '"' + t.getLex() + '"' +
                           " fila: " + t.getLine() +
                           " col: " + t.getCol());
    }

    private static void list (GenericLexer lexer) throws TokenException, BadIDException, BadINTException, IOException {
        GenericToken t = lexer.getToken();
            
        while (t.getType() != -1) { //-1 = EOF
            switch (t.getType()) {
                case 5:
                    if (Long.parseLong(t.getLex()) > 2147483648L) {
                        throw new BadINTException(t);
                    } else {
                        printToken(t);
                    }
                    break;
                case 6:
                    //If is a white space, ignore
                    break;
                case 7:
                    throw new TokenException(t);
                case 8:
                    throw new BadIDException(t);
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
        
        while (s.hasNextLine()) {
            tmp += s.nextLine();
            tmp += "\n\r";
        }

        return new ByteArrayInputStream(tmp.getBytes(Charset.forName("UTF-8")));
    }

    private static void lexer (String list[]) {
        File file = null;
        InputStream afs = null;
        GenericLexer lexer = null;
        String myFile = null;
        
        for (String arg: list) {
            myFile = arg;
            
            try {
                if (!arg.equals("-")) {
                    System.out.println("fichero: " + arg);
                    file = new File(arg);
                    afs = new FileInputStream(file);
                } else {
                    System.out.println("Ingrese el texto a analizar");
                    afs = standarInput();
                    System.out.println("fichero: entrada estandar");
                }

                lexer = new GenericLexer(new tlJFlexLexer(afs));
                list(lexer);
                System.out.println("--------------------------------------- \n\r");
            } catch (FileNotFoundException ex) {
                System.err.println("The File: " + myFile + " was not found");
                continue;
            } catch (TokenException | BadIDException | BadINTException tok) {
                System.err.println(tok.getMessage());
                continue;
            } catch (IOException ioex) {
                System.err.println("Unexpected error: " + ioex.getMessage());
            }
        }
    }

    public static void main (String args[]) {
        if (args.length == 0) {
            String tmp[] = {"-"};
            lexer(tmp);
        } else {
            lexer(args);
        } 
    }
}
