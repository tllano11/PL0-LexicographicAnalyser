package co;

import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlJFlexLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.*;
import java.util.Scanner;
import java.io.*;
import java.nio.charset.Charset;

public class MaintlJFlexLexer {
    private static final String[] types = {"ERROR", "SEPARATORS", "OPERATORS", "KEYWORD", "ID", "INT", "WITHESPACE", "EOF"};

    private static void list (GenericLexer lexer) throws TokenException {
        GenericToken t = null;

        try {
            t = lexer.getToken();
            
            while (t.getType() != -1) { //-1 = EOF
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
        } catch (IOException ex) {
            throw new TokenException(t);
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

    public static void main (String args[]) {
        if (args.length < 1) {
            System.err.println("Error: Uso MainJFlexLexer Files");
            System.exit(1);
        }

        File file = null;
        InputStream afs = null;
        GenericLexer lexer = null;
        //Parser parser = new Parser();

        for (int i = 0; i <  args.length; i++) {
            try {
                if (!args[i].equals("-")) {
                    System.out.println("fichero: " + args[i]);
                    file = new File(args[i]);
                    afs = new FileInputStream(file);
                } else {
                    System.out.println("Ingrese el texto a analizar");
                    afs = standarInput();
                    System.out.println("fichero: entrada estandar");
                }

                lexer = new GenericLexer(new tlJFlexLexer(afs));
                list(lexer);
                System.out.println("--------------------------------------- \n\r");
                //parser.analyze(lexer);
            } catch (FileNotFoundException ex) {
                System.err.println("The File: " + args[i] + " was not found");
                System.exit(1);
            } catch (TokenException tok) {
                System.err.println(tok.getMessage());
                continue;
            }
        } 
    }
}
