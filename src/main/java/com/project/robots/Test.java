package com.project.robots;

public class Test {

    static void test1() throws PositionInvalideException {
        Monde monde = new Monde(5, 10);
        monde.putDirtyPaper(1, 0);
        monde.putDirtyPaper(3, 9);
        System.out.println(monde.countDirtyPapers());
        //monde.printMonde();
    }

    static void testPollueurDroit() throws PositionInvalideException {
        Monde monde = new Monde(5, 10);
        Robot.DEBUG = true;//affiche les infos sue les déplacements des robots
        RobotPollueurToutDroit pollueurDroit = new RobotPollueurToutDroit(monde, 1);
        pollueurDroit.parcourir();
        System.out.println(monde.countDirtyPapers());

        //monde.printMonde();
    }


    static void testPollueurLibre() throws InterruptedException {
        Monde monde = new Monde(5, 10);
        Robot.DEBUG = true;//affiche les infos sue les déplacements des robots
        RobotPollueurLibre pollueurLibre = new RobotPollueurLibre(monde);
        for (int i = 0; i < 10; i++) {
            pollueurLibre.parcourir();
            //monde.printMonde();
            Thread.sleep(1000);
        }
    }

    static void testPollueurSauteur() throws Exception {
        Monde monde = new Monde(10, 15);
        Robot.DEBUG = true;//affiche les infos sue les déplacements des robots
        RobotPollueurSauteur pollueurSauteur = new RobotPollueurSauteur(monde, 1, 0);
        for (int i = 0; i < 30; i++) {
            pollueurSauteur.parcourir();
            //monde.printMonde();

            Thread.sleep(1000);
        }
    }

    static void testettoyeurLibre() throws PositionInvalideException {
        Monde monde = new Monde(5, 10);
        monde.putDirtyPaper(1, 0);
        Robot.DEBUG = true;//affiche les infos sue les déplacements des robots
        RobotNettoyeurLibre robotNettoyeurLibre = new RobotNettoyeurLibre(monde, 0, 0);
        for (int i = 0; i < 5; i++) {
            robotNettoyeurLibre.nettoyer();
        }
        //monde.printMonde();
    }

    public static void main(String[] args) throws Exception {

        test1();
        //testPollueurDroit();
        //testPollueurLibre();
        //testPollueurSauteur();
        //testettoyeurLibre();

    }
}