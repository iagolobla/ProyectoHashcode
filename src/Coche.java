import java.util.ArrayList;

public class Coche {

    private ArrayList<Viaje> viajesCompletados;
    private Viaje viajeActual = null;
    private Celda celdaActual;
    private int tickFinalViaje = 0;

    public Coche(Celda inicio) {
        viajesCompletados = new ArrayList<>();
        this.celdaActual = inicio;
    }

    public void empezarViaje(int distancia){
        this.tickFinalViaje = Main.TActual+distancia;
    }

    public void moverCoche(){
        if(this.tickFinalViaje == Main.TActual){
            celdaActual = this.getViajeActual().getCeldaFin();
            this.viajesCompletados.add(this.viajeActual);
            this.viajeActual = null;
        }
    }

    public int calcularTiempoViaje(Viaje v){
        //Se coge lo que tarda mas: En llegar a la celda de inicio o en llegar a la hora de arranque
        int tiempoArrancar = Math.max(this.getCeldaActual().calcularDistancia(v.getCeldaInicio()),v.getS()-Main.TActual);
        return tiempoArrancar+v.calcularDistancia();
    }

    //Cosas a tener en cuenta:
        //La distancia: A mayor distancia, mayor puntuación
        //El bonus: En caso de que llegue antes de la hora de salida, se le suma el bonus
        //El tiempo en espera: Queremos evitar tiempo en el que los coches esten parados
    public int calcularPuntuacion(Viaje v){
        int Distancia = v.calcularDistancia();
        int Bonus = 0;
        //En caso de que la distancia hasta el inicio sea menor que lo que tarda en empezar el viaje
        if(this.getCeldaActual().calcularDistancia(v.getCeldaInicio())+Main.TActual <= v.getS()){
            Bonus = Main.B;
        }
        return Distancia+Bonus;
    }

    public ArrayList<Viaje> getViajesCompletados() {
        return viajesCompletados;
    }

    public void setViajesCompletados(ArrayList<Viaje> viajesCompletados) {
        this.viajesCompletados = viajesCompletados;
    }

    public Viaje getViajeActual() {
        return viajeActual;
    }

    public void setViajeActual(Viaje viajeActual) {
        this.viajeActual = viajeActual;
        empezarViaje(this.calcularTiempoViaje(viajeActual));
        viajeActual.setCocheAsignado(this);
    }

    public Celda getCeldaActual() {
        return celdaActual;
    }

    public void setCeldaActual(Celda celdaActual) {
        this.celdaActual = celdaActual;
    }

    public int getTickFinalViaje() {
        return tickFinalViaje;
    }

    public void setTickFinalViaje(int tickFinalViaje) {
        this.tickFinalViaje = tickFinalViaje;
    }

}
