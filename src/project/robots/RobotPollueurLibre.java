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

    @Override
    public void parcourir() throws PositionInvalideException {
        LinkedList<Integer> positionsX = new LinkedList<>();
        LinkedList<Integer> positionsY = new LinkedList<>();
        //TODO see if we can move around: up to 8 possible moves
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (monde.positionIsValid(posX + i, posY + j)) {
                    positionsX.add(posX + i);
                    positionsY.add(posY + j);
                }
            }
        }
        //poluer d'abord la case de depart
        monde.putDirtyPaper(posX, posY);

        int jumpToX = getRandomElement(positionsX);
        int jumpToY = getRandomElement(positionsY);
        moveToPosition(jumpToX, jumpToY);
        monde.putDirtyPaper(jumpToX, jumpToY);

    }
}
