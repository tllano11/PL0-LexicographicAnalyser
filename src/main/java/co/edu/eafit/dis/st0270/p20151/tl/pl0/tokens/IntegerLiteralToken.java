package co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens;

public class IntegerLiteralToken
    extends LiteralToken {

    private Integer value;

    public IntegerLiteralToken(String literal, int linea, int column) {
        super(linea, column);
        this.value = new Integer(literal);
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "Integer: " + value + " " + super.toString();
    }
}
