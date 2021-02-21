package com.project.robots;

import java.awt.*;
import java.util.LinkedList;

public class RobotNettoyeurLibre extends RobotNettoyeur {
    public RobotNettoyeurLibre(Monde m, int x, int y) throws PositionInvalideException {
        super(m, x, y);
    }

    public RobotNettoyeurLibre(Monde monde) {
        super(monde);
    }

    /**
     * Se déplace aléatoirement et nettoie les cases adjacente
     */
    @Override
    public void nettoyer() {
        try {
            // nettoyer d'abord notre position initiale
            int x = posX;
            int y = posY;
            if (monde.containDirtyPaper(x, y)) {
                if (DEBUG)
                    System.out.println("Position (" + x + "," + y + ") nettoyée");
            }
            monde.cleanDirtyPaper(x, y);
            // on choisi une case au hazard et on se déplace
            LinkedList<Point> positionsValides = getListeDeplacements(1);
            Point positionFinale = getRandomElement(positionsValides);
            x = (int) positionFinale.getX();
            y = (int) positionFinale.getY();
            moveToPosition(x, y);
            if (monde.containDirtyPaper(x, y)) {
                if (DEBUG)
                    System.out.println("Position (" + x + "," + y + ") nettoyée");
            }
            monde.cleanDirtyPaper(x, y);
        } catch (PositionInvalideException e) {
            // Ceci n'est jamais sensé se produire puisqu'on se déplace dans une position valide
            e.printStackTrace();
        }
    }
}
