import java.awt.*;

public class Vertice {

    private int x,y;
    private String nombre;
    public static final int d = 40;

    public Vertice(int x, int y, String nombre) {
        this.x = x;
        this.y = y;
        this.nombre = nombre;
    }

    public void pintar(Graphics G){

        G.setColor(new Color(81, 86, 247));
        G.fillOval(this.x - d/2,this.y - d/2,d,d);
        G.setColor(Color.black);
        G.drawString(nombre,this.x,this.y);

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

