public class RobotNettoyeur extends Robot{

    public RobotNettoyeur(Monde m) throws Exception {
        super(m, 0, 0);
    }

    public void nettoyer() throws Exception {
        for (int i = 0; i < monde.nbL; i++) {
            for (int j = 0; j < monde.nbC; j++) {
                //TODO cannot jump to other side => explore continously like a robot :)
                moveToPosition(i,j);
                monde.cleanDirtyPaper(i,j);
            }
        }
    }
}
