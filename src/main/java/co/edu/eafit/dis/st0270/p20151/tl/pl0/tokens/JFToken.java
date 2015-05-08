package co.edu.eafit.dis.st0270.p20151.tl.pl0.tokens;

public class JFToken {

    private int line;
    private int column;
    private int type;
    private String text;


    public JFToken(String text, int line, int column, int type) {
	this.line   = line + 1;
	this.column = column;
	this.text = text;
	this.type = type;
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

    public String getText(){
	return text;
    }

    public int getType(){
	return type;
    }
}
