package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlAntlrLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.lexer.tlJFlexLexer;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.GenericToken;
import java.io.IOException;

public class GenericLexer {
    
    private int type;
    private tlAntlrLexer antlrLexer;
    private tlJFlexLexer jFlexLexer;
    
    public GenericLexer (tlAntlrLexer antlrLexer) {
        this.antlrLexer = antlrLexer;
        this.jFlexLexer = null;
        this.type = 1;
    }
    
    public GenericLexer (tlJFlexLexer jFlexLexer) {
        this.antlrLexer = null;
        this.jFlexLexer = jFlexLexer;
        this.type = 2;
    }
    
    public GenericToken getToken() throws IOException {
        if (type == 1) return new GenericToken(antlrLexer.nextToken());

        if (type == 2) {
            try {
                return new GenericToken(jFlexLexer.getNextToken());
            } catch (NullPointerException nul) {
                return new GenericToken(antlrLexer.emitEOF());
            }
        }
        
        return null;
    }

    public GenericToken getEOF() throws IOException {
        return new GenericToken(antlrLexer.emitEOF());
    }
}
