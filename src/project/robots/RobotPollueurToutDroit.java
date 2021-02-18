package project.robots;

public class RobotPollueurToutDroit extends Robot {
    final int colonneDepart; // égale à posY au final => posY est fixe

    public RobotPollueurToutDroit(Monde m, int colonneDepart) throws PositionInvalideException {
        super(m, 0, colonneDepart);
        this.colonneDepart = colonneDepart;
    }

    /**
     * Polue en ligne droite vers le sud toute les cases
     *
     * @throws PositionInvalideException si la position est invalide
     */
    @Override
    public void parcourir() throws PositionInvalideException {
        //poluer d'abord la case de depart
        monde.putDirtyPaper(0, colonneDepart);

        for (int i = 1; i < monde.getNbL(); i++) {
            moveToPosition(i, colonneDepart);
            monde.putDirtyPaper(i, colonneDepart);
        }
    }
}
