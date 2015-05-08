package co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens;

public abstract class Token {

        private int line;
        private int column;

        public Token(int line, int column) {
                this.line   = line + 1;
                this.column = column;
        }

        public void setLine(int line) {
                this.line = line;
        }

        public void setColumn(int column) {
                this.column = column;
        }

        public int getLine() {
                return line;
        }

        public int getColumn() {
                return column;
        }

        public String toString() {
                return "linea: " + line + " columna: " + column;
        }
}
