package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import java.lang.*;

public class TokenException extends Exception {
    
    public TokenException (GenericToken tok) {
        super ("Error: " + tok.getLex() + " fila: " + tok.getLine() + " col: "
               + tok.getCol());
    }
}
