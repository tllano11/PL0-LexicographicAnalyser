package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import java.lang.*;

public class BadINTException extends Exception {
    
    public BadINTException (GenericToken tok) {
        super ("Error INT: " + tok.getLex() + " fila: " + tok.getLine() + " col: " + tok.getCol());
    }
}
