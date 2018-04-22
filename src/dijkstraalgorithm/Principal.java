package dijkstraalgorithm;

import java.util.Scanner;

/**
 *
 * @author Drakkar
 */
public class Principal {

    static int matriz[][];

    public static void main(String[] args) {

        double mult;
        double porciento;
        int cantidad, fila, columna, n, z;

        Dijkstra d = new Dijkstra();
        Scanner teclado = new Scanner(System.in);
        Scanner tecla = new Scanner(System.in);
        System.out.println("Ingrese de cuanto sera la matriz: ");
        n = teclado.nextInt();
        System.out.println("Indique el porcentaje de ceros: ");
        z = tecla.nextInt();
        //Cálculo de la cantidad de ceros, dependiendo el porcentaje
        mult = n * n;
        porciento = (mult * z) / 100;
        porciento = Math.round(porciento);
        cantidad = (int) porciento;
        System.out.println("Cantidad obtenida del porcentaje: " + cantidad);
        d.matz = new int[n + 2][n + 2];
        d.mAdy = new int[n * n][n * n];
        System.out.println("Valor de Matriz de Adyacencia: " + d.mAdy.length);
        //Ciclos para rellenar la matriz original con numeros aleatorios
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    d.matz[1][1] = 1;
                } else {
                    d.matz[i][j] = (int) (Math.random() * 10) + 1;
                }
            }
        }
        //Ciclo para colocar los 0's aleatoriamente
        for (int y = 0; y < cantidad; y++) {
            fila = (int) (Math.random() * n);
            columna = (int) (Math.random() * n);
            //System.out.println("[" + fila + "," + columna + "]");
            if (d.matz[fila][columna] != 0 && d.matz[fila][columna] != d.matz[1][1]) {
                d.matz[fila][columna] = 0;
            } else {
                y--;
            }
        }
        d.impresion();
        d.convertir();
        d.dijkstra();
    }
}
