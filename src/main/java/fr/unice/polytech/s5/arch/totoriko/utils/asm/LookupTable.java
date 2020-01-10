package fr.unice.polytech.s5.arch.totoriko.utils.asm;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


/**
 * Utility class mimicking a Lookup Table.
 */
public class LookupTable {

    // opcodes for instructions with an immediate.
    public static Map<String, Integer> immInstructions = new HashMap<>();

    // opcodes for instructions register to register.
    public static Map<String, Integer> regInstructions = new HashMap<>();

    // opcodes for hybrid instructions.
    public static Map<String, Integer> mixInstructions = new HashMap<>();
    public static Map<String, Integer> registers = new HashMap<>();


    /**
     * must be called before translating any instructions.
     */
    public static void fillTables() {

        immInstructions.put("lsl", 0b0);
        immInstructions.put("lsr", 0b1_00000_000_000);
        immInstructions.put("asr", 0b10_00000_000_000);
        immInstructions.put("add", 0b11_1_0_000_000_000);
        immInstructions.put("sub", 0b11_1_1_000_000_000);
        immInstructions.put("mov", 0b1_00_000_00000000);
        immInstructions.put("rsb", 0b10000_1001_000_000);
        immInstructions.put("str", 0b1001_0_000_00000000);
        immInstructions.put("ldr", 0b1001_1_000_00000000);

        regInstructions.put("add", 0b11_0_0_000_000_000);
        regInstructions.put("sub", 0b11_0_1_000_000_000);
        regInstructions.put("and", 0b10000_0000_000_000);
        regInstructions.put("eor", 0b10000_0001_000_000);
        regInstructions.put("lsl", 0b10000_0010_000_000);
        regInstructions.put("lsr", 0b10000_0011_000_000);
        regInstructions.put("asr", 0b10000_0100_000_000);
        regInstructions.put("adc", 0b10000_0101_000_000);
        regInstructions.put("sbc", 0b10000_0110_000_000);
        regInstructions.put("ror", 0b10000_0111_000_000);
        regInstructions.put("tst", 0b10000_1000_000_000);
        regInstructions.put("cmp", 0b10000_1010_000_000);
        regInstructions.put("cmn", 0b10000_1011_000_000);
        regInstructions.put("orr", 0b10000_1100_000_000);
        regInstructions.put("mul", 0b10000_1101_000_000);
        regInstructions.put("bic", 0b10000_1110_000_000);
        regInstructions.put("mvn", 0b10000_1111_000_000);

        mixInstructions.put("add", 0b1011_0000_0_0000000);
        mixInstructions.put("sub", 0b1011_0000_1_0000000);
        mixInstructions.put("b", 0b1101_0000_00000000);
        mixInstructions.put("eq", 0b0000);
        mixInstructions.put("ne", 0b0001);
        mixInstructions.put("cs", 0b0010);
        mixInstructions.put("cc", 0b0011);
        mixInstructions.put("mi", 0b0100);
        mixInstructions.put("pl", 0b0101);
        mixInstructions.put("vs", 0b0110);
        mixInstructions.put("vc", 0b0111);
        mixInstructions.put("hi", 0b1000);
        mixInstructions.put("ls", 0b1001);
        mixInstructions.put("ge", 0b1010);
        mixInstructions.put("lt", 0b1011);
        mixInstructions.put("gt", 0b1100);
        mixInstructions.put("le", 0b1101);
        mixInstructions.put("al", 0b1101);

        IntStream.range(0, 8)
                 .forEach( i -> registers.put("r" + i, i));

    }
}
