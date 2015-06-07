package co;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.parser.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class MaintlAntlrParser { 

private static boolean standarInput = true; //Switch for the standar input

/**
 * @method Get the standar input from the user terminal
 */
private static InputStream standarInput() {
        String tmp = "";
        Scanner s = new Scanner(System.in);
        
        while (s.hasNext()) {
            tmp += s.nextLine();
            tmp += "\n\r";
        }

        return new ByteArrayInputStream(tmp.getBytes(Charset.forName("UTF-8")));
}

/**
 * @method All th code logic of the parser run here
 * @param list a list of files to analize
 */
private static void parser (String list[]) {
        InputStream is = null;

        for (String arg : list) {
            try {
                if (!arg.equals("-")) {
                    System.out.println("*** File: " + arg + " ***");
                    is = new FileInputStream(arg);
                } else {
                    if (standarInput) {
                        System.out.println("Ingrese el texto a analizar");
                        is = standarInput();
                        System.out.println("*** File: entrada estandar ***");
                        standarInput = false;
                    } else {
                        System.out.println("*** Fail: Only one standar input" +
                                           " is accepted per execution ***");
                        continue;
                    }
                }

                //Initialization of the class used for the parser
                Reader r = new InputStreamReader(is, "utf-8");
                ANTLRInputStream afs = new ANTLRInputStream(r);
                tlAntlrParserLexer lexer = new tlAntlrParserLexer(afs);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                tlAntlrParserParser parser = new tlAntlrParserParser(tokens);
                ParseTree tree = parser.program();
                tlAntlrParserIDeclVarsVisitor dec = new tlAntlrParserIDeclVarsVisitor();
                tlAntlrParserIUsedVarsVisitor use = new tlAntlrParserIUsedVarsVisitor(); 
                
                //Eval the program if the parser don't fail
                System.out.println("*** OK ***");
                dec.visit(tree);
		use.visit(tree);
            } catch (FileNotFoundException ex) {
                System.err.println("*** The File: " + arg + " was not found");
                continue;
            } catch (IOException ioex) {
                System.err.println("Unexpected error: " + ioex.getMessage());
                System.exit(1);
            }
        }
    }
    
    /**
     * @method main, all the code logic run here
     */
    public static void main (String args[]) {
        if (args.length == 0) {
            String tmp [] = {"-"};
            parser(tmp);
        } else {
            parser(args);
        }
    }
}
