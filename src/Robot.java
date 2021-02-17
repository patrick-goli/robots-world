public abstract class Robot {
    public int posY;
    public int posX;
    public Monde monde;

    public Robot(Monde m, int x, int y) throws Exception {
        monde=m;
        if(!monde.positionIsValid(x,y))
            throw new Exception("Case pas dans monde");
        posX=x;
        posY=y;
    }

    public Robot(Monde m){
        monde=m;
        posX = (int) ( monde.nbC * Math.random());
        posY = (int)  ( monde.nbL * Math.random());
    }

    public void moveToPosition(int x, int y) throws Exception {
        if (!monde.positionIsValid(x,y)) {
            throw new Exception("Case pas dans monde");
        }
        posX=x;
        posY=y;
    }

    public void parcourir() throws Exception {
        throw new Exception("Ne sait pas encore se deplacer");
    }
}
