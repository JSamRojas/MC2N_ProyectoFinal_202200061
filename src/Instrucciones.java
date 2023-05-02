import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Instrucciones extends JPanel implements ActionListener {

    public static JButton BtnBuscarCaminos;
    public static JLabel Instrucciones;
    public static JLabel Titulo;
    public static String TEXTO;
    public static int Matriz[][];
    public static int columna = 0;
    public static String vectorAdy = "";
    public static String imprimir = "";
    public static int k = 0;
    public static int inicio = 0;
    public static int fin = 0;
    public static ArrayList<String> nombresVertices = new ArrayList<String>();


    public Instrucciones(){

        Initcomponents();

    }

    private void Initcomponents(){

        TEXTO = "1. Presione Clic izquierdo sobre el plano para crear un vertice "+
                "2. Presione Clic derecho sobre un vertice y luego sobre el siguiente donde desea crear la arista " +
                "3. Cuando su grafo este listo, presione el boton para encontrar los caminos " +
                "5. Si desea mover alguna arista de lugar, puede hacerlo manteniendo el clic izquierdo sobre la misma " +
                "4. Si no hay ningun grafo ingresado, la aplicacion dara error";

        BtnBuscarCaminos = new JButton("Encontrar Caminos");
        BtnBuscarCaminos.setBounds(50,300,300,50);
        BtnBuscarCaminos.setFont(new Font("Tahoma",Font.BOLD,14) );
        Main.MainMenu.add(BtnBuscarCaminos);
        BtnBuscarCaminos.addActionListener(this);

        Titulo = new JLabel("Instrucciones");
        Titulo.setBounds(40,20,320,40);
        Titulo.setHorizontalAlignment(JLabel.CENTER);
        Titulo.setFont(new Font("Tahoma",Font.BOLD,14) );
        Main.MainMenu.add(Titulo);

        Instrucciones = new JLabel("<html><p> " + TEXTO + "</p></html>");
        Instrucciones.setBounds(50,100,300,150);
        Instrucciones.setHorizontalAlignment(JLabel.CENTER);
        Instrucciones.setFont(new Font("Tahoma",Font.BOLD,12) );
        Main.MainMenu.add(Instrucciones);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.BtnBuscarCaminos){

            if (Plano.vectorVertices.isEmpty() || Plano.vectorAristas.isEmpty()){

                JOptionPane.showMessageDialog(Main.lienzo,"Ingrese un grafo valido","ERROR",JOptionPane.ERROR_MESSAGE);
                return;

            }

            Matriz = new int[Plano.vectorVertices.size()][Plano.vectorVertices.size()];

            for (int i = 0; i < Plano.vectorVertices.size(); i++){

                for (int j = 0; j < Plano.vectorVertices.size(); j++){

                    Matriz[i][j] = 0;

                }

            }

            cambiarmatriz();

            Main.vertices = Plano.vertices.toCharArray();

            for (int i = 0; i < Plano.vectorVertices.size(); i++){

                nombresVertices.add(Plano.vectorVertices.get(i).getNombre());

            }
            Object[] arrayvertices = nombresVertices.toArray();

            String verticeInicio = (String) JOptionPane.showInputDialog(null,"Selecciona el vertice de inicio","Seleccione el vertice",JOptionPane.DEFAULT_OPTION,null,arrayvertices,arrayvertices[0]);

            String verticeFinal = (String) JOptionPane.showInputDialog(null,"Selecciona el vertice final","Seleccione el vertice",JOptionPane.DEFAULT_OPTION,null,arrayvertices,arrayvertices[0]);


            inicio = Encontrarvert(verticeInicio);
            fin = Encontrarvert(verticeFinal);

            for (int i = 0; i < Matriz.length; i++){

                for (int j = 0; j < Matriz.length; j++){

                    imprimir = imprimir + "[" + Matriz[i][j] + "]";

                }
                imprimir = imprimir + "\n";
            }

            // System.out.println(imprimir);

            CaminoCorto caminoCorto = new CaminoCorto(Matriz,Main.vertices, inicio,fin);
            caminoCorto.bfs();


        }

    }

    public int Encontrarvert(String vect){

        for (int i = 0; i < Plano.vectorVertices.size(); i++){

            if (vect.equals(Plano.vectorVertices.get(i).getNombre())){

                return i;
            }
        }
        return 0;
    }

    public void cambiarmatriz(){

        for (int i = 0; i < Plano.vectorVertices.size(); i++){

            for (int j = 0; j < Plano.vectorAristas.size(); j++){

                if (i == k){

                    Matriz[i][k] = 0;

                }

                if (Plano.vectorVertices.get(i).getNombre().equals(Plano.vectorAristas.get(j).getvSale())){

                    vectorAdy = "";
                    vectorAdy = Plano.vectorAristas.get(j).getvEntra();
                    columna = Encontrarvert(vectorAdy);
                    Matriz[i][columna] = 1;

                } else if (Plano.vectorVertices.get(i).getNombre().equals(Plano.vectorAristas.get(j).getvEntra())) {

                    vectorAdy = "";
                    vectorAdy = Plano.vectorAristas.get(j).getvSale();
                    columna = Encontrarvert(vectorAdy);
                    Matriz[i][columna] = 1;

                }

            }
            k++;

        }

    }


}