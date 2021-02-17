public class Main {

         public static void main(String[] args) throws Exception {
             Monde monde=new Monde(10,5);
             //monde.putDirtyPaper(9,1);
             //RobotPollueurToutDroit robotDroit=new RobotPollueurToutDroit(monde, 4);
             //RobotPollueurLibre robotLibre = new RobotPollueurLibre(monde);

             //robotLibre.parcourir();
             //robotDroit.parcourir();
             monde.printMatrix();
        }
    }