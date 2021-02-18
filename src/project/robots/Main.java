package project.robots;

public class Main {

    static void test1() throws PositionInvalideException {
        Monde monde = new Monde(5, 10);
        monde.putDirtyPaper(1, 0);
        monde.putDirtyPaper(4, 9);
        System.out.println(monde.countDirtyPapers());
        monde.printMonde();
    }

    static void testPollueurDroit() throws PositionInvalideException {
        Monde monde = new Monde(5, 10);
        Robot.DEBUG = true;//affiche les infos sue les déplacements des robots
        RobotPollueurToutDroit pollueurDroit = new RobotPollueurToutDroit(monde, 1);
        pollueurDroit.parcourir();
        System.out.println(monde.countDirtyPapers());

        monde.printMonde();
    }


    static void testPollueurLibre() {
        Monde monde = new Monde(5, 10);
        Robot.DEBUG = true;//affiche les infos sue les déplacements des robots
        RobotPollueurLibre pollueurLibre = new RobotPollueurLibre(monde);
        for (int i = 0; i < 5; i++) {
            pollueurLibre.parcourir();
        }
        monde.printMonde();
    }

    static void testPollueurSauteur() throws Exception {
        Monde monde = new Monde(5, 10);
        Robot.DEBUG = true;//affiche les infos sue les déplacements des robots
        RobotPollueurSauteur pollueurSauteur = new RobotPollueurSauteur(monde, 1, 0, 2);
        for (int i = 0; i < 5; i++) {
            pollueurSauteur.parcourir();
        }
        monde.printMonde();
    }

    public static void main(String[] args) throws Exception {
        Monde monde = new Monde(5, 10);
        monde.putDirtyPaper(1, 0);
        Robot.DEBUG = true;//affiche les infos sue les déplacements des robots
        RobotNettoyeurLibre robotNettoyeurLibre=new RobotNettoyeurLibre(monde,0,0);
        for (int i = 0; i < 5; i++) {
            robotNettoyeurLibre.nettoyer();
        }
        monde.printMonde();

        //test1();
        //testPollueurDroit();
        //testPollueurLibre();
        //testPollueurSauteur();
    }
}