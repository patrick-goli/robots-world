package project.robots;

public class RobotNettoyeurStandard extends RobotNettoyeur {

    public RobotNettoyeurStandard(Monde m) {
        super(m);
    }

    @Override
    public void nettoyer() throws PositionInvalideException {
        for (int i = 0; i < monde.getNbL(); i++) {
            for (int j = 0; j < monde.getNbC(); j++) {
                //TODO ne peut pas sauter de l'autre coté du monde
                // explorer en continue: seuleument les positions adjacentes
                moveToPosition(i, j);
                monde.cleanDirtyPaper(i, j);
            }
        }
    }
}
