package project.robots;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RobotPollueurLibre extends Robot {

    public RobotPollueurLibre(Monde m) {
        super(m);
    }

    // Function select an element base on index
    private static int getRandomElement(List<Integer> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    /**
     * Se déplace et polue aléatoirement les cases adjacente
     */
    @Override
    public void parcourir() {
        //coordonnées de positions adjacentes
        LinkedList<Integer> positionsValidesX = new LinkedList<>();
        LinkedList<Integer> positionsValidesY = new LinkedList<>();
        //On calcule les positions de mouvement : max 8 cases possibles
        // une case adjacente a {-1, 0, +1} en plus des coordonnées
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    //correspond à faire du sur-place
                    continue;
                }
                if (monde.positionIsValid(posX + i, posY + j)) {
                    //on s'assure de ne pas sortir du cadre(monde)
                    positionsValidesX.add(posX + i);
                    positionsValidesY.add(posY + j);
                }
            }
        }
        //poluer d'abord la case de depart
        try {
            monde.putDirtyPaper(posX, posY);
            // on choisi une case au hazard et on se déplace
            int jumpToX = getRandomElement(positionsValidesX);
            int jumpToY = getRandomElement(positionsValidesY);
            moveToPosition(jumpToX, jumpToY);
            monde.putDirtyPaper(jumpToX, jumpToY);
        } catch (PositionInvalideException e) {
            // Ceci n'est jamais sensé se produire puisqu'on se déplace dans une position valide
            e.printStackTrace();
        }
    }
}
