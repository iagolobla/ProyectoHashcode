import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static int R, C, F, N, B, T, TActual = 0;
    static Celda[][] celdas;
    static HashMap<Integer, Viaje> viajes;
    static ArrayList<Coche> coches;

    public static void main (String[] args) throws Exception {

        viajes = new HashMap<>();

        String archivo = "a_example";
        String in = archivo + ".in";
        String out = archivo + ".out";

        BufferedReader input = leerArchivo(in);

        String primeraLinea = input.readLine();
        String[] tokens = primeraLinea.split("\\s");

        R = Integer.parseInt(tokens[0]);
        C = Integer.parseInt(tokens[1]);
        F = Integer.parseInt(tokens[2]);
        N = Integer.parseInt(tokens[3]);
        B = Integer.parseInt(tokens[4]);
        T = Integer.parseInt(tokens[5]);

        viajes = new HashMap<>(N, 1);
        coches = new ArrayList<>(F);

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

            s = valores[4];
            f = valores[5];

            Viaje v = new Viaje(inicio, fin, s, f, i);
            if(v.calcularDistancia() <= (f-s)){
                viajes.put(v.getId(), v);
            }
        }
        input.close();

        for (int i = 0; i < F; i++) coches.add(new Coche(celdas[0][0]));

        //Iteramos por los Ticks
        for(TActual = 0;TActual < T;TActual++){
            for(Coche coche : coches){
                int mejorDistancia = 0;
                ArrayList<Viaje> viajesCoche = new ArrayList<>();
                if(coche.getViajeActual() == null){
                    //Codigo para asignar un viaje
                    for(Viaje viaje : viajes.values()){
                        if(!viaje.estaExpirado()){
                            //Si no tiene un coche asignado el viaje
                            if(viaje.getCocheAsignado() == null){
                                //Si le da tiempo a completar el viaje
                                if(coche.calcularTiempoViaje(viaje) + TActual <= viaje.getF()){
                                    //Si el viaje tiene una longitud mejor que mejorDistancia
                                    if(viaje.calcularDistancia() > mejorDistancia){
                                        mejorDistancia = viaje.calcularDistancia();
                                        viajesCoche.add(viaje);
                                    }
                                }
                            }
                        }
                    }
                    //Iteramos por los mejores viajes para el coche y escogemos el mas largo
                    for(Viaje viaje : viajesCoche){
                        if(viaje.calcularDistancia() == mejorDistancia){
                            coche.setViajeActual(viaje);
                            break;
                        }
                    }
                }
                if(coche.getViajeActual() != null) {
                    coche.moverCoche();
                }
            }
        }

    }

    private static BufferedReader leerArchivo(String nombre) throws IOException {
        return new BufferedReader(new FileReader(nombre));
    }

}
