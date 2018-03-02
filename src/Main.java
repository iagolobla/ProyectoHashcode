import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static int R, C, F, N, B, T, TActual = 0;
    static Celda[][] celdas;
    static Viaje[]  viajes;
    static Coche[] coches;

    public static void main (String[] args) throws Exception {

        String archivo = "e_high_bonus";
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

        viajes = new Viaje[N];
        coches = new Coche[F];

        celdas = new Celda[R][C];


        //Cargar Viajes
        for (int i = 0; i < N; i++) {
            int a, b, x, y, s, f;

            String[] linea = input.readLine().split("\\s");
            int[] valores = new int[linea.length];

            for (int j = 0; j < linea.length; j++) valores[j] = Integer.parseInt(linea[j]); //mas rapido que ir caso a caso convirtiendo

            celdas[valores[0]] [valores[1]] = new Celda(new Point(valores[0], valores[1]));
            Celda inicio = celdas[valores[0]] [valores[1]];
            celdas[valores[2]] [valores[3]] = new Celda(new Point(valores[2], valores[3]));
            Celda fin = celdas[valores[2]] [valores[3]];

            s = valores[4];
            f = valores[5];

            Viaje v = new Viaje(inicio, fin, s, f, i);
            //Asumimos que los viajes son realizables
            viajes[i] = v;
        }
        input.close();
        input = null;

        celdas[0][0] = new Celda(new Point(0,0));
        for (int i = 0; i < F; i++) coches[i] = (new Coche(celdas[0][0]));

        System.out.println("Se termino la carga de datos.");
        //Iteramos por los Ticks
        for(TActual = 0;TActual < T;TActual++){
            for(Coche coche : coches){
                int mejorDistancia = 0;
                int mejorDistanciaBonus = 0;
                ArrayList<Viaje> viajesCoche = new ArrayList<>();
                ArrayList<Viaje> viajesBonus = new ArrayList<>();
                if(coche.getViajeActual() == null){
                    //Codigo para asignar un viaje
                    for(int id = 0;id < N;id++){
                        Viaje viaje = viajes[id];
                        if(viaje != null) {
                            if (!viaje.estaExpirado()) {
                                //Si no tiene un coche asignado el viaje
                                if (viaje.getCocheAsignado() == null) {
                                    //Si le da tiempo a completar el viaje
                                    if (coche.calcularTiempoViaje(viaje) + TActual <= viaje.getF()) {
                                        //Criba de Bonus
                                        if (coche.getCeldaActual().calcularDistancia(viaje.getCeldaInicio()) + TActual <= viaje.getS()) {
                                            //Si el viaje tiene una longitud mejor que mejorDistancia
                                            if (viaje.calcularDistancia() > mejorDistanciaBonus) {
                                                mejorDistanciaBonus = viaje.calcularDistancia();
                                                viajesBonus.add(viaje);
                                            }
                                        } else {
                                            //Si el viaje tiene una longitud mejor que mejorDistancia
                                            if (viaje.calcularDistancia() > mejorDistancia) {
                                                mejorDistancia = viaje.calcularDistancia();
                                                viajesCoche.add(viaje);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //Iteramos por los mejores viajes para el coche y escogemos el mas largo
                    if(viajesBonus.isEmpty()) {
                        for (Viaje viaje : viajesCoche) {
                            if (viaje.calcularDistancia() == mejorDistancia) {
                                coche.setViajeActual(viaje);
                                break;
                            }
                        }
                    } else {
                        for (Viaje viaje : viajesBonus) {
                            if (viaje.calcularDistancia() == mejorDistanciaBonus) {
                                coche.setViajeActual(viaje);
                                break;
                            }
                        }
                    }
                }
                if(coche.getViajeActual() != null) {
                    coche.moverCoche();
                }

            }
        }


        PrintWriter writer = new PrintWriter(out, "ASCII");
        for (Coche c : coches) {
            writer.print(c.getViajesCompletados().size()+ " ");
            System.out.print(c.getViajesCompletados().size()+ " ");
            for (Viaje v : c.getViajesCompletados()) {
                writer.print(v.getId() + " ");
                System.out.print(v.getId() + " ");
            }
            writer.println();
            System.out.println();
        }
        writer.close();
    }

    private static BufferedReader leerArchivo(String nombre) throws IOException {
        return new BufferedReader(new FileReader(nombre));
    }

}
