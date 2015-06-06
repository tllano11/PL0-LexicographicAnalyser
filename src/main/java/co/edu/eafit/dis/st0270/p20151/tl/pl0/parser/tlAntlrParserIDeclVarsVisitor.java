package co.edu.eafit.dis.st0270.p20151.tl.pl0.parser;
import java.util.HashSet;

/**
 * @class Visitor for find the declared variables
 *        For a parser of the PL0 programming language
 */

class tlAntlrParserIDeclVarsVisitor extends tlAntlrParserBaseVisitor {

    /**
     * @method visitor for the program
     * @param context of the block
     */
    public HashSet<String> visitPBlock (tlAntlrParserParser.
                                        PBlockContext ctx) {
        return null;
    }
    
    public HashSet<String> visitBDefBlock (tlAntlrParserParser.
                                           BDefBlockContext ctx) {
        return null;
    }

    public HashSet<String> visitDcConst (tlAntlrParserParser.
                                         DcConstContext ctx) {
        return null;
    }

    public HashSet<String> visitDvVar (tlAntlrParserParser.
                                       DvVarContext ctx) {
        return null;
    }
        
    public HashSet<String> visitDpProcedure (tlAntlrParserParser.
                                             DpProcedureContext ctx) {
        return null;
    }

    public HashSet<String> visitIID (tlAntlrParserParser.
                                     IIDContext ctx) {
        return null;
    }

    public HashSet<String> visitICall (tlAntlrParserParser.
                                       ICallContext ctx) {
        return null;
    }

    public HashSet<String> visitIBegin (tlAntlrParserParser.
                                        IBeginContext ctx) {
        return null;
    }

    public HashSet<String> visitIIf(tlAntlrParserParser.
                                    IIfContext ctx){
        return null;
    }

    public HashSet<String> vsitIWhile (tlAntlrParserParser.
                                       IWhileContext ctx) {
        return null;
    }

    public HashSet<String> visitIEpsilon (tlAntlrParserParser.
                                          IEpsilonContext ctx) {
        return null;
    }

    public HashSet<String> visitCOdd (tlAntlrParserParser.
                                      COddContext ctx) {
        return null;
    }

    public HashSet<String> visitCExpr (tlAntlrParserParser.
                                       CExprContext ctx) {
        return null;
    }

    public HashSet<String> visitEOperators (tlAntlrParserParser.
                                            EOperatorsContext ctx) {
        return null;
    }

    public HashSet<String> visitTFactor (tlAntlrParserParser.
                                         TFactorContext ctx) {
        return null;
    }

    public HashSet<String> visitFID (tlAntlrParserParser.
                                     FIDContext ctx) {
        return null;
    }

    public HashSet<String> visitFInt (tlAntlrParserParser.
                                      FIntContext ctx) {
        return null;
    }

    public HashSet<String> visitFExpr (tlAntlrParserParser.
                                       FExprContext ctx) {
        return null;
    }
}
