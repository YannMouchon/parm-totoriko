package fr.unice.polytech.s5.arch.totoriko.utils.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogisimFileOutput extends UALFileOutput<String> {

    public LogisimFileOutput(String filename) {
        super(filename);
    }

    @Override
    public void writeToDisk(String filename) throws IOException {

        StringBuilder s = new StringBuilder();

        s.append("v2.0 raw").append(System.lineSeparator());

        data.forEach(op -> s.append(op).append(" "));

        File file;
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));

        writer.write(s.toString());

        writer.close();
    }
}
