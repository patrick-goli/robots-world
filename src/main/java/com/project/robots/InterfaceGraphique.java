package com.project.robots;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGraphique extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JButton nbPapiersSales = new JButton("Nombre de papiers sales: 0");
    private final Monde monde = new Monde(10, 10);
    private final JPanel carte = new CarteDuMonde(monde.getNbL(), monde.getNbC());


    public InterfaceGraphique(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 200 * monde.getNbC(), 200 * monde.getNbL());

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        //centre
        contentPane.add("Center", carte);

        //sud
        JButton PS1 = new JButton();
        JButton PS2 = new JButton("Case sale");
        JButton PS3 = new JButton();
        PS3.setBackground(new Color(173, 216, 230));
        JButton PS4 = new JButton("Case propre");
        PS1.setBackground(new Color(0, 0, 0));
        JPanel PS = new JPanel(new GridLayout(1, 4));
        PS.add(PS1);
        PS.add(PS2);
        PS.add(PS3);
        PS.add(PS4);
        contentPane.add("South", PS);

        //nord

        JPanel PN1 = new JPanel(new GridLayout(1, 5));
        JPanel PN2 = new JPanel(new BorderLayout());
        JButton N1 = new JButton("Réinitialiser le Monde");
        N1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent RobotNettoyeur) {
                reinitialiserMonde();
            }
        });

        JButton N2 = new JButton("Nettoyer");
        JButton N3 = new JButton("Polluer");
        JButton N4 = new JButton("Quitter");
        N4.setBorder(new EmptyBorder(50, 50, 50, 50));
        N4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                e.getSource();
                System.exit(0);
            }
        });

        PN1.add(N1);
        PN1.add(N2);
        PN1.add(N3);
        PN1.add(N4);
        JPanel PN = new JPanel(new BorderLayout());
        PN.add("North", PN1);
        PN2.add(nbPapiersSales);
        PN.add("South", PN2);
        contentPane.add("North", PN);

        //ouest
        JButton W1 = new JButton("Pollueur Tout Droit");
        W1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    RobotPollueurToutDroit robot = new RobotPollueurToutDroit(monde, (int) (monde.getNbC() * Math.random()));
                    robot.parcourir();
                } catch (PositionInvalideException positionInvalideException) {
                    positionInvalideException.printStackTrace();
                }
                miseAJourMonde();
            }
        });
        JButton W2 = new JButton("Pollueur Sauteur");
        JButton W3 = new JButton("Pollueur Libre");
        JPanel PW = new JPanel(new GridLayout(3, 1));
        PW.add(W1);
        PW.add(W2);
        PW.add(W3);
        contentPane.add("West", PW);

        //est
        JButton E1 = new JButton("Nettoyeur Libre");
        JButton E2 = new JButton("Nettoyeur Standard");
        JButton E3 = new JButton("Nettoyeur Smart");
        JPanel PE = new JPanel(new GridLayout(3, 1));
        PE.add(E1);
        PE.add(E2);
        PE.add(E3);
        contentPane.add("East", PE);

    }

    public static void main(String[] args) {
        Robot.DEBUG = true;
        InterfaceGraphique interfaceGraphique = new InterfaceGraphique("Le Monde Des Robots \uD83E\uDD16");
        interfaceGraphique.setVisible(true);
    }

    public void miseAJourMonde() {
        nbPapiersSales.setText("Nombre de papiers sales: " + monde.countDirtyPapers());
        carte.updateUI();
    }

    public void reinitialiserMonde() {
        monde.cleanAllDirtyPapers();
        nbPapiersSales.setText("Nombre de papiers sales: 0");
        carte.updateUI();
    }

    private class CarteDuMonde extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        final int nbLignes;
        final int nbColonnes;

        public CarteDuMonde(int lignes, int colonnes) {
            nbLignes = lignes;
            nbColonnes = colonnes;
        }

        /**
         * Dessine le monde
         * Les indices (i,j) de ma matrice correspondent aux coordonnées (hauteur, largeur) sur le plan
         * Le robot en position (1,0) est en haut à gauche sur le graphique (afficher le monde pour voir)
         *
         * @param g objet Graphics
         */
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int sizeX = getWidth() / nbColonnes;// nombre de cases sur la hauteur
            int sizeY = getHeight() / nbLignes; //nombre de case sur la largeur

            int x = 0;
            for (int horz = 0; horz < nbColonnes; horz++) {
                int y = 0;
                for (int vert = 0; vert < nbLignes; vert++) {
                    try {
                        if (monde.containDirtyPaper(y / sizeY, x / sizeX)) {
                            //couleur noire si papier sale présent
                            g.setColor(new Color(0, 0, 0));
                        } else {
                            g.setColor(new Color(173, 216, 230));
                        }

                        g.fillRoundRect(x, y, sizeX, sizeY, 20, 20);
                        g.setColor(new Color(0, 0, 150));
                        //les bordures en blue
                        g.drawRoundRect(x, y, sizeX, sizeY, 20, 20);
                    } catch (PositionInvalideException e) {
                        e.printStackTrace();
                        System.exit(1);
                    } catch (ArithmeticException e) {
                        //e.printStackTrace();
                        //ignore cette erreur qui survient lors de la réduction de la taille de la fenetre du "Monde"
                        //qui vaut alors 0 ( sizeX=0 ou sizeY=0) division par zéro
                        continue;
                    }
                    y += sizeY;
                }
                x += sizeX;
            }
            g2d.dispose();
        }
    }
}