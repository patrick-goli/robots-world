package project.robots;

import javax.swing.*;
import java.awt.*;

public class Monde {
    private static final boolean DIRTY = true;
    private static final boolean CLEAN = false;
    private final boolean[][] mat;
    private final int nbL;
    private final int nbC;
    private final CarteDuMonde carte;
    private final JFrame grille = new JFrame("Le Monde Des Robots \uD83E\uDD16");

    public Monde() {
        nbL = 10;
        nbC = 10;
        mat = new boolean[nbL][nbC];
        carte = new CarteDuMonde(nbL, nbC);
        grille.setPreferredSize(new Dimension(80 * nbC, 80 * nbL));
        grille.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Monde(int lignes, int colonnes) {
        nbL = lignes;
        nbC = colonnes;
        mat = new boolean[nbL][nbC];
        carte = new CarteDuMonde(nbL, nbC);
        grille.setPreferredSize(new Dimension(80 * nbC, 80 * nbL));
        grille.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        return mat[i][j];
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
        grille.add(carte);
        grille.pack();
        grille.setLocationRelativeTo(null);
        grille.setVisible(true);

    }

    private class CarteDuMonde extends JPanel {

        final int nbLignes;
        final int nbColonnes;

        public CarteDuMonde(int lignes, int colonnes) {
            nbLignes = lignes;
            nbColonnes = colonnes;
        }

        /**
         * Dessine le monde
         * Les indices (i,j) de ma matrice correspondent aux coordonnées (hauteur, largeur) sur le plan
         * Le robot en position (1,0) est en haut à gauche sur le graphique (afficher le monde pour voir)
         *
         * @param g objet Graphics
         */
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int sizeX = getWidth() / nbColonnes;// nombre de cases sur la hauteur
            int sizeY = getHeight() / nbLignes; //nombre de case sur la largeur

            int x = 0;
            for (int horz = 0; horz < nbColonnes; horz++) {
                int y = 0;
                for (int vert = 0; vert < nbLignes; vert++) {
                    try {
                        if (containDirtyPaper(y / sizeY, x / sizeX)) {
                            g.setColor(new Color(0, 0, 0));
                        } else {
                            g.setColor(new Color(144, 238, 144));
                        }

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
}