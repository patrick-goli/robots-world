package project.robots;

public class RobotNettoyeurSmart extends RobotNettoyeur {
    public RobotNettoyeurSmart(Monde m, int x, int y) throws PositionInvalideException {
        super(m, x, y);
    }

    @Override
    public void nettoyer() throws Exception {
        super.nettoyer();
    }
}
