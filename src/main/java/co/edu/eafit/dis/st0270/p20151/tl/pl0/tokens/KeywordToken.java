package co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens;

public class KeywordToken
        extends Token {

        public enum EnumKeyword {
                CONST,
                VAR,
                PROCEDURE,
                CALL,
                BEGIN,
                END,
                IF,
                THEN,
                WHILE,
                DO,
                ODD,
                MAIN;

                static public EnumKeyword getKeywordOrdinal(String name) throws Error
                {
                        if (name.compareTo("const") == 0) return CONST;
                        if (name.compareTo("var") == 0) return VAR;
                        if (name.compareTo("procedure") == 0) return PROCEDURE;
                        if (name.compareTo("main") == 0) return MAIN;
                        if (name.compareTo("call") == 0) return CALL;
                        if (name.compareTo("begin") == 0) return BEGIN;
                        if (name.compareTo("end") == 0) return END;
                        if (name.compareTo("if") == 0) return IF;
                        if (name.compareTo("then") == 0) return THEN;
                        if (name.compareTo("while") == 0) return WHILE;
                        if (name.compareTo("do") == 0) return DO;
                        if (name.compareTo("odd") == 0) return ODD;
                        throw new Error("Unknown keyword ordinal");
                }

        }

        private String keyword = null;

        public KeywordToken(String keyword, int line, int column) {
                super(line,column);
                this.keyword = new String(keyword);
        }

        public String toString() {
                return "keyword: " + this.keyword + " " + super.toString();
        }

        public EnumKeyword getEnumKeyword()
                throws Error
        {
                return EnumKeyword.getKeywordOrdinal(keyword);
        }
}
