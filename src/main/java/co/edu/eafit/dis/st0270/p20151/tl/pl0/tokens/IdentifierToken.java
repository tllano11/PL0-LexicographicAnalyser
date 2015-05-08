package co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens;

public class IdentifierToken 
    extends Token {

    private String id;

    public IdentifierToken(String id, int line, int column) {
        super(line,column);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        String str = super.toString();
        return "Identifier: " + id + " " + str;
    }
}
