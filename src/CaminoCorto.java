import java.awt.*;
import java.util.*;
import java.util.List;

public class CaminoCorto {

    int adjM[][];
    char vert[];
    int prev[];
    int visitado[];
    int inicio,fin;
    public static Graphics g;

    CaminoCorto(int adjM[][], char[] vert, int inicio, int fin){

        this.adjM = adjM;
        this.vert = vert;
        this.inicio = inicio;
        this.fin = fin;

        this.visitado = new int[Plano.vectorVertices.size()];
        this.prev = new int[Plano.vectorVertices.size()];
        Arrays.fill(this.prev, -1);

    }

    public void bfs(){

        java.util.Queue<Integer> queue = new LinkedList<>();
        visitado[inicio] = -1;
        queue.add(inicio);

        while (!queue.isEmpty()){

            int current_vert = queue.poll();
            int vert_vecino;

            while ((vert_vecino = vecinoNoVisitado(current_vert)) != -1){

                visitado[vert_vecino] = 1;
                queue.add(vert_vecino);
                prev[vert_vecino] = current_vert;

                if (vert_vecino == fin){
                    queue.clear();
                    break;
                }

            }

        }

        trazarRuta();

    }

    private int vecinoNoVisitado(int i){

        for (int j = 0; j <adjM.length; j++){

            if (adjM[i][j] == 1 && visitado[j] == 0){

                return j;

            }

        }

        return -1;

    }

    private void trazarRuta(){

        int vertice = fin;

        List<String> ruta = new ArrayList<>();
        while (vertice != -1){

            ruta.add(String.valueOf(vert[vertice]));
            vertice = prev[vertice];

        }

        Collections.reverse(ruta);


        for (int i = 0; i < ruta.size(); i++){

            for (int j = 0; j < Plano.vectorVertices.size(); j++){

                if (ruta.get(i).equals(Plano.vectorVertices.get(j).getNombre())){


                    g = Main.objeto.getGraphics();

                    g.setColor(new Color(155, 64, 247));
                    g.fillOval(Plano.vectorVertices.get(j).getX()-Vertice.d/2,Plano.vectorVertices.get(j).getY()-Vertice.d/2,Vertice.d,Vertice.d);
                    g.setColor(Color.black);
                    g.drawString(Plano.vectorVertices.get(j).getNombre(),Plano.vectorVertices.get(j).getX(),Plano.vectorVertices.get(j).getY());

                }

            }

        }

        for (int i = 0; i < ruta.size(); i++){

            if (i + 1 > ruta.size() -1){

            }else {

                for (int j = 0; j < Plano.vectorVertices.size(); j++){

                    if (ruta.get(i).equals(Plano.vectorVertices.get(j).getNombre())){

                        for (int k  = 0; k <Plano.vectorVertices.size(); k++){

                            if (ruta.get(i+1).equals(Plano.vectorVertices.get(k).getNombre())){

                                g.setColor(new Color(249, 6, 6));
                                g.drawLine(Plano.vectorVertices.get(j).getX(),Plano.vectorVertices.get(j).getY(),Plano.vectorVertices.get(k).getX(),Plano.vectorVertices.get(k).getY());

                            }

                        }


                    }

                }

            }



        }

        System.out.println(ruta);

    }


}
