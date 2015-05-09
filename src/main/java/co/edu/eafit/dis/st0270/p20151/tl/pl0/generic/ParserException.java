package co.edu.eafit.dis.st0270.p20151.tl.pl0.generic;

import co.edu.eafit.dis.st0270.p20151.tl.pl0.generic.GenericToken;

public class ParserException extends Exception {

    public ParserException (GenericToken t) {
	super ("Unexpected token: " + t.getLex()
	       + " at Line: " + t.getLine()
	       + " at Colum: " + t.getCol());
    }
}
