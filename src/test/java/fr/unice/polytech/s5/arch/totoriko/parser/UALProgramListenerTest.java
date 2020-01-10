package fr.unice.polytech.s5.arch.totoriko.parser;

import fr.unice.polytech.s5.arch.totoriko.UALLexer;
import fr.unice.polytech.s5.arch.totoriko.UALParser;
import fr.unice.polytech.s5.arch.totoriko.utils.asm.LookupTable;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UALProgramListenerTest {

    private UALProgramListener listener = new UALProgramListener();
    private UALLexer lexer;
    private CommonTokenStream tokens;
    private UALParser parser;
    private ParseTree tree;
    private ParseTreeWalker walker;

    @BeforeAll
    static void setup() {
        LookupTable.fillTables();
    }

    private List<Integer> parseString(String s) {

        CharStream stream = CharStreams.fromString(s);

        lexer = new UALLexer(stream);
        tokens = new CommonTokenStream(lexer);
        parser = new UALParser(tokens);

        UALLblListener lblOnlyListener = new UALLblListener();

        walker = new ParseTreeWalker();
        tree = parser.program();

        walker.walk(lblOnlyListener, tree);
        listener.setLabelsMemoryAddress(lblOnlyListener.getLabelsMemoryAddress());



        walker.walk(listener, tree);

        return listener.getOp();
    }

    @Test
    void testInstructionsOpcodesWithZeroOrEmptyParameters() {

        assertEquals(0b0, LookupTable.immInstructions.get("lsl"));
        assertEquals(0b1_00000_000_000, LookupTable.immInstructions.get("lsr"));
        assertEquals(0b10_00000_000_000, LookupTable.immInstructions.get("asr"));
        assertEquals(0b11_0_0_000_000_000, LookupTable.regInstructions.get("add"));
        assertEquals(0b11_0_1_000_000_000, LookupTable.regInstructions.get("sub"));
        assertEquals(0b11_1_0_000_000_000, LookupTable.immInstructions.get("add"));
        assertEquals(0b11_1_1_000_000_000, LookupTable.immInstructions.get("sub"));
        assertEquals(0b1_00_000_00000000, LookupTable.immInstructions.get("mov"));
        assertEquals(0b10000_0000_000_000, LookupTable.regInstructions.get("and"));
        assertEquals(0b10000_0001_000_000, LookupTable.regInstructions.get("eor"));
        assertEquals(0b10000_0010_000_000, LookupTable.regInstructions.get("lsl"));
        assertEquals(0b10000_0011_000_000, LookupTable.regInstructions.get("lsr"));
        assertEquals(0b10000_0100_000_000, LookupTable.regInstructions.get("asr"));
        assertEquals(0b10000_0101_000_000, LookupTable.regInstructions.get("adc"));
        assertEquals(0b10000_0110_000_000, LookupTable.regInstructions.get("sbc"));
        assertEquals(0b10000_0111_000_000, LookupTable.regInstructions.get("ror"));
        assertEquals(0b10000_1000_000_000, LookupTable.regInstructions.get("tst"));
        assertEquals(0b10000_1001_000_000, LookupTable.immInstructions.get("rsb"));
        assertEquals(0b10000_1010_000_000, LookupTable.regInstructions.get("cmp"));
        assertEquals(0b10000_1011_000_000, LookupTable.regInstructions.get("cmn"));
        assertEquals(0b10000_1100_000_000, LookupTable.regInstructions.get("orr"));
        assertEquals(0b10000_1101_000_000, LookupTable.regInstructions.get("mul"));
        assertEquals(0b10000_1110_000_000, LookupTable.regInstructions.get("bic"));
        assertEquals(0b10000_1111_000_000, LookupTable.regInstructions.get("mvn"));
        assertEquals(0b1001_0_000_00000000, LookupTable.immInstructions.get("str"));
        assertEquals(0b1001_1_000_00000000, LookupTable.immInstructions.get("ldr"));
        assertEquals(0b1011_0000_0_0000000, LookupTable.mixInstructions.get("add"));
        assertEquals(0b1011_0000_1_0000000, LookupTable.mixInstructions.get("sub"));
        assertEquals(0b1101_0000_00000000, LookupTable.mixInstructions.get("b"));
        assertEquals(0b0, LookupTable.mixInstructions.get("eq"));
        assertEquals(0b1, LookupTable.mixInstructions.get("ne"));
        assertEquals(0b10, LookupTable.mixInstructions.get("cs"));
        assertEquals(0b11, LookupTable.mixInstructions.get("cc"));
        assertEquals(0b100, LookupTable.mixInstructions.get("mi"));
        assertEquals(0b101, LookupTable.mixInstructions.get("pl"));
        assertEquals(0b110, LookupTable.mixInstructions.get("vs"));
        assertEquals(0b111, LookupTable.mixInstructions.get("vc"));
        assertEquals(0b1000, LookupTable.mixInstructions.get("hi"));
        assertEquals(0b1001, LookupTable.mixInstructions.get("ls"));
        assertEquals(0b1010, LookupTable.mixInstructions.get("ge"));
        assertEquals(0b1011, LookupTable.mixInstructions.get("lt"));
        assertEquals(0b1100, LookupTable.mixInstructions.get("gt"));
        assertEquals(0b1101, LookupTable.mixInstructions.get("le"));
        assertEquals(0b1110, LookupTable.mixInstructions.get("al"));
    }

    @Test
    void testBranchingBInstructionUsesALFConditionByDefault() {

        String op = ".main:\nb .main";

        assertEquals(0b1101_1110_00000000, parseString(op).get(0));
    }

    @Test
    void testSmallProgramIsCorrectlyTranslated() {

        String op = "\tsub\tsp, #24\n"
                    + "\tmovs\tr0, #0\n"
                    + "\tstr\tr0, [sp, #20]\n"
                    + "\tstr\tr0, [sp, #16]\n"
                    + "\tstr\tr0, [sp, #12]\n"
                    + "\tmovs\tr0, #1\n"
                    + "\tstr\tr0, [sp, #8]\n"
                    + "\tmovs\tr0, #2\n"
                    + "\tstr\tr0, [sp, #4]\n"
                    + "\tmovs\tr0, #3\n"
                    + "\tstr\tr0, [sp]";

        Integer[] expected = new Integer[]{0xb098, 0x2000, 0x9014, 0x9010, 0x900c, 0x2001, 0x9008, 0x2002, 0x9004, 0x2003, 0x9000};

        assertEquals(Arrays.asList(expected), parseString(op));
    }
}