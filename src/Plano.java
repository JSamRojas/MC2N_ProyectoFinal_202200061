import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

public class Plano extends JPanel implements MouseListener, MouseMotionListener {

    public static Vector<Vertice> vectorVertices;
    public static Vector<Arista> vectorAristas;
    public static Point p1,p2;
    public static Graphics C;
    private Vertice vertMover;
    private int iVert;
    public static String verticeSale = "";
    public static String verticeEntra = "";
    public static String vertices = "";


    public Plano(){

        vectorVertices = new Vector<>();
        vectorAristas = new Vector<>();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    @Override
    public void paint(Graphics  G){

        super.paint(G);

        for (Vertice vertice: vectorVertices){


            vertice.pintar(G);

        }

        for (Arista art: vectorAristas){


            art.pintar(G);

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1){

            String nombre = "";

            nombre = JOptionPane.showInputDialog("Ingrese el nombre de su vertice: ");


            if (nombre.trim().isEmpty()){

                JOptionPane.showMessageDialog(Main.lienzo,"Ingrese el nombre de su vertice","ERROR",JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (nombre.equals(null)){
                return;
            }

            for (Vertice vertice: vectorVertices){

                if (vertice.getNombre().equals(nombre)){
                    JOptionPane.showMessageDialog(Main.lienzo,"no puede ingresar dos vertices con el mismo nombre","ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }
            vertices = vertices + nombre;
            vectorVertices.add(new Vertice(e.getX(),e.getY(),nombre));

            repaint();

        }

        if (e.getButton() == MouseEvent.BUTTON3){

            for (Vertice vert: vectorVertices) {

                if (new Rectangle(vert.getX() - Vertice.d/2, vert.getY() - Vertice.d/2,Vertice.d,Vertice.d).contains(e.getPoint())){
                    if (p1 == null){
                        p1 = new Point(vert.getX(),vert.getY());
                        verticeSale = vert.getNombre();
                    }else {
                        p2 = new Point(vert.getX(),vert.getY());
                        verticeEntra = vert.getNombre();
                        vectorAristas.add(new Arista(p1.x,p1.y,p2.x,p2.y,verticeSale,verticeEntra));
                        repaint();
                        p1 = null;
                        p2 = null;
                        verticeEntra ="";
                        verticeSale = "";
                    }
                }

            }


        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int i = 0;

        for (Vertice vertice: vectorVertices){

            if (new Rectangle(vertice.getX() - Vertice.d/2, vertice.getY() - Vertice.d/2,Vertice.d/2,Vertice.d/2).contains(e.getPoint())){

                vertMover = vertice;
                iVert = i;
                break;

            }

            i++;

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        vertMover = null;
        iVert = -1;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (vertMover!=null){

            vectorVertices.set(iVert, new Vertice(e.getX(),e.getY(),vertMover.getNombre()));
            int iArt = 0;

            for (Arista art: vectorAristas){

                if (new Rectangle(art.getX1() - Vertice.d/2, art.getY1() - Vertice.d/2, Vertice.d,Vertice.d).contains(e.getPoint())){

                    vectorAristas.set(iArt,new Arista(e.getX(),e.getY(),art.getX2(),art.getY2(),art.getvSale(),art.getvEntra()));

                } else if (new Rectangle(art.getX2() - Vertice.d/2, art.getY2() - Vertice.d/2, Vertice.d,Vertice.d).contains(e.getPoint())) {

                    vectorAristas.set(iArt,new Arista(art.getX1(),art.getY1(), e.getX(),e.getY(),art.getvSale(),art.getvEntra()));

                }
                iArt++;

            }

        }
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}