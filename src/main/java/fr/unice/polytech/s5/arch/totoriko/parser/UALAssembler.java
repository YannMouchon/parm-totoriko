package fr.unice.polytech.s5.arch.totoriko.parser;

import fr.unice.polytech.s5.arch.totoriko.UALLexer;
import fr.unice.polytech.s5.arch.totoriko.UALParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.List;

/**
 * UAL parsing. Returns a list of integer corresponding to translated instructions.
 */
public class UALAssembler {

    public List<Integer> build(String fromFileName) {

        CharStream stream = null;
        try {
            stream = CharStreams.fromFileName(fromFileName);
        } catch (IOException e) {
            System.err.println("No such file: " + e.getMessage());
            System.exit(1);
        }

        UALLexer lexer = new UALLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        UALParser parser = new UALParser(tokens);

        // makes use of the program rule inside UAL's grammar.
        ParseTree programTree = parser.program();

        UALLblListener lblOnlyListener = new UALLblListener();
        UALProgramListener listener = new UALProgramListener();

        ParseTreeWalker walker = new ParseTreeWalker();

        // first pass to get each label's memory address.
        walker.walk(lblOnlyListener, programTree);
        listener.setLabelsMemoryAddress(lblOnlyListener.getLabelsMemoryAddress());

        // second pass translating instructions
        walker.walk(listener, programTree);


        return listener.getOp();
    }
}
