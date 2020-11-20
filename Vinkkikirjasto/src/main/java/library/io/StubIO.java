/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.io;

import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {

    private ArrayList<String> prints;

    public StubIO() {
        prints = new ArrayList<>();
    }

    public void print(String toPrint) {
        prints.add(toPrint);
    }



    public ArrayList<String> getPrints() {
        return prints;
    }
    
}