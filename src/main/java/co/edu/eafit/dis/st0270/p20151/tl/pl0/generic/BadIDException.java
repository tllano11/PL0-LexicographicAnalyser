package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import java.lang.*;

public class BadIDException extends Exception {
    
    public BadIDException (GenericToken tok) {
        super ("Error ID: " + tok.getLex() + " fila: " + tok.getLine() + " col: " + tok.getCol());
    }
}
