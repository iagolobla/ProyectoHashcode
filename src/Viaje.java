public class Viaje {

    private Celda celdaInicio, celdaFin;
    private int s;
    private int f;
    private Coche cocheAsignado;
    int id;

    public Viaje(Celda celdaInicio, Celda celdaFin, int s, int f, int id) {
        this.celdaInicio = celdaInicio;
        this.celdaFin = celdaFin;
        this.s = s;
        this.f = f;
        this.id = id;
    }

    public int calcularDistancia() {
        return celdaInicio.calcularDistancia(celdaFin);
    }

    public boolean estaExpirado(){
        if(Main.TActual >= this.f){
            return true;
        }
        return false;
    }

    public Celda getCeldaInicio() {
        return celdaInicio;
    }

    public void setCeldaInicio(Celda celdaInicio) {
        this.celdaInicio = celdaInicio;
    }

    public Celda getCeldaFin() {
        return celdaFin;
    }

    public void setCeldaFin(Celda celdaFin) {
        this.celdaFin = celdaFin;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Coche getCocheAsignado() {
        return cocheAsignado;
    }

    public void setCocheAsignado(Coche cocheAsignado) {
        this.cocheAsignado = cocheAsignado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
