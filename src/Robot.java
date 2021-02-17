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
        posX = (int) ( monde.nbL * Math.random());
        posY = (int)  ( monde.nbC * Math.random());
        System.out.println(this.toString()+" created at coodinates (" + posX + "," + posY +")");
    }

    public void moveToPosition(int x, int y) throws Exception {
        if (!monde.positionIsValid(x,y)) {
            throw new Exception("Case (" + x + "," + y +") pas dans monde");
        }
        posX=x;
        posY=y;
        System.out.println(this.toString()+ " moving to coodinates (" + x + "," + y +")");
    }

    public void parcourir() throws Exception {
        throw new Exception("Ne sait pas encore se deplacer");
    }
}
