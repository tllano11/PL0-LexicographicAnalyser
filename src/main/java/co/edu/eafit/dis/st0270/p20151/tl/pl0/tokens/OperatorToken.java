package co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens;

public class OperatorToken
    extends Token {

    public enum EnumOperator {
        ASSIGN,
        PLUS,
        MINUS,
        TIMES,
        DIVIDE,
        LT,
        LET,
        GT,
        GET,
        EQ,
        NEQ;
    }

    private String operator;
    private EnumOperator eOp;

    public OperatorToken(String operator, int linea, int column) {
        super(linea, column);
        this.operator = new String(operator);
        this.eOp = getEOP();
    }

    private EnumOperator getEOP() {

        if (operator.compareTo(":=") == 0)
            return EnumOperator.ASSIGN;
        if (operator.compareTo("+") == 0)
            return EnumOperator.PLUS;
        if (operator.compareTo("-") == 0)
            return EnumOperator.MINUS;
        if (operator.compareTo("*") == 0)
            return EnumOperator.TIMES;
        if (operator.compareTo("/") == 0)
            return EnumOperator.DIVIDE;
        if (operator.compareTo("<") == 0)
            return EnumOperator.LT;
        if (operator.compareTo("<=") == 0)
            return EnumOperator.LET;
        if (operator.compareTo(">") == 0)
            return EnumOperator.GT;
        if (operator.compareTo(">=") == 0)
            return EnumOperator.GET;
        if (operator.compareTo("=") == 0)
            return EnumOperator.EQ;
        if (operator.compareTo("<>") == 0)
            return EnumOperator.NEQ;

        throw new Error("Internal Error: invalid operator");
    }

    public EnumOperator getEnumOperator() {
        return eOp;
    }

    public String toString() {
        return "Operator: " + operator + " " + super.toString();
    }
}
