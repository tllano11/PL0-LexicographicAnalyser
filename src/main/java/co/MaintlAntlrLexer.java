package co;

import org.antlr.v4.runtime.*;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlAntlrLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @class main antlr4 lexer for PL0
 */
public class MaintlAntlrLexer {
  //Switch for the standar input
  private static boolean standarInput = true;

  //String array with the names of the type of tokens
  private static final String[] types = {"ERROR", "separator", "operator",
                                         "keyword", "identifier", "integer",
                                         "withespace"};

  /**
   * @method Print the token information
   */
  private static void printToken(GenericToken t) {
    System.out.println("tipo: " + types[t.getType()] +
                       " valor: " + '"' + t.getLex() + '"' +
                       " fila: " + t.getLine() +
                       " col: " + t.getCol());
  }

  /**
   * @method list all the token in the stream
   * @param Generic lexer of the stream
   */
  private static void list (GenericLexer lexer)
    throws TokenException, IOException, RecognitionException {

    //Genric token, actual token in the stream
    GenericToken t = lexer.getToken();

    //While the lexer have more tokens do
    while (t.getType() != -1) { //-1 = EOF
      switch (t.getType()) {
      case 4: //4 = ID
        //Check if the ID has more than 32 characters
        if (t.getLex().length() > 32) {
          //If true, throw a TokenException
          throw new TokenException(t);
        } else {
          //If not, print the token
          printToken(t);
        }
        break;

      case 5: //5 = Int
        //Make sure the Int is < 2^31
        if (Long.parseLong(t.getLex()) > 2147483648L) {
          //If true, throw a TokenException
          throw new TokenException(t);
        } else {
          //If not, print the token
          printToken(t);
        }
        break;

      default:
        //Print the token
        printToken(t);
        break;
      }

      //Get the next token in the stream
      t = lexer.getToken();
    }
  }

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
  private static void lexer (String list[]) {
    //Declaration of attributes used for the lexer process
    ANTLRInputStream afs = null;
    InputStream is = null;
    Reader r = null;
    GenericLexer lexer = null;

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

        //Initialization of attributes used for the lexer process
        r = new InputStreamReader(is, "utf-8");
        afs = new ANTLRInputStream(r);
        lexer = new GenericLexer(new tlAntlrLexer(afs));
        list(lexer);
      } catch (FileNotFoundException ex) {
        //Error if the file name is incorrect
        System.err.println("The File: " + arg + " was not found");
        //Continue with the next file
        continue;
      } catch (RecognitionException ex) {
        //Error in the lexer, continue with the next file
        continue;
      } catch (TokenException tok) {
        //Error with a token
        System.err.println(tok.getMessage());
        //Continue with the next file
        continue;
      } catch (IOException ioex) {
        //Error with the IO operations
        System.err.println("Unexpected error: " + ioex.getMessage());
        //Exit the program
        System.exit(1);
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
      lexer(temp);
    } else {
      //Else call the parser with the arguments provide by the user
      lexer(args);
    }
  }
}
