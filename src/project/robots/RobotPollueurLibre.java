package project.robots;

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
            LinkedList<Couple> positionsValides = this.getListeDeplacements(1);
            Couple positionFinale = Couple.getRandomElement(positionsValides);
            moveToPosition(positionFinale.getA(), positionFinale.getB());
            monde.putDirtyPaper(positionFinale.getA(), positionFinale.getB());
        } catch (PositionInvalideException e) {
            // Ceci n'est jamais sensé se produire puisqu'on se déplace dans une position valide
            e.printStackTrace();
        }
    }
}
