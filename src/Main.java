import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static int R, C, F, N, B, T;

    public static void main (String[] args) throws Exception {

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







    }

    private static BufferedReader leerArchivo(String nombre) throws IOException {
        return new BufferedReader(new FileReader(nombre));
    }

}
