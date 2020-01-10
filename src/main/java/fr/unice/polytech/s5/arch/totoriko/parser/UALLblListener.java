package fr.unice.polytech.s5.arch.totoriko.parser;

import fr.unice.polytech.s5.arch.totoriko.UALListener;
import fr.unice.polytech.s5.arch.totoriko.UALParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.Map;

/**
 * UAL's AST tree walker which only read labels.
 * To each label is mapped its line minus empty lines, which gives us the location of the label in the memory.
 */
public class UALLblListener implements UALListener {

    /**
     * Each label is given a line - empty lines.
     */
    private Map<String, Integer> labelsMemoryAddress;

    public UALLblListener() {
        labelsMemoryAddress = new HashMap<>();
    }

    @Override
    public void enterProgram(UALParser.ProgramContext ctx) {

    }

    @Override
    public void exitProgram(UALParser.ProgramContext ctx) {

    }

    @Override
    public void enterPound(UALParser.PoundContext ctx) {

    }

    @Override
    public void exitPound(UALParser.PoundContext ctx) {

    }

    @Override
    public void enterComma(UALParser.CommaContext ctx) {

    }

    @Override
    public void exitComma(UALParser.CommaContext ctx) {

    }

    @Override
    public void enterImm(UALParser.ImmContext ctx) {

    }

    @Override
    public void exitImm(UALParser.ImmContext ctx) {

    }

    @Override
    public void enterLbracket(UALParser.LbracketContext ctx) {

    }

    @Override
    public void exitLbracket(UALParser.LbracketContext ctx) {

    }

    @Override
    public void enterRbracket(UALParser.RbracketContext ctx) {

    }

    @Override
    public void exitRbracket(UALParser.RbracketContext ctx) {

    }

    @Override
    public void enterLbl(UALParser.LblContext ctx) {

    }

    @Override
    public void exitLbl(UALParser.LblContext ctx) {

        int line = ctx.getStart().getLine();
        String name = ctx.label().getText();

        // Memory location starts at 0
        labelsMemoryAddress.put(name, line - 1);
    }

    @Override
    public void enterLabel(UALParser.LabelContext ctx) {

    }

    @Override
    public void exitLabel(UALParser.LabelContext ctx) {

    }

    @Override
    public void enterName(UALParser.NameContext ctx) {

    }

    @Override
    public void exitName(UALParser.NameContext ctx) {

    }

    @Override
    public void enterComment(UALParser.CommentContext ctx) {

    }

    @Override
    public void exitComment(UALParser.CommentContext ctx) {

    }

    @Override
    public void enterRegister(UALParser.RegisterContext ctx) {

    }

    @Override
    public void exitRegister(UALParser.RegisterContext ctx) {

    }

    @Override
    public void enterLsl(UALParser.LslContext ctx) {

    }

    @Override
    public void exitLsl(UALParser.LslContext ctx) {

    }

    @Override
    public void enterLsr(UALParser.LsrContext ctx) {

    }

    @Override
    public void exitLsr(UALParser.LsrContext ctx) {

    }

    @Override
    public void enterAsr(UALParser.AsrContext ctx) {

    }

    @Override
    public void exitAsr(UALParser.AsrContext ctx) {

    }

    @Override
    public void enterAdd(UALParser.AddContext ctx) {

    }

    @Override
    public void exitAdd(UALParser.AddContext ctx) {

    }

    @Override
    public void enterSub(UALParser.SubContext ctx) {

    }

    @Override
    public void exitSub(UALParser.SubContext ctx) {

    }

    @Override
    public void enterMov(UALParser.MovContext ctx) {

    }

    @Override
    public void exitMov(UALParser.MovContext ctx) {

    }

    @Override
    public void enterAnd(UALParser.AndContext ctx) {

    }

    @Override
    public void exitAnd(UALParser.AndContext ctx) {

    }

    @Override
    public void enterEor(UALParser.EorContext ctx) {

    }

    @Override
    public void exitEor(UALParser.EorContext ctx) {

    }

    @Override
    public void enterAdc(UALParser.AdcContext ctx) {

    }

    @Override
    public void exitAdc(UALParser.AdcContext ctx) {

    }

    @Override
    public void enterSbc(UALParser.SbcContext ctx) {

    }

    @Override
    public void exitSbc(UALParser.SbcContext ctx) {

    }

    @Override
    public void enterRor(UALParser.RorContext ctx) {

    }

    @Override
    public void exitRor(UALParser.RorContext ctx) {

    }

    @Override
    public void enterTst(UALParser.TstContext ctx) {

    }

    @Override
    public void exitTst(UALParser.TstContext ctx) {

    }

    @Override
    public void enterRsb(UALParser.RsbContext ctx) {

    }

    @Override
    public void exitRsb(UALParser.RsbContext ctx) {

    }

    @Override
    public void enterCmp(UALParser.CmpContext ctx) {

    }

    @Override
    public void exitCmp(UALParser.CmpContext ctx) {

    }

    @Override
    public void enterCmn(UALParser.CmnContext ctx) {

    }

    @Override
    public void exitCmn(UALParser.CmnContext ctx) {

    }

    @Override
    public void enterOrr(UALParser.OrrContext ctx) {

    }

    @Override
    public void exitOrr(UALParser.OrrContext ctx) {

    }

    @Override
    public void enterMul(UALParser.MulContext ctx) {

    }

    @Override
    public void exitMul(UALParser.MulContext ctx) {

    }

    @Override
    public void enterBic(UALParser.BicContext ctx) {

    }

    @Override
    public void exitBic(UALParser.BicContext ctx) {

    }

    @Override
    public void enterMvn(UALParser.MvnContext ctx) {

    }

    @Override
    public void exitMvn(UALParser.MvnContext ctx) {

    }

    @Override
    public void enterStr(UALParser.StrContext ctx) {

    }

    @Override
    public void exitStr(UALParser.StrContext ctx) {

    }

    @Override
    public void enterLdr(UALParser.LdrContext ctx) {

    }

    @Override
    public void exitLdr(UALParser.LdrContext ctx) {

    }

    @Override
    public void enterB(UALParser.BContext ctx) {

    }

    @Override
    public void exitB(UALParser.BContext ctx) {

    }

    @Override
    public void enterInstruction(UALParser.InstructionContext ctx) {

    }

    @Override
    public void exitInstruction(UALParser.InstructionContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }

    Map<String, Integer> getLabelsMemoryAddress() {
        return labelsMemoryAddress;
    }
}
