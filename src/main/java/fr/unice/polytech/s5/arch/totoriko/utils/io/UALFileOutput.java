package fr.unice.polytech.s5.arch.totoriko.utils.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class UALFileOutput<T> {

    protected String filename;
    List<T> data;

    public UALFileOutput() {
        this(null);
    }

    public UALFileOutput(String filename) {

        this.filename = filename;
        data = new ArrayList<>(100);
    }

    public void append(T data) {
        this.data.add(data);
    }

    public void writeToDisk() throws IOException {
        writeToDisk(this.filename);
    }

    public abstract void writeToDisk(String filename) throws IOException;

    public void setData(List<T> data) {
        this.data = data;
    }
}
