package co;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.parser.*;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.ParserErrorListener;
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

        //Declaration of attributes used for the parsing process
        InputStream is = null;
        Reader r = null;
        ANTLRInputStream afs = null;
        tlAntlrParserLexer lexer = null;
        CommonTokenStream tokens = null;
        tlAntlrParserParser parser = null;
        ParseTree tree = null;
        tlAntlrParserIDeclVarsVisitor dec = null;
        tlAntlrParserIUsedVarsVisitor use = null;

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

                //Initialization of attributes used for the parsing process
                //Lexer Actions
                r = new InputStreamReader(is, "utf-8");
                afs = new ANTLRInputStream(r);
                lexer = new tlAntlrParserLexer(afs);
                lexer.removeErrorListeners();
                lexer.addErrorListener(ParserErrorListener.INSTANCE);


                //Parser Actions
                tokens = new CommonTokenStream(lexer);
                parser = new tlAntlrParserParser(tokens);
                parser.removeErrorListeners();
                parser.addErrorListener(ParserErrorListener.INSTANCE);
                tree = parser.program();

                //Eval the program if the parser don't fail
                dec = new tlAntlrParserIDeclVarsVisitor();
                use = new tlAntlrParserIUsedVarsVisitor();
                System.out.println("*** OK ***");
                dec.visit(tree);
                use.visit(tree);
            } catch (FileNotFoundException ex) {
                System.err.println("*** Fail: The File: " + arg
                                   + " was not found ***");
                continue;
            } catch (IOException ioex) {
                System.err.println("*** Fail: Unexpected error: " +
                                   ioex.getMessage() + " ***");
                System.exit(1);
            } catch (ParseCancellationException pce){
                System.err.println(pce.getMessage());
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
