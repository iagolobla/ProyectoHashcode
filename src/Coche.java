import java.util.ArrayList;

public class Coche {

    private ArrayList<Viaje> viajesCoche;
    Celda celdaActual;

    public Coche(Celda inicio) {
        this.celdaActual = inicio;
    }

    public void mover(int direccion) {
        switch (direccion) {
            case 0: //izquierda, c-1

                break;
            case 1: //arriba, r-1

                break;

            case 2: //derecha, c+1

                break;

            case 3: //abajo, r+1

                break;
        }
    }

}
