/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jefferson
 */
public class Main {

    public static int MAX_PERSONA = 181;
    public static int REPETICIONES = 1000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Declaracion de vectores para almacenar los tiempos
        //las variables que empiezan por la palabra time son vectores de tiempos 
        //acomulados, es decir tiempo desde 0 hasta la persona i
        float tiempoTotalProm = 0;
        int maxColaProm = 0;
        float esperaProm = 0;
        for (int m = 0; m < 1000; m++) {
            int requisa[] = new int[MAX_PERSONA];
            int llegada[] = new int[MAX_PERSONA];
            int timeLlegada[] = new int[MAX_PERSONA];
            int timeAtencion[] = new int[MAX_PERSONA];
            float espera = 0;
            int maxCola = 0;
            timeLlegada[0] = 0;
            timeAtencion[0] = 0;
            int atendido = 0;
            for (int i = 1; i < MAX_PERSONA; i++) {
                requisa[i] = espera(60, 120);//se halla valor i de la requisa
                llegada[i] = espera(10, 180);//se halla en valor i de la llegada
                timeLlegada[i] = timeLlegada[i - 1] + llegada[i];//tiempo de llegada 
                timeAtencion[i] = timeAtencion[i - 1] + requisa[i - 1];//tiempo de atencion
                if (timeLlegada[i] > timeAtencion[i]) {//en este caso requisador esta esperando a que llegue el siguiente
                    timeAtencion[i] = timeLlegada[i];//se le atiende inmediatamente llega
                } else {//el cliente es quien espera, se calcaula esta espera
                    espera += timeAtencion[i] - timeLlegada[i];
                }
                int j = i;
                int cola = 0;
                while (j != 0) {//se calcula la cola
                    if (timeLlegada[j] < timeAtencion[j - 1]) {//si i se encuentra en cola
                        cola++;
                        j--;//se repite el ciclo para calcular si (i - 1) sigue en cola
                    } else {//se detiene cuando encuentra un i atendido
                        break;
                    }
                }
                maxCola = (cola > maxCola) ? cola : maxCola;
            }
            float timeTotal = timeAtencion[MAX_PERSONA - 1] + requisa[MAX_PERSONA - 1];
//            System.out.println("Tiempo de entrada total: " + timeTotal / 60 + " minutos");
//            System.out.println("Máxima cola: " + maxCola + " personas");
//            System.out.println("Promedio de espera:" + espera / (MAX_PERSONA - 1) / 60 + " minutos");
            tiempoTotalProm += timeTotal;
            maxColaProm += maxCola;
            esperaProm += espera;
        }
        System.out.println("Tiempo de entrada total: " + tiempoTotalProm / REPETICIONES / 60 + " minutos");
        System.out.println("Máxima cola: " + maxColaProm / REPETICIONES + " personas");
        System.out.println("Promedio de espera:" + esperaProm / REPETICIONES / (MAX_PERSONA - 1) / 60 + " minutos");
    }

    public static int espera(int a, int b) {
        Random random = new Random();
        return a + Math.abs((random.nextInt() % (b - a)));
    }

    public static void roba() {
        //Declaracion de vectores para almacenar los tiempos
        //las variables que empiezan por la letra t, son array de los tiempos acomulados
        int requisa[] = new int[MAX_PERSONA];
        int cola[] = new int[MAX_PERSONA];
        int tReal[] = new int[MAX_PERSONA];
        int tAtendido[] = new int[MAX_PERSONA];
        int tEspera[] = new int[MAX_PERSONA];
        int personaAcomuladas[] = new int[MAX_PERSONA];
        tReal[0] = 0;
        tAtendido[0] = 0;
        int atendido = 0;
        for (int i = 1; i < MAX_PERSONA; i++) {
            requisa[i] = espera(60, 120);
            cola[i] = espera(10, 180);
            tReal[i] = tReal[i - 1] + cola[i];
            tAtendido[i] = tAtendido[i - 1] + requisa[i - 1];
            if (tReal[i] > tAtendido[i]) {//el requisador es el que espera
                tAtendido[i] = tReal[i];
            } else {//el cliente es quien se espera, se calcaula esta espera
                tEspera[i] = tAtendido[i] - tReal[i];
            }
            int j = i;
            int personasAcomuladas = 0;
            while (j != 0) {
                if (tReal[j] < tAtendido[j - 1]) {
                    personasAcomuladas++;
                    j--;
                } else {
                    break;
                }
            }
            personaAcomuladas[i] = personasAcomuladas;
        }
        System.out.println("Tiempo de requisa: " + Arrays.toString(requisa));
        System.out.println("Tiempo de llegada cola: " + Arrays.toString(cola));
        System.out.println("Tiempo real de llegada: " + Arrays.toString(tReal));
        System.out.println("Tiempo real de atencion: " + Arrays.toString(tAtendido));
        System.out.println("Tiempo real de espera: " + Arrays.toString(tEspera));

    }

}
