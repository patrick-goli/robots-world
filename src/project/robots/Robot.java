package project.robots;

public abstract class Robot {
    protected final Monde monde;
    protected int posY;
    protected int posX;

    public Robot(Monde m, int x, int y) throws PositionInvalideException {
        monde = m;
        if (!monde.positionIsValid(x, y))
            throw new PositionInvalideException("(" + x + "," + y + ")");
        posX = x;
        posY = y;
    }

    public Robot(Monde m) {
        monde = m;
        posX = (int) (monde.nbL * Math.random());
        posY = (int) (monde.nbC * Math.random());
        System.out.println(this.toString() + " created at coodinates (" + posX + "," + posY + ")");
    }

    public void moveToPosition(int x, int y) throws PositionInvalideException {
        if (!monde.positionIsValid(x, y)) {
            throw new PositionInvalideException("(" + x + "," + y + ")");
        }
        posX = x;
        posY = y;
        System.out.println(this.toString() + " moving to coodinates (" + x + "," + y + ")");
    }

    public void parcourir() throws Exception {
        throw new Exception("Ne sait pas encore se deplacer");
    }
}
