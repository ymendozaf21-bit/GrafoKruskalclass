package grafokruskalclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class GrafoKruskalclass {

    static class Arista {
        int origen, destino, peso;

        Arista(int o, int d, int p) {
            origen = o;
            destino = d;
            peso = p;
        }
    }

    static class Grafo {
        int vertices;
        List<Arista> aristas;

        Grafo(int v) {
            vertices = v;
            aristas = new ArrayList<>();
        }

        void agregarArista(int o, int d, int p) {
            aristas.add(new Arista(o, d, p));
        }
    }

    static class Kruskal {
        int[] padre;

        Kruskal(int n) {
            padre = new int[n];
            for (int i = 0; i < n; i++) {
                padre[i] = i;
            }
        }

        int find(int i) {
            if (padre[i] == i)
                return i;
            return padre[i] = find(padre[i]);
        }

        void union(int a, int b) {
            int raizA = find(a);
            int raizB = find(b);
            padre[raizA] = raizB;
        }

        void ejecutar(Grafo g) {
            Collections.sort(g.aristas, Comparator.comparingInt(a -> a.peso));

            List<Arista> resultado = new ArrayList<>();

            for (Arista a : g.aristas) {
                int x = find(a.origen);
                int y = find(a.destino);

                if (x != y) {
                    resultado.add(a);
                    union(x, y);
                }
            }

            System.out.println("Arbol de expansion minima (Kruskal):");
            for (Arista a : resultado) {
                System.out.println(a.origen + " - " + a.destino + " : " + a.peso);
            }
        }
    }

    public static void main(String[] args) {

        Grafo g = new Grafo(4);

        g.agregarArista(0, 1, 10);
        g.agregarArista(0, 2, 6);
        g.agregarArista(0, 3, 5);
        g.agregarArista(1, 3, 15);
        g.agregarArista(2, 3, 4);

        Kruskal k = new Kruskal(4);
        k.ejecutar(g);
    }
}