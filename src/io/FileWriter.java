package io;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Mihail
 */
public class FileWriter {

    String text;

    public FileWriter(String text){
        this.text = text;
    }

    public void writeFile() throws IOException {
        PrintWriter writer = new PrintWriter("output.txt","UTF-8");
        writer.println(text);
        writer.close();
    }
}
