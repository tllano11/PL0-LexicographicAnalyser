package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import org.antlr.v4.runtime.*;

public class VerboseListener extends BaseErrorListener {

    public static final VerboseListener INSTANCE = new VerboseListener();
    
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e){
           
        System.err.println("Error: " + getUnknownChar(msg) +
                           " fila: " + line +
                           " col: " + charPositionInLine);

        throw e;
    }

    public String getUnknownChar(String msg) {
        return msg.substring(msg.indexOf("'")+1, msg.length()-1);
    }
}
