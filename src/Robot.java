public abstract class Robot {
    public int posY;
    public int posX;
    public Monde monde;

    public Robot(int x, int y){
        posX=x;
        posY=y;
    }

    public Robot(){
        assert monde != null;
        posX = (int) ( monde.nbC* Math.random());
        posY = (int)  ( monde.nbL* Math.random());
    }

    public void moveToPosition(int i, int j) throws Exception {
        if (i < 0 || j < 0 || j > monde.nbL || j > monde.nbC) {
            throw new Exception("Case pas dans monde");
        }
        posX=i;
        posY=j;
    }

    public void parcourir() throws Exception {
        throw new Exception("Ne sait pas encore se deplacer");
    }
}
