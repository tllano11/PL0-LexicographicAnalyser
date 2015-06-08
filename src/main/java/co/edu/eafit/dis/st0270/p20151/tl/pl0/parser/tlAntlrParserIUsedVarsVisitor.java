package co.edu.eafit.dis.st0270.p20151.tl.pl0.parser;

import java.util.*;
import org.antlr.v4.runtime.tree.TerminalNode;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.parser.tlAntlrParserParser.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * @class Visitor used to find the used variables
 *        For a parser of the PL0 programming language
 */
public class tlAntlrParserIUsedVarsVisitor
    extends tlAntlrParserBaseVisitor<Void> {

    //Global variable to store the program's rule context
    private tlAntlrParserParser.PBlockContext grandFather;

    //Name of the current procedure
    private String procName = "";

    //List of const's sets that exist at a specific procedure
    private List<HashSet<String>> constsList = new LinkedList<>();
    
    //List of variable's sets that exist at a specefic procedure
    private List<HashSet<String>> varsList = new LinkedList<>();

    //Set of the used Variables at a specific procedure
    private HashSet<String> usedVars = new HashSet<>();

    //RE that have all the ASCCI (Printable) characters
    private String rex = "\\p{ASCII}";  
    
    /**
     * @method visitor for the program rule
     * @param context at actual grammar rule
     */
    public Void  visitPBlock (tlAntlrParserParser.PBlockContext ctx) {
        System.out.println("*** Used Vars ***");

        //Save the program's context
        grandFather = ctx;

        //Visit the block rule
        visit(ctx.block());

        //print the usedVariables from main
        printVars();
        
        return null;
    }

    /**
     * @method visitor for the block rule
     * @param context at actual grammar rule
     */
    public Void visitBDefBlock (tlAntlrParserParser.BDefBlockContext ctx) {
        //Visit the defconst rule (if exist)
        if (ctx.defconst() != null) visit(ctx.defconst());
        //If not, an empty set is added to the constsList
        else constsList.add(new HashSet<String>());
        
        //Visit the defvar rule (if exist)
        if (ctx.defvar() != null) visit(ctx.defvar());
        //If not, an empty set is added to the varsList
        else varsList.add(new HashSet<String>());

        //Visit each procedure in the actual context
        for (DefprocContext proc: ctx.defproc()) visit(proc);

        //Visit the instruction rule
        visit(ctx.instruction());

        return null;
    }

    /**
     * @method visitor for the defconst rule
     * @param context at actual grammar rule
     */
    public Void visitDcConst (tlAntlrParserParser.DcConstContext ctx) {
        //Set of constants at this context
        HashSet<String> consts = new HashSet<String>();

        //Add to consts each constant in the context
        for (TerminalNode node : ctx.ID()) consts.add(node.getText());

        //Add to constsList the actual set of constants
        constsList.add(consts);
        
        return null;
    }

    /**
     * @method visitor for the defvar rule
     * @param context at actual grammar rule
     */
    public Void visitDvVar (tlAntlrParserParser.DvVarContext ctx) {
        //Set of variabless at this context
        HashSet<String> vars = new HashSet<String>();

        //Add to vars each variable in the context
        for (TerminalNode node : ctx.ID()) vars.add(node.getText());

        //Add to varsList the actual set of variables
        varsList.add(vars);
        return null;
    }

    /**
     * @method visitor for the defproc rule
     * @param context at actual grammar rule
     */
    public Void visitDpProc (tlAntlrParserParser.DpProcContext ctx) {
        //The name of the current procedure
        procName = ctx.ID().getText();

        //Visit the block rule
        visit(ctx.block());

        //Vars last set is removed from varList
        varsList.remove(varsList.size() - 1);

        //Consts last set is removed from constsList
        constsList.remove(constsList.size() - 1);

        //Print the used variables in the current context
        printVars();
        return null;
    }

    /**
     * @method visitor for the instruction rule (iID)
     * @param context at actual grammar rule
     */
    public Void visitIID (tlAntlrParserParser.IIDContext ctx) {
        //Add this ID to the usedVars
        addVar(ctx.ID().getText());

        //Visit the expr rule
        visit(ctx.expr());
        
        return null;
    }

    /**
     * @method visitor for the instruction rule (iBegin)
     * @param context at actual grammar rule
     */
    public Void visitIBegin (tlAntlrParserParser.IBeginContext ctx) {
        if (ctx.getParent().getParent().equals(grandFather)) {
            //If the father of the fathe of the actual context
            //Is the program rule; we are in the Main

            procName = "main";
        }

        //Visit each instruction rule in the current context
        for (InstructionContext ins : ctx.instruction()) visit(ins); 
        
        return null;
    }

    /**
     * @method visitor for the instruction rule (iIf)
     * @param context at actual grammar rule
     */
    public Void visitIIf(tlAntlrParserParser.IIfContext ctx) {
        //Visit the condition rule
        visit(ctx.condition());

        //Visit the instruction rule
        visit(ctx.instruction());
        return null;
    }

    /**
     * @method visitor for the instruction rule (iWhile)
     * @param context at actual grammar rule
     */
    public Void vsitIWhile (tlAntlrParserParser.IWhileContext ctx) {
        //Visit the condition rule
        visit(ctx.condition());

        //Visit the instruction rule
        visit(ctx.instruction());
        
        return null;
    }

    /**
     * @method visitor for the condition rule (cOdd)
     * @param context at actual grammar rule
     */
    public Void visitCOdd (tlAntlrParserParser.COddContext ctx) {
        //Visit the expr rule
        visit(ctx.expr());

        return null;
    }

    /**
     * @method visitor for the condition rule (cComp)
     * @param context at actual grammar rule
     */
    public Void visitCComp (tlAntlrParserParser.CCompContext ctx) {
        //0 COMPARISON 1
        
        //Visit the expr0 rule
        visit(ctx.expr(0));

        //Visit the expr1 rule
        visit(ctx.expr(1));
        
        return null;
    }

    /**
     * @method visitor for the expr rule
     * @param context at actual grammar rule
     */
    public Void visitEExpr (tlAntlrParserParser.EExprContext ctx) {
        //Visit each term rule in the current context
        for (TermContext term : ctx.term()) visit(term);
        
        return null;
    }

    /**
     * @method visitor for the term rule
     * @param context at actual grammar rule
     */
    public Void visitTTerm (tlAntlrParserParser.TTermContext ctx) {
        //Visit each factor rule in the current context
        for (FactorContext factor : ctx.factor()) visit(factor);
        
        return null;
    }

    /**
     * @method visitor for the factor rule (fID)
     * @param context at actual grammar rule
     */
    public Void visitFID (tlAntlrParserParser.FIDContext ctx) {
        //Add this ID to the usedVars
        addVar(ctx.ID().getText());
            
        return null;
    }

    /**
     * @method visitor for the factor rule (fExpr)
     * @param context at actual grammar rule
     */
    public Void visitFExpr (tlAntlrParserParser.FExprContext ctx) {
        //Visit the expr rule
        visit(ctx.expr());
        
        return null;
    }

    /**
     * @method Transform a string into its pritable form
     * @param lex String to transform
     */ 
    public String toPrintable(String lex) {
        //Initilice the variables used to transform the string
        Pattern p = Pattern.compile(rex);
        Matcher m;
        String printableLex = "";
        String ch;
        int ascii;

        //For each character in lix
        for (char c : lex.toCharArray()) {
            ch = Character.toString(c);
            m = p.matcher(ch);

            //Check if the character is printable
            if (m.matches()) {
                //If it's printable; it's concatenated to printableLex
                printableLex += ch;
            } else {
                //If not; it's transformed to it's numerical value
                ascii = (int) c;
                printableLex += String.valueOf(ascii);
            }
        }
        
        return printableLex;
    }

    /**
     * @method check if the var is valid and adds it to usedVars
     * @param var name of the var to add
     */
    public void addVar (String id) {
        //Switch for stop this function
        boolean swt = false;
        
        //It's used to know if id is a constant
        boolean isconst = false; 
        
        for (int i = varsList.size() - 1; i >= 0 && !swt; i--) {
            //For each nesting level
            
            if (varsList.get(i).contains(id)) {
                //If id was defined as variable
                //In the current nesting level
                //Look if id isn't defined as const
                
                for (int j = i; j < constsList.size() && !isconst; j++) {
                    //For each following nesting level

                    if (constsList.get(j).contains(id)) {
                        //If id is defined as a const

                        //End funtion
                        isconst = true;
                        swt = true;
                    }
                }

                if (!isconst) {
                    //If id isn't defined as a const
                    //Add to the usedVars set
                    usedVars.add(id);

                    //End funtion
                    swt = true;
                }
            }
        }
    }

    /**
     * @method print the usedVars
     */
    public void printVars () {
        //Print
        System.out.println("*** " + procName + " ***\n\rusedVars: "
                           + toPrintable(usedVars.toString()) + ".");

        //Clear the usedVars Set
        usedVars.clear();
    }
}

