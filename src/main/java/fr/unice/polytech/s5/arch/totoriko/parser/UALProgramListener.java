package fr.unice.polytech.s5.arch.totoriko.parser;

import fr.unice.polytech.s5.arch.totoriko.UALParser;
import fr.unice.polytech.s5.arch.totoriko.utils.asm.LookupTable;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UALProgramListener implements fr.unice.polytech.s5.arch.totoriko.UALListener {

    private List<Integer> op;
    private Map<String, Integer> labelsMemoryAddress;

    public UALProgramListener() {
        op = new ArrayList<>(100);
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

        boolean regToReg = ctx.imm() == null;

        int code = regToReg ? LookupTable.regInstructions.get("lsl")
                            : LookupTable.immInstructions.get("lsl");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        if ( !regToReg )
            code += Integer.parseInt( ctx.imm().getText().substring(1) ) << 6;

        op.add(code);
    }

    @Override
    public void enterLsr(UALParser.LsrContext ctx) {

    }

    @Override
    public void exitLsr(UALParser.LsrContext ctx) {

        boolean regToReg = ctx.imm() == null;

        int code = regToReg ? LookupTable.regInstructions.get("lsr")
                            : LookupTable.immInstructions.get("lsr");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        if ( !regToReg )
            code += Integer.parseInt( ctx.imm().getText().substring(1) ) << 6;

        op.add(code);
    }

    @Override
    public void enterAsr(UALParser.AsrContext ctx) {

    }

    @Override
    public void exitAsr(UALParser.AsrContext ctx) {

        boolean regToReg = ctx.imm() == null;

        int code = regToReg ? LookupTable.regInstructions.get("asr")
                            : LookupTable.immInstructions.get("asr");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        if ( !regToReg )
            code += Integer.parseInt( ctx.imm().getText().substring(1) ) << 6;

        op.add(code);
    }

    @Override
    public void enterAdd(UALParser.AddContext ctx) {

    }

    @Override
    public void exitAdd(UALParser.AddContext ctx) {

        boolean regToReg = ctx.register().size() == 3;
        boolean sp = ctx.register(0).getText().toLowerCase().equals("sp");

        int code = regToReg ? LookupTable.regInstructions.get("add")
                            : ( sp ? LookupTable.mixInstructions.get("add") :LookupTable.immInstructions.get("add"));


        if (regToReg || (ctx.register().size() == 2 && !sp ) ) {
            for (int i = 0 ; i < ctx.register().size() ; i++)
                code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "") << (3 * i);

            if (ctx.register().size() == 2)
                code += Integer.parseInt( ctx.imm().getText().substring(1) ) << 6;
        }

        if ( sp )
            code += Integer.parseInt( ctx.imm().getText().substring(1) ) << 6;

        op.add(code);
    }

    @Override
    public void enterSub(UALParser.SubContext ctx) {
    }

    @Override
    public void exitSub(UALParser.SubContext ctx) {

        boolean regToReg = ctx.register().size() == 3;
        boolean sp = ctx.register(0).getText().toLowerCase().equals("sp");

        int code = regToReg ? LookupTable.regInstructions.get("sub")
                            : (sp ? LookupTable.mixInstructions.get("sub") : LookupTable.immInstructions.get("sub"));


        if (regToReg || ( ctx.register().size() == 2 && !sp ) ) {
            for (int i = 0 ; i < ctx.register().size() ; i++)
                code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "") << (3 * i);

            if (ctx.register().size() == 2)
                code += Integer.parseInt( ctx.imm().getText().substring(1) ) << 6;
        }



        if ( sp )
            code += Integer.parseInt( ctx.imm().getText().substring(1) ) << 6;

        op.add(code);
    }

    @Override
    public void enterMov(UALParser.MovContext ctx) {

    }

    @Override
    public void exitMov(UALParser.MovContext ctx) {

        if (ctx.register().size() > 1) {
            System.err.println("mov (register) not implemented. Skipping.");
            return;
        }

        int code = LookupTable.immInstructions.get("mov");

        code += Integer.parseInt( ctx.imm().getText().substring(1) );
        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "") << 8;

        op.add(code);
    }

    @Override
    public void enterAnd(UALParser.AndContext ctx) {

    }

    @Override
    public void exitAnd(UALParser.AndContext ctx) {

        int code = LookupTable.regInstructions.get("and");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterEor(UALParser.EorContext ctx) {

    }

    @Override
    public void exitEor(UALParser.EorContext ctx) {

        int code = LookupTable.regInstructions.get("eor");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterAdc(UALParser.AdcContext ctx) {

    }

    @Override
    public void exitAdc(UALParser.AdcContext ctx) {

        int code = LookupTable.regInstructions.get("adc");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterSbc(UALParser.SbcContext ctx) {

    }

    @Override
    public void exitSbc(UALParser.SbcContext ctx) {

        int code = LookupTable.regInstructions.get("sbc");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterRor(UALParser.RorContext ctx) {

    }

    @Override
    public void exitRor(UALParser.RorContext ctx) {

        int code = LookupTable.regInstructions.get("ror");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterTst(UALParser.TstContext ctx) {

    }

    @Override
    public void exitTst(UALParser.TstContext ctx) {

        int code = LookupTable.regInstructions.get("tst");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterRsb(UALParser.RsbContext ctx) {

    }

    @Override
    public void exitRsb(UALParser.RsbContext ctx) {

        int code = LookupTable.regInstructions.get("rsb");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterCmp(UALParser.CmpContext ctx) {

    }

    @Override
    public void exitCmp(UALParser.CmpContext ctx) {

        int code = LookupTable.regInstructions.get("cmp");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterCmn(UALParser.CmnContext ctx) {

    }

    @Override
    public void exitCmn(UALParser.CmnContext ctx) {

        int code = LookupTable.regInstructions.get("cmn");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterOrr(UALParser.OrrContext ctx) {

    }

    @Override
    public void exitOrr(UALParser.OrrContext ctx) {

        int code = LookupTable.regInstructions.get("orr");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterMul(UALParser.MulContext ctx) {

    }

    @Override
    public void exitMul(UALParser.MulContext ctx) {

        int code = LookupTable.regInstructions.get("mul");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterBic(UALParser.BicContext ctx) {

    }

    @Override
    public void exitBic(UALParser.BicContext ctx) {

        int code = LookupTable.regInstructions.get("bic");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterMvn(UALParser.MvnContext ctx) {

    }

    @Override
    public void exitMvn(UALParser.MvnContext ctx) {

        int code = LookupTable.regInstructions.get("mvn");

        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "");
        code += Integer.parseInt(ctx.register(1).getText().charAt(1) + "") << 3;

        op.add(code);
    }

    @Override
    public void enterStr(UALParser.StrContext ctx) {

    }

    @Override
    public void exitStr(UALParser.StrContext ctx) {

        int code = LookupTable.immInstructions.get("str");

        code += Integer.parseInt( ctx.imm().getText().substring(1) );
        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "") << 8;

        op.add(code);
    }

    @Override
    public void enterLdr(UALParser.LdrContext ctx) {

    }

    @Override
    public void exitLdr(UALParser.LdrContext ctx) {

        int code = LookupTable.immInstructions.get("ldr");

        code += Integer.parseInt( ctx.imm().getText().substring(1) );
        code += Integer.parseInt(ctx.register(0).getText().charAt(1) + "") << 8;

        op.add(code);
    }

    @Override
    public void enterB(UALParser.BContext ctx) {

    }

    @Override
    public void exitB(UALParser.BContext ctx) {

        int code = LookupTable.mixInstructions.get("b");
        int ccode = ctx.BC().getText().length() > 1 ? LookupTable.mixInstructions.get(ctx.BC().getText().substring(1).toLowerCase())
                                                    : LookupTable.mixInstructions.get("al");

        code += ccode << 8;

        String label = ctx.label().getText();

        if ( ! labelsMemoryAddress.containsKey(label) ) {

            System.err.println("Label " + label + " has never been defined");
            return;
        }

        code += labelsMemoryAddress.get(label);

        op.add(code);
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

    public List<Integer> getOp() {
        return op;
    }

    Map<String, Integer> getLabelsMemoryAddress() {
        return labelsMemoryAddress;
    }

    void setLabelsMemoryAddress(Map<String, Integer> labelsMemoryAddress) {
        this.labelsMemoryAddress = labelsMemoryAddress;
    }
}
