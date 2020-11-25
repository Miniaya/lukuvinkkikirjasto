package library.ui;

import java.io.IOException;
import library.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        IO io = new ConsoleIO();
        CLUI ui = new CLUI(io);
        ui.init();
    
    }
}