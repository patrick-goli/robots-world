package project.robots;

import java.util.LinkedList;

public class RobotPollueurSauteur extends Robot {
    private final int pasJump; //pas du jump: nombre de case

    public RobotPollueurSauteur(Monde m, int x, int y, int pas) throws Exception {
        super(m, x, y);
        // s'assurer qu'on peut effectivement sauter
        if (pas <= 0 || pas >= Math.max(monde.getNbC(), monde.getNbL()))
            throw new Exception("Ne peut pas sauter: pas " + pas + " trop grand");
        pasJump = pas;
    }

    public RobotPollueurSauteur(Monde m, int pas) throws Exception {
        super(m);
        if (pas <= 0 || pas >= Math.max(monde.getNbC(), monde.getNbL()))
            throw new Exception("Ne peut pas sauter: pas " + pas + " trop grand");
        pasJump = pas;
    }


    @Override
    public void parcourir() throws Exception {

        try {
            // pollue d'abord notre position initiale
            monde.putDirtyPaper(posX, posY);
            // on choisi une case au hazard et on se déplace
            LinkedList<Couple> positionsValides = this.listeDeplacements(pasJump);
            if (positionsValides.size() == 0) {
                throw new Exception("Ne peut pas sauter: pas " + pasJump + " trop grand");
            }
            Couple positionFinale = Couple.getRandomElement(positionsValides);
            moveToPosition(positionFinale.getA(), positionFinale.getB());
            monde.putDirtyPaper(positionFinale.getA(), positionFinale.getB());
        } catch (PositionInvalideException e) {
            // Ceci n'est jamais sensé se produire puisqu'on se déplace dans une position valide
            e.printStackTrace();
        }
    }
}
