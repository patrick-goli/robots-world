import javax.swing.*;
import java.awt.*;

public class Monde extends JPanel {
    public int nbL;
    public int nbC;
    public boolean[][] mat;
    private static final boolean DIRTY = true;
    private static final boolean CLEAN = false;

    public Monde() {
        this.nbC =10;
        this.nbL = 10;
        this.mat = new boolean[nbL][nbC];
    }

    public Monde(int lignes, int colonnes) {
        this.nbC = colonnes;
        this.nbL = lignes;
        this.mat = new boolean[nbL][nbC];
    }

    public void putDirtyPaper(int i, int j) throws Exception {
        if (i < 0 || j < 0 || j > this.nbL || j > this.nbC) {
            throw new Exception("Case pas dans monde");
        }
        this.mat[i][j] = DIRTY;
    }

    public void cleanDirtyPaper(int i, int j) throws Exception {
        if (i < 0 || j < 0 || j > this.nbL || j > this.nbC) {
            throw new Exception("Case pas dans monde");
        }
        this.mat[i][j] = CLEAN;
    }

    public boolean containDirtyPaper(int i, int j) throws Exception {
        if (i < 0 || j < 0 || j > this.nbL || j > this.nbC) {
            throw new Exception("Case pas dans monde");
        }
        return this.mat[i][j] == DIRTY;
    }

    public int countDirtyPapers() {
        int sum = 0;
        for (int i = 0; i < this.nbL; i++) {
            for (boolean j : this.mat[i]) {
                sum += (j == DIRTY ? 1 : 0);
            }
        }
        return sum;
    }

    public void printMatrix() {
        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int size = Math.min(getWidth() - 4, getHeight() - 4) / 10;
        int width = getWidth() - (size * 2);
        int height = getHeight() - (size * 2);

        int y = (getHeight() - (size * 10)) / 2;
        for (int horz = 0; horz < 10; horz++) {
            int x = (getWidth() - (size * 10)) / 2;
            for (int vert = 0; vert < 10; vert++) {
                g.drawRect(x, y, size, size);
                x += size;
            }
            y += size;
        }
        g2d.dispose();
    }

}