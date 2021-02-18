package project.robots;

public abstract class Robot {
    public static boolean DEBUG = false; //Pour afficher ce qui se passe en arrière-plan
    protected final Monde monde;
    protected int posY;
    protected int posX;

    public Robot(Monde m, int x, int y) throws PositionInvalideException {
        monde = m;
        monde.validatePosition(x, y);
        posX = x;
        posY = y;
    }

    /**
     * Cree un robot positioné aléatoirement
     *
     * @param m Le monde
     */
    public Robot(Monde m) {
        monde = m;
        posX = (int) (monde.getNbL() * Math.random());
        posY = (int) (monde.getNbC() * Math.random());
        if (DEBUG)
            System.out.println(this.toString() + " créé aux coordonnées (" + posX + "," + posY + ")");
    }

    /**
     * Se déplacer à la position souhaitée
     *
     * @param x coordonée
     * @param y coordonee
     * @throws PositionInvalideException si la position est invalide
     */
    public void moveToPosition(int x, int y) throws PositionInvalideException {
        monde.validatePosition(x, y);
        posX = x;
        posY = y;
        if (DEBUG)
            System.out.println(this.toString() + " se déplace aux coordonnées (" + x + "," + y + ")");
    }

    /**
     * Se déplacer dans le monde des robots
     *
     * @throws Exception Pas encore implémentée
     */
    public void parcourir() throws Exception {
        throw new Exception("Ne sait pas encore se deplacer");
    }
}
