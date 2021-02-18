package project.robots;

public class RobotPollueurToutDroit extends Robot {
    int colDepart;

    public RobotPollueurToutDroit(Monde m, int colonneDepart) throws PositionInvalideException {
        super(m, 0, colonneDepart);
        colDepart = colonneDepart;
    }

    public void parcourir() throws PositionInvalideException {
        //poluer d'abord la case de depart
        monde.putDirtyPaper(0, colDepart);

        for (int i = 1; i < monde.nbL; i++) {
            moveToPosition(i, colDepart);
            monde.putDirtyPaper(i, colDepart);
        }
    }
}
