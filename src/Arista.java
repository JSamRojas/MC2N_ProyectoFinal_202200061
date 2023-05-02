import java.awt.*;

public class Arista {

    private int x1, y1, x2, y2;
    private String vSale;
    private String vEntra;

    public Arista(int x1, int y1, int x2, int y2, String vSale, String vEntra) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.vSale = vSale;
        this.vEntra = vEntra;
    }

    public void pintar(Graphics G){

        G.setColor(Color.black);
        G.drawLine(x1,y1,x2,y2);

    }

    public String getvSale() {
        return vSale;
    }

    public void setvSale(String vSale) {
        this.vSale = vSale;
    }

    public String getvEntra() {
        return vEntra;
    }

    public void setvEntra(String vEntra) {
        this.vEntra = vEntra;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
