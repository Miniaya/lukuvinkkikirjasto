/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.io;

import java.util.Scanner;

public class ConsoleIO implements IO {
    
    @Override
    public void print(String toPrint) {
        System.out.println(toPrint);
    }
    
}