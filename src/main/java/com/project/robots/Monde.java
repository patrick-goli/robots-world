package com.project.robots;

public class Monde {
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

    public void cleanAllDirtyPapers() {
        for (int i = 0; i < nbL; i++) {
            for (int j = 0; j < nbC; j++) {
                mat[i][j] = CLEAN;
            }
        }
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


    public void showMatrix() {
        for (int i = 0; i < nbL; i++) {
            for (int j = 0; j < nbC; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("\n");
        }
        System.out.println(" ");
    }

}