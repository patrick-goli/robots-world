package project.robots;
import javax.swing.*;
import java.awt.*;

public class Monde extends JPanel {
    public int nbL;
    public int nbC;
    private final boolean[][] mat;
    private static final boolean DIRTY = true;
    private static final boolean CLEAN = false;

    public Monde() {
        nbL = 10;
        nbC =10;
        mat = new boolean[nbL][nbC];
    }

    public Monde(int lignes, int colonnes) {
        nbL = lignes;
        nbC = colonnes;
        mat = new boolean[nbL][nbC];
    }


    public boolean positionIsValid(int i, int j){
        return i >= 0 && i < nbL && j >= 0 && j < nbC  ;
    }

    public void putDirtyPaper(int i, int j) throws Exception {
        if (!positionIsValid(i,j)) {
            throw new Exception("Case (" + i + "," + j +") pas dans monde");
        }
        mat[i][j] = DIRTY;
    }

    public void cleanDirtyPaper(int i, int j) throws Exception {
        if (!positionIsValid(i,j)) {
            throw new Exception("Case (" + i + "," + j +") pas dans monde");
        }
        mat[i][j] = CLEAN;
    }

    public boolean containDirtyPaper(int i, int j) throws Exception {
        if (!positionIsValid(i,j)) {
            throw new Exception("Case (" + i + "," + j +") pas dans monde");
        }
        return mat[i][j] == DIRTY;
    }

    public int countDirtyPapers() {
        int sum = 0;
        for (int i = 0; i < nbL; i++) {
            for (boolean j : mat[i]) {
                sum += (j == DIRTY ? 1 : 0);
            }
        }
        return sum;
    }

    public void printMatrix() {
        JFrame frame = new JFrame("Monde");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50*nbC,50*nbL);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int sizeX = getWidth()/nbC;
        int sizeY = getHeight()/nbL;

        int x = 0;
        for (int horz = 0; horz <nbC; horz++) {
            int y = 0;
            for (int vert = 0; vert <nbL; vert++) {
                try {
                    if(containDirtyPaper(y/sizeY, x/sizeX)) {
                        g.setColor(new Color(0,0,0));
                    }else {
                        g.setColor(new Color(144, 238, 144));
                    }

                    g.fillOval(x, y, sizeX, sizeY);
                    //TODO set border color
                    //g.setColor(new Color(0, 0, 200));
                    //g.fillRoundRect(x,y,sizeX,sizeY,2,2);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                y += sizeY;
            }
            x += sizeX;
        }
        g2d.dispose();
    }

}