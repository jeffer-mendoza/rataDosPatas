/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import static Main.Main.MAX_PERSONA;
import static Main.Main.espera;
import java.text.DecimalFormat;

/**
 *
 * @author jeffermh
 */
public class Banco {

    //número de personas 
    public static int MAX_PERSONA = 120;

    /*
    **** VARIABLES DE ENTRADA ****
    */
    //son las listas de valores futuros 
    int atencion[];
    int llegada[];
    
    
    //Son los tiempos acomulados serán calculados a partir de las listas de 
    //valores futuros, (instantes de tiempo)
    int tiempoLlegada[] = new int[MAX_PERSONA];
    int tiempoAtencion[] = new int[MAX_PERSONA];

    /*
    **** VARIABLES DE ESTADO ****
    */
    //estado de los cajeros
    //la longitud determina el número de cajeros
    //el valor de la posición i, determina el estado del cajero
    boolean cajeros[];

    /**
     * 
     * @param atencion tiempo de atención de usuario i 
     * @param llegada tiempo de llegada del usuario i, después del usuario (i - 1)
     * @param numeroCajeros el número de cajeros que se desea emplear para la simulacion
     */
    public Banco(int[] atencion, int[] llegada, int numeroCajeros) {
        this.atencion = atencion;
        this.llegada = llegada;
        this.cajeros = new boolean[numeroCajeros];
    }


    public void start() {
        float espera = 0;
        int maxCola = 0;//varible dinamica, que determina el mayor valor de cola
        this.tiempoLlegada[0] = 0;
        this.tiempoAtencion[0] = atencion[0];
        int atendido = 0;
        for (int i = 1; i < MAX_PERSONA; i++) {
            tiempoLlegada[i] = tiempoLlegada[i - 1] + llegada[i];//tiempo de llegada acomulado del usuario i(instante de tiempo)
            tiempoAtencion[i] = tiempoAtencion[i - 1] + atencion[i];//tiempo de atencion acomulado del usuario i (instante de tiempo)
            if (tiempoLlegada[i] > tiempoAtencion[i - 1]) {//si el instante de tiempo de llegada de i es mayor al instante del ultimo atendido (i - 1), i no realiza espera 
                tiempoAtencion[i] = tiempoLlegada[i];//se le atiende inmediatamente llega
            } else {//el cliente es quien espera, se calcula esta espera
                espera += tiempoAtencion[i] - tiempoLlegada[i];
            }
            int j = i;
            int cola = 0;
            while (j != 0) {//se calcula la cola
                if (tiempoLlegada[j] < tiempoAtencion[j - 1]) {//si i se encuentra en cola
                    cola++;
                    j--;//se repite el ciclo para calcular si (i - 1) sigue en cola
                } else {//se detiene cuando encuentra un i atendido
                    break;
                }
            }
            maxCola = (cola > maxCola) ? cola : maxCola;
        }
        
        
        
        
        DecimalFormat decimales = new DecimalFormat("0.00");
        float timeTotal = tiempoAtencion[MAX_PERSONA - 1] + atencion[MAX_PERSONA - 1];
//            System.out.println("Tiempo de entrada total: " + timeTotal / 60 + " minutos");
//            System.out.println("Máxima cola: " + maxCola + " personas");
//            System.out.println("Promedio de espera:" + espera / (MAX_PERSONA - 1) / 60 + " minutos");
        System.out.println(decimales.format(timeTotal / 60) + ";" + maxCola + ";" + decimales.format(espera / (MAX_PERSONA - 1) / 60));
    }

}
