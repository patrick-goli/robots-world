package com.project.robots;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGraphique extends JFrame {
    private final Monde monde = new Monde(5, 7);

    public InterfaceGraphique(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100 * monde.getNbC(), 100 * monde.getNbL(), 1000 * monde.getNbC(), 1000 * monde.getNbL());

        JPanel PC = new CarteDuMonde(monde.getNbC(), monde.getNbL());
        JPanel PN = new JPanel(new BorderLayout());
        JPanel PN1 = new JPanel(new GridLayout(1, 5));
        JPanel PN2 = new JPanel(new BorderLayout());
        JPanel PS = new JPanel(new GridLayout());
        JPanel PW = new JPanel(new GridLayout(3, 1));
        JPanel PE = new JPanel(new GridLayout(3, 1));

        JPanel contentPane = new JPanel(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JButton N1 = new JButton("Réinitialiser le Monde");
        JButton N2 = new JButton("Nettoyer");
        JButton N3 = new JButton("Polluer");
        JButton N4 = new JButton("Quitter");

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
        PN.add("North", PN1);
        PN2.add(new JButton("Nombre de papiers gras: "));
        PN.add("South", PN2);
        contentPane.add("North", PN);

        JButton W1 = new JButton("Pollueur Droit");
        JButton W2 = new JButton("Pollueur Sauteur");
        JButton W3 = new JButton("Pollueur Libre");

        PW.add(W1);
        PW.add(W2);
        PW.add(W3);


        contentPane.add("West", PW);

        JButton E1 = new JButton("Nettoyeur Libre ");
        JButton E2 = new JButton("Nettoyeur Standard");
        JButton E3 = new JButton("Nettoyeur Smart");

        PE.add(E1);
        PE.add(E2);
        PE.add(E3);

        contentPane.add("East", PE);
        contentPane.add("Center", PC);

    }

    public static void main(String[] args) {
        InterfaceGraphique interfaceGraphique = new InterfaceGraphique("Le Monde Des Robots \uD83E\uDD16");
        interfaceGraphique.setVisible(true);
    }

    private class CarteDuMonde extends JPanel {

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
                            g.setColor(new Color(144, 238, 144));
                        }

                        g.fillRoundRect(x, y, sizeX, sizeY, 20, 20);
                        g.setColor(new Color(0, 0, 200));
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