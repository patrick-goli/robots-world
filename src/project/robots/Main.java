package project.robots;

public class Main {

    public static void main(String[] args) throws Exception {
        Monde monde = new Monde(5, 10);
        monde.putDirtyPaper(2, 0);
        RobotPollueurToutDroit robotDroit = new RobotPollueurToutDroit(monde, 1);
        RobotPollueurLibre robotLibre = new RobotPollueurLibre(monde);
        RobotPollueurSauteur sauteur = new RobotPollueurSauteur(monde, 0, 0);
        for (int i = 0; i < 5; i++) {
            robotLibre.parcourir();
        }
        robotDroit.parcourir();
        sauteur.parcourir();
        monde.printMonde();
//        Thread.sleep(2000);
//        RobotNettoyeur nettoyeur = new RobotNettoyeur(monde);
//        nettoyeur.nettoyer();
//        monde.printMonde();

    }
}