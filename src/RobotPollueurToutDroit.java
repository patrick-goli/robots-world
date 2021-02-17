public class RobotPollueurToutDroit extends Robot{
    int colDepart; //TODO remove this ?

    public RobotPollueurToutDroit(int depart){
        super(0,depart);
        colDepart=depart;
    }

    public void parcourir() throws Exception {
        for (int j = posY; j < monde.nbC; j++) {
            moveToPosition(posX,j);
            monde.putDirtyPaper(posX,j);

        }
    }
}
