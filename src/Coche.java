import java.util.ArrayList;

public class Coche {

    private ArrayList<Viaje> viajesCoche;
    private Celda celdaActual;
    private int tickFinalViaje = 0;

    public Coche(Celda inicio) {
        this.celdaActual = inicio;
    }

    public void empezarViaje(int distancia){
        this.tickFinalViaje = Main.TActual+distancia;
    }

    public void moverCoche(Celda celdaFinal){
        if(this.tickFinalViaje == Main.TActual){
            celdaActual = celdaFinal;
        }
    }

}
