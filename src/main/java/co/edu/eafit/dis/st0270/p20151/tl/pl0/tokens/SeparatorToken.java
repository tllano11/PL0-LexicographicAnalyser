package co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens;

public class SeparatorToken
    extends Token {

    public enum EnumSeparator {
        LPAREN,
        RPAREN,
        SEMI,
        COMMA,
        POINT;

        static public EnumSeparator getSeparatorOrdinal(String name) throws Error
        {
            switch(name.charAt(0)) {
            case '(' : return LPAREN;
            case ')' : return RPAREN;
            case ';' : return SEMI;
            case ',' : return COMMA;
            case '.' : return POINT;
            }
            throw new Error("Unknown separator ordinal");
        }
    }
    private String separator;

    public SeparatorToken(String separator, int linea, int column) {
        super(linea, column);
        this.separator = new String(separator);
    }

    public String toString() {
        return "Separator: " + separator + " " + super.toString();
    }

    public EnumSeparator getEnumSeparator()
        throws Error
    {
        return EnumSeparator.getSeparatorOrdinal(separator);
    }
}
