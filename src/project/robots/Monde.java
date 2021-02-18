package project.robots;

import javax.swing.*;
import java.awt.*;

public class Monde extends JPanel {
    private static final boolean DIRTY = true;
    private static final boolean CLEAN = false;
    private final boolean[][] mat;
    private final int nbL;
    private final int nbC;

    public Monde() {
        nbL = 10;
        nbC = 10;
        mat = new boolean[nbL][nbC];
    }

    public Monde(int lignes, int colonnes) {
        nbL = lignes;
        nbC = colonnes;
        mat = new boolean[nbL][nbC];
    }

    public int getNbL() {
        return nbL;
    }

    public int getNbC() {
        return nbC;
    }

    /**
     * @param i coordonée x
     * @param j coordonee y
     * @return true si la position est validde
     */
    public boolean positionIsValid(int i, int j) {
        return i >= 0 && i < nbL && j >= 0 && j < nbC;
    }

    /**
     * @param i coordonée x
     * @param j coordonee y
     * @throws PositionInvalideException si la position est invalide
     */
    public void validatePosition(int i, int j) throws PositionInvalideException {
        if (!positionIsValid(i, j)) {
            throw new PositionInvalideException("(" + i + "," + j + ")");
        }
    }

    /**
     * Met un papier sale a la position indiquee
     *
     * @param i coordonée x
     * @param j coordonee y
     * @throws PositionInvalideException si la position est invalide
     */
    public void putDirtyPaper(int i, int j) throws PositionInvalideException {
        validatePosition(i, j);
        mat[i][j] = DIRTY;
    }

    /**
     * Nettoie un papier sale a la position indiquee
     *
     * @param i coordonée x
     * @param j coordonee y
     * @throws PositionInvalideException si la position est invalide
     */
    public void cleanDirtyPaper(int i, int j) throws PositionInvalideException {
        validatePosition(i, j);
        mat[i][j] = CLEAN;
    }

    /**
     * Teste si une position contient un paper sale
     *
     * @param i coordonée x
     * @param j coordonee y
     * @throws PositionInvalideException si la position est invalide
     */
    public boolean containDirtyPaper(int i, int j) throws PositionInvalideException {
        validatePosition(i, j);
        return mat[i][j] == DIRTY;
    }

    /**
     * Compte le nombre de papiers sales dans le monde
     *
     * @return Le nombre total
     */
    public int countDirtyPapers() {
        int sum = 0;
        for (int i = 0; i < nbL; i++) {
            for (boolean j : mat[i]) {
                sum += (j == DIRTY ? 1 : 0);
            }
        }
        return sum;
    }

    /**
     * Affiche le monde en utilisant la matrice
     */
    public void printMonde() {
        JFrame frame = new JFrame("Le Monde Des Robots");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(80 * nbC, 80 * nbL);
    }

    /**
     * Dessine le monde
     *
     * @param g objet Graphics
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int sizeX = getWidth() / nbC;
        int sizeY = getHeight() / nbL;

        int x = 0;
        for (int horz = 0; horz < nbC; horz++) {
            int y = 0;
            for (int vert = 0; vert < nbL; vert++) {
                try {
                    if (containDirtyPaper(y / sizeY, x / sizeX)) {
                        g.setColor(new Color(0, 0, 0));
                    } else {
                        g.setColor(new Color(144, 238, 144));
                    }

                    //g.fillOval(x, y, sizeX, sizeY);
                    //TODO set border color
                    g.fillRoundRect(x, y, sizeX, sizeY, 20, 20);
                    g.setColor(new Color(0, 0, 200));
                    g.drawRoundRect(x, y, sizeX, sizeY, 20, 20);
                } catch (PositionInvalideException e) {
                    e.printStackTrace();
                    System.exit(1);
                } catch (ArithmeticException e) {
                    //e.printStackTrace();
                    continue;
                }
                y += sizeY;
            }
            x += sizeX;
        }
        g2d.dispose();
    }

}