package project.robots;

public class RobotNettoyeurStandard extends RobotNettoyeur {

    public RobotNettoyeurStandard(Monde m) {
        super(m);
    }

    @Override
    public void nettoyer() throws PositionInvalideException {
        for (int i = 0; i < monde.nbL; i++) {
            for (int j = 0; j < monde.nbC; j++) {
                //TODO cannot jump to other side => explore continously like a robot :)
                moveToPosition(i, j);
                monde.cleanDirtyPaper(i, j);
            }
        }
    }
}
