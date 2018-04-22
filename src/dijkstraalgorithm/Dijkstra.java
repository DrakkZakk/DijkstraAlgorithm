package dijkstraalgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Drakkar convertir matriz original en matriz para grafos para
 * resolverlo con dijkstra Si la matriz es de 4*4 = 16, la matriz grafo sería de
 * 16*16
 */
public class Dijkstra {

    List lista_S = new ArrayList();
    List listaComp_S = new ArrayList();
    List caminos = new ArrayList();
    String temp;
    public int matz[][];
    public int mAdy[][];

    //Imprime la matriz Original
    public void impresion() {
        for (int x = 1; x < matz.length - 1; x++) {
            System.out.println("");
            for (int y = 1; y < matz.length - 1; y++) {
                System.out.print(matz[x][y] + "\t");
            }
        }
        System.out.println("");
    }

    public void convertir() {
        int tam = matz.length - 2;
        int mult, mult2, vertical, horizontal;
        //Ciclos para encontrar los vecinos
        if (matz[1][1 - 1] == 0 && matz[1 - 1][1 - 1] == 0 && matz[1 - 1][1] == 0 && matz[1 - 1][1 + 1] == 0
                && matz[1][1 + 1] == 0 && matz[1 + 1][1 + 1] == 0 && matz[1 + 1][1] == 0 && matz[1 + 1][1 - 1] == 0) {
            System.out.println("No hay conexiones!!");
        } else {
            mAdy[0][0] = matz[1][1];
            for (int i = 1; i < matz.length - 1; i++) {
                for (int j = 1; j < matz.length - 1; j++) {
                    if (matz[i][j] == 0) {

                    } else {
                        if (matz[i][j - 1] != 0) {
                            mult = (i - 1) * tam;
                            vertical = mult + (j - 1);
                            mult2 = (i - 1) * tam;
                            horizontal = mult2 + (j - 2);
                            mAdy[vertical][horizontal] = matz[i][j - 1];
                        }
                        if (matz[i - 1][j - 1] != 0) {
                            mult = (i - 1) * tam;
                            vertical = mult + (j - 1);
                            mult2 = (i - 2) * tam;
                            horizontal = mult2 + (j - 2);
                            mAdy[vertical][horizontal] = matz[i - 1][j - 1];
                        }
                        if (matz[i - 1][j] != 0) {
                            mult = (i - 1) * tam;
                            vertical = mult + (j - 1);
                            mult2 = (i - 2) * tam;
                            horizontal = mult2 + (j - 1);
                            mAdy[vertical][horizontal] = matz[i - 1][j];
                        }
                        if (matz[i - 1][j + 1] != 0) {
                            mult = (i - 1) * tam;
                            vertical = mult + (j - 1);
                            mult2 = (i - 2) * tam;
                            horizontal = mult2 + j;
                            mAdy[vertical][horizontal] = matz[i - 1][j + 1];
                        }
                        if (matz[i][j + 1] != 0) {
                            mult = (i - 1) * tam;
                            vertical = mult + (j - 1);
                            mult2 = (i - 1) * tam;
                            horizontal = mult2 + j;
                            mAdy[vertical][horizontal] = matz[i][j + 1];
                        }
                        if (matz[i + 1][j + 1] != 0) {
                            mult = (i - 1) * tam;
                            vertical = mult + (j - 1);
                            mult2 = i * tam;
                            horizontal = mult2 + j;
                            mAdy[vertical][horizontal] = matz[i + 1][j + 1];
                        }
                        if (matz[i + 1][j] != 0) {
                            mult = (i - 1) * tam;
                            vertical = mult + (j - 1);
                            mult2 = i * tam;
                            horizontal = mult2 + (j - 1);
                            mAdy[vertical][horizontal] = matz[i + 1][j];
                        }
                        if (matz[i + 1][j - 1] != 0) {
                            mult = (i - 1) * tam;
                            vertical = mult + (j - 1);
                            mult2 = i * tam;
                            horizontal = mult2 + (j - 2);
                            mAdy[vertical][horizontal] = matz[i + 1][j - 1];
                        }
                    }
                }
            }
            for (int x = 0; x < mAdy.length; x++) {
                for (int y = 0; y < mAdy.length; y++) {
                    if (x == y) {
                        mAdy[x][y] = 0;
                    } else if (mAdy[x][y] == 0) {
                        mAdy[x][y] = -1;
                    }
                }
            }
            //Imprime la matriz de Adyacencia
            for (int w = 0; w < mAdy.length; w++) {
                System.out.println("");
                for (int z = 0; z < mAdy.length; z++) {
                    System.out.print(mAdy[w][z] + "\t");
                }
            }
        }
    }

    public void dijkstra() {
        int nod, minimo, aux, nodCambio = 0, intento, origen = 0;
        //Inicializando listas
        for (int i = 0; i < mAdy.length; i++) {
            if (i != origen) {
                listaComp_S.add("" + i);
            } else {
                lista_S.add("" + i);
            }
            caminos.add("");
        }
        //Aplicando ciclo i de diksjtra
        for (int i = 0; i < mAdy.length; i++) {
            minimo = -1;
            for (int j = 0; j < listaComp_S.size(); j++) {
                nod = Integer.valueOf((String) (listaComp_S.get(j))).intValue();
                aux = min(nod);
                if (minimo == -1 || (aux < minimo && aux != -1)) {
                    minimo = aux;
                    nodCambio = j;
                }
            }
            if (minimo != -1) {
                lista_S.add("" + (String) (listaComp_S.get(nodCambio)));
                listaComp_S.remove(nodCambio);
            }
        }
        //Imprimiendo resultados
        for (int k = 0; k < caminos.size(); k++) {
            if (k != origen) {
                temp = (String) (caminos.get(k)) + (char) (k + 65);
                caminos.set(k, temp);
            }
        }
        for (int j = 0; j < caminos.size(); j++) {
            if (j != origen) {
                intento = 0;
                temp = (String) (caminos.get(j));
                while (temp.charAt(0) != (char) (origen + 65) && intento < 10) {
                    aux = temp.charAt(0) - 65;
                    temp = ((String) (caminos.get(aux))) + temp.substring(1, temp.length());
                    if (++intento == 10) {
                        temp = "*" + temp;
                    }
                };
                imprimeCamino(temp, j, origen);
            }
        }
        System.out.println("");
    }

    private int min(int dest) {
        int min = -1;
        int nod = 0;
        int nodOrig = -1;
        int aux;
        for (int i = 0; i < lista_S.size(); i++) {
            nod = Integer.valueOf((String) (lista_S.get(i))).intValue();
            if (mAdy[nod][nod] != -1 && mAdy[nod][dest] != -1) {
                aux = mAdy[nod][nod] + mAdy[nod][dest];
            } else {
                aux = -1;
            }
            if ((aux < min && aux != -1) || min == -1) {
                min = aux;
                nodOrig = nod;
            }
        }
        if (min != -1) {
            mAdy[dest][dest] = min;
            caminos.set(dest, "" + (char) (nodOrig + 65));
        }
        return min;
    }

    private void imprimeCamino(String cam, int nod, int o) {
        System.out.print("\nCamino: ");
        if (cam.charAt(0) == '*') {
            System.out.print("No hay camino de: " + (char) (o + 65) + " a: " + cam.charAt(cam.length() - 1));
        } else {
            for (int i = 0; i < cam.length(); i++) {
                System.out.print("" + cam.charAt(i) + (i == cam.length() - 1 ? "" : "->"));
            }
            System.out.print(" costo: " + mAdy[nod][nod]);
        }
    }
}
