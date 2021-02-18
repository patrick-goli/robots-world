package project.robots;

public class Main {

    public static void main(String[] args) throws Exception {
        Monde monde = new Monde(5, 10);
        monde.putDirtyPaper(1, 0);
        Robot.DEBUG = true;//affiche les infos sue les déplacements des robots
        RobotPollueurToutDroit pollueurDroit = new RobotPollueurToutDroit(monde, 1);
        RobotPollueurLibre pollueurLibre = new RobotPollueurLibre(monde);
        //RobotPollueurSauteur pollueurSauteur = new RobotPollueurSauteur(monde, 0, 0);

        System.out.println(monde.countDirtyPapers());
        for (int i = 0; i < 5; i++) {
            pollueurLibre.parcourir();
        }
        System.out.println(monde.countDirtyPapers());
        pollueurDroit.parcourir();
        //pollueurSauteur.parcourir();

        System.out.println(monde.countDirtyPapers());
        monde.printMonde();
//        Thread.sleep(2000);
//        RobotNettoyeurStandard nettoyeurStandard = new RobotNettoyeurStandard(monde);
//        nettoyeurStandard.nettoyer();
//        monde.printMonde();

    }
}