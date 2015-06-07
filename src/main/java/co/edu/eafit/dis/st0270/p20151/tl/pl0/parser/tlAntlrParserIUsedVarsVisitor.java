package co.edu.eafit.dis.st0270.p20151.tl.pl0.parser;

import java.util.*;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * @class Visitor used to find the used variables
 *        For a parser of the PL0 programming language
 */
public class tlAntlrParserIUsedVarsVisitor extends tlAntlrParserBaseVisitor {

    /**
     * @method visitor for the program rule
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitPBlock (tlAntlrParserParser.
                                        PBlockContext ctx) {
        return null;
    }

    /**
     * @method visitor for the block rule
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitBDefBlock (tlAntlrParserParser.
                                           BDefBlockContext ctx) {
        return null;
    }

    /**
     * @method visitor for the defconst rule
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitDcConst (tlAntlrParserParser.
                                         DcConstContext ctx) {
        return null;
    }

    /**
     * @method visitor for the defvar rule
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitDvVar (tlAntlrParserParser.
                                       DvVarContext ctx) {
        return null;
    }

    /**
     * @method visitor for the defproc rule
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitDpProc (tlAntlrParserParser.
                                        DpProcContext ctx) {
        return null;
    }

    /**
     * @method visitor for the instruction rule (iID)
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitIID (tlAntlrParserParser.
                                     IIDContext ctx) {
        return null;
    }

    /**
     * @method visitor for the instruction rule (iCall)
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitICall (tlAntlrParserParser.
                                       ICallContext ctx) {
        return null;
    }

    /**
     * @method visitor for the instruction rule (iBegin)
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitIBegin (tlAntlrParserParser.
                                        IBeginContext ctx) {
        return null;
    }

    /**
     * @method visitor for the instruction rule (iIf)
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitIIf(tlAntlrParserParser.
                                    IIfContext ctx){
        return null;
    }

    /**
     * @method visitor for the instruction rule (iWhile)
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> vsitIWhile (tlAntlrParserParser.
                                       IWhileContext ctx) {
        return null;
    }

    /**
     * @method visitor for the condition rule (cOdd)
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitCOdd (tlAntlrParserParser.
                                      COddContext ctx) {
        return null;
    }

    /**
     * @method visitor for the condition rule (cComp)
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitCComp (tlAntlrParserParser.
                                       CCompContext ctx) {
        return null;
    }

    /**
     * @method visitor for the expr rule
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitEExpr (tlAntlrParserParser.
                                       EExprContext ctx) {
        return null;
    }

    /**
     * @method visitor for the term rule
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitTTerm (tlAntlrParserParser.
                                       TTermContext ctx) {
        return null;
    }

    /**
     * @method visitor for the factor rule (fID)
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitFID (tlAntlrParserParser.
                                     FIDContext ctx) {
        return null;
    }

    /**
     * @method visitor for the factor rule (fInt)
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitFInt (tlAntlrParserParser.
                                      FIntContext ctx) {
        return null;
    }

    /**
     * @method visitor for the factor rule (fExpr)
     * @param context at actual grammar rule
     * @return set of used variables
     */
    public HashSet<String> visitFExpr (tlAntlrParserParser.
                                       FExprContext ctx) {
        return null;
    }
}

