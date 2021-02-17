package project.robots;
public abstract class RobotNettoyeur extends Robot{

    public RobotNettoyeur(Monde m, int x, int y) throws Exception {
        super(m, x, y);
    }

    public RobotNettoyeur(Monde m) {
        super(m);
    }

    public void nettoyer() throws Exception {
        throw new Exception("Ne sait pas encore nettoyer");
    }
}
