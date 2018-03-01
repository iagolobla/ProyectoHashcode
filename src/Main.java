import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static int R, C, F, N, B, T, TActual = 0;
    static Celda[][] celdas;

    public static void main (String[] args) throws Exception {

        ArrayList<Viaje> viajes = new ArrayList<>();

        String in = "a_example.in";

        BufferedReader input = leerArchivo(in);

        String primeraLinea = input.readLine();
        String[] tokens = primeraLinea.split("\\s");

        R = Integer.parseInt(tokens[0]);
        C = Integer.parseInt(tokens[1]);
        F = Integer.parseInt(tokens[2]);
        N = Integer.parseInt(tokens[3]);
        B = Integer.parseInt(tokens[4]);
        T = Integer.parseInt(tokens[5]);

        celdas = new Celda[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                celdas[r][c] = new Celda(new Point(r, c));
            }
        }

        for (int i = 0; i < N; i++) {
            int a, b, x, y, s, f;

            String[] linea = input.readLine().split("\\s");
            int[] valores = new int[linea.length];

            for (int j = 0; j < linea.length; j++) valores[j] = Integer.parseInt(linea[j]); //mas rapido que ir caso a caso convirtiendo

            Celda inicio = new Celda(new Point(valores[0], valores[1]));
            Celda fin = new Celda(new Point(valores[2], valores[3]));

            int s = valores[4];
            int s = valores[5];

            Viaje v = new Viaje(inicio, fin, s, f);
            viajes.add(v);


        }







    }

    private static BufferedReader leerArchivo(String nombre) throws IOException {
        return new BufferedReader(new FileReader(nombre));
    }

}
