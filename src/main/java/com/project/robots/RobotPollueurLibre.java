package com.project.robots;

import java.awt.*;
import java.util.LinkedList;

public class RobotPollueurLibre extends Robot {

    public RobotPollueurLibre(Monde m) {
        super(m);
    }


    /**
     * Se déplace aléatoirement et polllue les cases adjacente
     */
    @Override
    public void parcourir() {

        try {
            // pollue d'abord notre position initiale
            monde.putDirtyPaper(posX, posY);
            // on choisi une case au hazard et on se déplace
            LinkedList<Point> positionsValides = getListeDeplacements(1);
            Point positionFinale = getRandomElement(positionsValides);
            int x = (int) positionFinale.getX();
            int y = (int) positionFinale.getY();

            moveToPosition(x, y);
            monde.putDirtyPaper(x, y);
        } catch (PositionInvalideException e) {
            // Ceci n'est jamais sensé se produire puisqu'on se déplace dans une position valide
            e.printStackTrace();
        }
    }
}
