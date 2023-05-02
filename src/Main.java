import javax.swing.*;

public class Main {

    public static JFrame lienzo;
    public static JFrame MainMenu;
    public static Plano objeto;
    public static char vertices[];

    public static void main(String[] args) {

        lienzo = new JFrame("Ingrese su Grafo");
        objeto = new Plano();
        lienzo.add(objeto);
        lienzo.pack();
        lienzo.setSize(600,600);
        lienzo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lienzo.setLocationRelativeTo(null);
        lienzo.setVisible(true);

        MainMenu = new JFrame("Instrucciones");
        MainMenu.add(new Instrucciones());
        MainMenu.setSize(400,400);
        MainMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainMenu.setLocationRelativeTo(lienzo);
        MainMenu.setVisible(true);


    }
}