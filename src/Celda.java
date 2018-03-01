import java.awt.*;

public class Celda {
    private Point punto;


    public Celda(Point punto) {
        this.punto = punto;
    }

    public Point getPunto() {
        return punto;
    }

    public void setPunto(Point punto) {
        this.punto = punto;
    }

    @Override
    public String toString() {
        return punto.x + " " + punto.y;
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString()) && obj instanceof Celda;
    }

    public boolean comprobar() {
        return punto.x >= 0 && punto.x < Main.celdas.length && punto.y >= 0 && punto.y < Main.celdas[0].length;
    }

}
