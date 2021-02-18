package project.robots;

public class RobotNettoyeurStandard extends RobotNettoyeur {

    public RobotNettoyeurStandard(Monde m) {
        super(m);
    }

    @Override
    public void nettoyer() throws PositionInvalideException {
        for (int i = 0; i < monde.getNbL(); i++) {
            for (int j = 0; j < monde.getNbC(); j++) {
                //TODO cannot jump to other side => explore continously: only adjacent positions
                moveToPosition(i, j);
                monde.cleanDirtyPaper(i, j);
            }
        }
    }
}
