package co;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.parser.*;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.PLErrorListener;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @class Main Parser Antlr4 for PL0
 */
public class MaintlAntlrParser {

  //Switch for the standar input
  private static boolean standarInput = true;

  /**
   * @method Get the standar input from the user terminal
   * @return InputStream with all the data from the standar input
   */
  private static InputStream standarInput() {
    String tmp = "";
    Scanner s = new Scanner(System.in);

    //While the user haven't press EOF
    while (s.hasNext()) {
      //Continue reading from terminal, and store the data in tmp
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
      //For every arg in  list...

      try {
        if (!arg.equals("-")) {
          //If arg isn't "-"; is a file name
          System.out.println("*** File: " + arg + " ***");
          //Get the stream of the file
          is = new FileInputStream(arg);
        } else {
          //Else is the signal for an standar input

          if (standarInput) {
            //If it's the first time, start the standar input mode
            System.out.println("Ingrese el texto a analizar");
            is = standarInput();
            System.out.println("*** File: entrada estandar ***");
            standarInput = false;
          } else {
            //Else printe the error, and continue the for
            System.out.println("*** Fail: Only one standar input"
                               + " is accepted per execution ***");
            continue;
          }
        }

        //Initialization of attributes used for the parsing process
        //Lexer Actions
        r = new InputStreamReader(is, "utf-8");
        afs = new ANTLRInputStream(r);
        lexer = new tlAntlrParserLexer(afs);
        lexer.removeErrorListeners();
        lexer.addErrorListener(PLErrorListener.INSTANCE);

        //Parser Actions
        tokens = new CommonTokenStream(lexer);
        parser = new tlAntlrParserParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(PLErrorListener.INSTANCE);
        tree = parser.program();

        //Eval the program if the parser don't fail
        dec = new tlAntlrParserIDeclVarsVisitor();
        use = new tlAntlrParserIUsedVarsVisitor();
        System.out.println("*** OK ***");
        dec.visit(tree);
        use.visit(tree);
      } catch (FileNotFoundException ex) {
        //Error if the file name is incorrect
        System.err.println("*** Fail: The File: " + arg
                           + " was not found ***");
        //Continue with the next file
        continue;
      } catch (IOException ioex) {
        //Error with the IO operations
        System.err.println("*** Fail: Unexpected error: " +
                           ioex.getMessage() + " ***");
        //Exit the program
        System.exit(1);
      } catch (RuntimeException re) {
        //Error in the lexer or in the parser
        System.err.println("*** Fail: " + re.getMessage() + " ***");
        //Contine with the next file
        continue;
      }
    }
  }

  /**
   * @method main, all the code logic run here
   */
  public static void main (String args[]) {
    if (args.length == 0) {
      //If there is no arguments, call the parser in the SI Mode
      String temp[] = {"-"};
      parser(temp);
    } else {
      //Else call the parser with the arguments provide by the user
      parser(args);
    }
  }
}
