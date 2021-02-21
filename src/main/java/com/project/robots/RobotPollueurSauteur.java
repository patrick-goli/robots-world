package com.project.robots;

import java.awt.*;

public class RobotPollueurSauteur extends Robot {

    public RobotPollueurSauteur(Monde m, int x, int y) throws PositionInvalideException {
        super(m, x, y);
    }

    public RobotPollueurSauteur(Monde m, int pas) {
        super(m);
    }


    @Override
    public void parcourir() throws Exception {

        try {
            // pollue d'abord notre position initiale
            monde.putDirtyPaper(posX, posY);
            // on choisi une case au hasard et on se déplace
            //pas du jump: nombre de case
            int pasJump = (Math.random() > 0.5) ? 2 : 3;
            Point[] positionsValides = getListeDeplacements(pasJump);
            if (positionsValides.length == 0) {
                throw new Exception("Ne peut pas sauter: pas " + pasJump + " trop grand");
            }
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
