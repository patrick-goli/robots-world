public class RobotPollueurToutDroit extends Robot{
    int colDepart; //TODO remove this ?

    public RobotPollueurToutDroit(Monde m, int depart) throws Exception {
        super(m, 0,depart);
        colDepart=depart;
    }

    public void parcourir() throws Exception {
        //poluer d'abord la case de depart
        monde.putDirtyPaper(0,colDepart);
        monde.printMatrix();
//        for (int i = 1; i < monde.nbL; i++) {
//            moveToPosition(i,colDepart);
//            monde.putDirtyPaper(i, colDepart);
//        }
    }
}
