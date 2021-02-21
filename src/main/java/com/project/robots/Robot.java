package com.project.robots;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public abstract class Robot {
    public static boolean DEBUG = false; //Pour afficher ce qui se passe en arrière-plan
    protected final Monde monde;
    protected int posY;
    protected int posX;

    public Robot(Monde m, int x, int y) throws PositionInvalideException {
        monde = m;
        monde.validatePosition(x, y);
        posX = x;
        posY = y;
    }

    /**
     * Cree un robot positioné aléatoirement
     *
     * @param m Le monde
     */
    public Robot(Monde m) {
        monde = m;
        posX = (int) (monde.getNbL() * Math.random());
        posY = (int) (monde.getNbC() * Math.random());
        if (DEBUG)
            System.out.println(this.toString() + " créé aux coordonnées (" + posX + "," + posY + ")");
    }

    /**
     * Randomly select an element based on index
     *
     * @param list The list to choose from
     * @return A randomly selected element of the list
     */
    public static Point getRandomElement(Point[] list) {
        return list[new Random().nextInt(list.length)];
    }

    /**
     * Se déplacer à la position souhaitée
     *
     * @param x coordonée
     * @param y coordonee
     * @throws PositionInvalideException si la position est invalide
     */
    public void moveToPosition(int x, int y) throws PositionInvalideException {
        monde.validatePosition(x, y);
        posX = x;
        posY = y;
        if (DEBUG)
            System.out.println(this.toString() + " se déplace aux coordonnées (" + x + "," + y + ")");
    }

    /**
     * Se déplacer dans le monde des robots
     *
     * @throws Exception Pas encore implémentée
     */
    public void parcourir() throws Exception {
        throw new Exception("Ne sait pas encore se deplacer");
    }

    /**
     * Calcule les deplacements possibles avec un pas donné
     *
     * @param pas par defaut = 1
     * @return La liste les coordonnées des positions
     */
    public Point[] getListeDeplacements(int pas) {
        //assert (pas >0 && pas < Math.max(monde.getNbC(), monde.getNbL()));
        LinkedList<Point> positionsValides = new LinkedList<>();
        //On calcule les positions de mouvement : max 8 cases possibles
        // une case adjacente a {-1, 0, +1} en plus des coordonnées
        int[] positions = {-pas, 0, pas};

        for (int i : positions) {
            for (int j : positions) {
                if (i == 0 && j == 0) {
                    //correspond à faire du sur-place
                    continue;
                }
                if (monde.positionIsValid(posX + i, posY + j)) {
                    //on s'assure de ne pas sortir du cadre
                    positionsValides.add(new Point(posX + i, posY + j));
                }
            }
        }
        return positionsValides.toArray(new Point[0]);
    }

}
