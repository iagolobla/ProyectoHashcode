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

}
