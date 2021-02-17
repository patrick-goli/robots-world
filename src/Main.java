public class Main {

         public static void main(String[] args) throws Exception {
             Monde monde=new Monde(5,20);
             monde.putDirtyPaper(2,0);
             RobotPollueurToutDroit robotDroit=new RobotPollueurToutDroit(monde, 1);
             RobotPollueurLibre robotLibre = new RobotPollueurLibre(monde);
             for (int i = 0; i <5 ; i++) {
                 robotLibre.parcourir();
             }
             robotDroit.parcourir();
             monde.printMatrix();
        }
    }