package co.edu.eafit.dis.st0270.p20151.tl.pl0.parser;

import java.util.*;
import org.antlr.v4.runtime.tree.TerminalNode;
import co.edu.eafit.dis.st0270.p20151.tl.pl0.parser.tlAntlrParserParser.*;

/**
 * @class Visitor used to find the declared variables
 *        For a parser of the PL0 programming language
 */
public class tlAntlrParserIDeclVarsVisitor
    extends tlAntlrParserBaseVisitor<Void> {

    //Global variable to store the program's rule context
    tlAntlrParserParser.PBlockContext grandFather;

    //Name of the current procedure
    String procName = "";
    
    //Set of the global variables of the current file
    HashSet<String> gvars = new HashSet<String>();

    //List of variable's sets that exist at a specefic procedure
    List<HashSet<String>> varsList = new LinkedList<>();
    
    /**
     * @method visitor for the program rule
     * @param context at actual grammar rule
     */
    public Void visitPBlock (tlAntlrParserParser.PBlockContext ctx) {
        System.out.println("*** Decl Vars ***");
        grandFather = ctx; //Save the program's context
        visit(ctx.block()); //Visit the block rule

        return null;
    }

    /**
     * @method visitor for the block rule
     * @param context at actual grammar rule
     */
    public Void visitBDefBlock (tlAntlrParserParser.BDefBlockContext ctx) {
        //Visit the defvar rule
        visit(ctx.defvar());

        //Visit each procedure in the actual context
        for (DefprocContext proc: ctx.defproc()) visit(proc);

        //Visit the instruction rule
        visit(ctx.instruction());

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

        //Print the variables
        if (ctx.getParent().getParent().equals(grandFather)) {
            //If the father of the fathe of the actual context
            //Is the program rule; these are the global variables
            
            gvars = vars;
            System.out.println("*** global ***\n"
                               + "vars: " + gvars.toString() + "."
                               + "\n\r hidings: [].");
        } else {
            //Else we are in a procedure

            //Print the actual variables
            System.out.println("*** "+ procName + " ***");
            System.out.println("vars: " + vars.toString() + ".");

            //Set to store the hidings
            HashSet<String> hds = new HashSet<String>();

            //For each set of variables of before procedures
            for (HashSet<String>  set : varsList) {
                //And for each var in the actual procedure
                for (String var : vars) {
                    //If the var was defined before, is hiding
                    if (set.contains(var)) hds.add(var);
                }
            }

            //Print the hiding variables
            System.out.println("hidings: " + hds.toString() + ".");
        }

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

        //Remove in varsList the last set of vars
        varsList.remove(varsList.size() - 1);

        return null;
    }

    /**
     * @method visitor for the instruction rule (iBegin)
     * @param context at actual grammar rules
     */
    public Void visitIBegin (tlAntlrParserParser.IBeginContext ctx) {
        if (ctx.getParent().getParent().equals(grandFather)) {
            //If the father of the fathe of the actual context
            //Is the program rule; we are in the Main
            
            System.out.println("*** main ***");
            System.out.println("vars: [].");
            System.out.println("hidings: [].");
        }

        return null;
    }
}
