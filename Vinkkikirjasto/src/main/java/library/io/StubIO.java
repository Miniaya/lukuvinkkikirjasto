/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.io;

import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {

    private List<String> prints;
    private List<String> inputs;
    private int index;
    
    public StubIO() {
        prints = new ArrayList();
        inputs = new ArrayList();
        index = 0;
    }

    public StubIO(List<String> inputs) {
        prints = new ArrayList<>();
        this.inputs = inputs;
        index = 0;
    }

    @Override
    public void print(String toPrint) {
        prints.add(toPrint);
    }

    @Override
    public String readLine(String prompt) {
        String line = inputs.get(index);
        index++;
        if (index >= inputs.size()) {
            index = 0;
        }
        return line;
    }

    public List<String> getPrints() {
        return prints;
    }
    
    public void setInputs(List<String> inputs) {
        this.inputs = inputs;
    }
    
}