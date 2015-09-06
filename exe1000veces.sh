#!/bin/bash

echo "Tiempo Total;Max_Cola;Promedio_Espera" > out.csv
echo "Esta ejecutando el algoritmo para la discoteca RataDeDosPatas... espere un momento"
for a in `seq 1 1000`; do
    /opt/jdk1.8.0_45/bin/java -jar dist/RataDosPatas.jar >> out.csv
done
echo "Se ejecuto exitosamente, revise el archivo ./out.csv"
