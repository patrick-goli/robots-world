import javax.swing.*;
import java.awt.*;

public class Monde extends JPanel {
    public int nbL;
    public int nbC;
    public boolean[][] mat;
    private static final boolean DIRTY = true;
    private static final boolean CLEAN = false;

    public Monde() {
        nbC =10;
        nbL = 10;
        mat = new boolean[nbL][nbC];
    }

    public Monde(int lignes, int colonnes) {
        nbC = colonnes;
        nbL = lignes;
        mat = new boolean[nbL][nbC];
    }

    public void putDirtyPaper(int i, int j) throws Exception {
        if (i < 0 || j < 0 || j > nbL || j > nbC) {
            throw new Exception("Case pas dans monde");
        }
        mat[i][j] = DIRTY;
    }

    public void cleanDirtyPaper(int i, int j) throws Exception {
        if (i < 0 || j < 0 || j > nbL || j > nbC) {
            throw new Exception("Case pas dans monde");
        }
        mat[i][j] = CLEAN;
    }

    public boolean containDirtyPaper(int i, int j) throws Exception {
        if (i < 0 || j < 0 || j > nbL || j > nbC) {
            throw new Exception("Case pas dans monde");
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
        int sizeY = getHeight()/nbL;
        int sizeX = getWidth()/nbC;

        int y = 0;
        for (int horz = 0; horz <nbL; horz++) {
            int x = 0;
            for (int vert = 0; vert <nbC; vert++) {
                try {
                    if(containDirtyPaper(x/sizeX, y/sizeY)) {
                        g.setColor(new Color(0,0,0));
                    }else {
                        g.setColor(new Color(144, 238, 144));
                    }

                    g.fillOval(x, y, sizeX, sizeY);
                    //g.setColor(new Color(0, 0, 200));
                    //g.fillRoundRect(x,y,sizeX,sizeY,2,2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                x += sizeX;
            }
            y += sizeY;
        }
        g2d.dispose();
    }

}