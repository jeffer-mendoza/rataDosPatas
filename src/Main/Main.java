/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jefferson
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Integer> requisa = new ArrayList<Integer>();
        List<Integer> cola = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            requisa.add(espera(60, 120));
            cola.add(espera(10, 180));
        }
        System.out.println(requisa.toString());
        System.out.println(cola.toString());
    }

    public static int espera(int a, int b) {
        Random random = new Random();
        return  a + Math.abs((random.nextInt() % (b - a)));
    }

}
