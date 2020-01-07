package fr.unice.polytech.s5.arch.totoriko;

import fr.unice.polytech.s5.arch.totoriko.parser.UALAssembler;
import fr.unice.polytech.s5.arch.totoriko.utils.io.BinaryFileOutput;
import fr.unice.polytech.s5.arch.totoriko.utils.io.LogisimFileOutput;
import fr.unice.polytech.s5.arch.totoriko.utils.io.UALFileOutput;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UALC {

    private List<String> errors;
    private CommandLine cmd;

    private UALC(String[] args) {

        manageCmdLineOptions(args);
        fr.unice.polytech.s5.arch.totoriko.utils.asm.LookupTable.fillTables();
    }

    private void manageCmdLineOptions(String[] args) {

        Options options = new Options();

        Option input = new Option("i", "input", true, "path to asm source file");
        input.setRequired(true);

        Option output = new Option("o", "output", true, "output file");
        output.setRequired(false);

        Option binary = new Option("b", false, "output to raw binary instead of text");
        binary.setRequired(false);

        options.addOption(input);
        options.addOption(output);
        options.addOption(binary);


        CommandLineParser cmdParser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {

            cmd = cmdParser.parse(options, args);

        } catch (ParseException e) {

            System.out.println(e.getMessage());
            formatter.printHelp("utility-help", options);

            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {

        UALC cc = new UALC(args);
        UALAssembler assembler = new UALAssembler();

        String inputFile = cc.cmd.getOptionValue("input");
        String outputFile = cc.cmd.hasOption("o") ? cc.cmd.getOptionValue("o") : "a.out";

        List<Integer> op = assembler.build(inputFile);

        UALFileOutput writer;

        if ( cc.cmd.hasOption("b")) {
            writer = new BinaryFileOutput(outputFile);
            // TODO
        }

        else {
            writer = new LogisimFileOutput(outputFile);
            List<String> opHexStr = new ArrayList<>();

            op.forEach( i -> opHexStr.add(Integer.toHexString(i)));

            writer.setData(opHexStr);
        }


        writer.writeToDisk();
    }

}
