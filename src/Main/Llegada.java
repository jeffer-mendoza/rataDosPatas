/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jefferson
 */
public class Llegada extends Thread {

    List<Integer> cola = new ArrayList<Integer>();

    public void run() {
        Random random = new Random();
        int tiempo = 0;
        while (cola.size() < 10) {
            try {
                tiempo = 10 + Math.abs((random.nextInt() % 170));
                Thread.sleep(tiempo);
                cola.add(tiempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(Llegada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Integer> getCola() {
        return cola;
    }
    
    

}
