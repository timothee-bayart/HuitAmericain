package bayart.ayouaz.huit.americain.view;


import bayart.ayouaz.huit.americain.model.*;
import bayart.ayouaz.huit.americain.controller.PartieGraphique;
import bayart.ayouaz.huit.americain.model.enums.Couleur;
import bayart.ayouaz.huit.americain.model.enums.Motif;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ViewGraphic extends JFrame implements Observer, IHM {
    private JButton[] menu;
    private JComboBox<String> comboVariantes;
    private JLabel label;
    private JTextArea pseudo;
    private JPanel panel;
    private JButton valider;
    private GridBagConstraints constraint;
    private PartieGraphique controleur;
    
    public ViewGraphic(PartieGraphique p){
        controleur=p;
        label = new JLabel();
        valider = new JButton("Valider");
        pseudo = new JTextArea();
        pseudo.getDocument().putProperty("filterNewlines", Boolean.TRUE);
//        pseudo.setS(new Dimension(175, 15));

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        this.setTitle("Huit Americain");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(900, 600));
        this.setVisible(true);

        controleur.setFenetre(this);
    }

    @Override
    public void afficherMessage(String message){
        label.setText(message);
    }

    public void afficherPseudoDepart(){
        pseudo.setText("");
        label.setText("Veuillez saisir votre nom (alphabétique) de joueur");
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        constraint = new GridBagConstraints();
        constraint.insets = new Insets(0,0,25,0);

        panel.add(label, constraint);

        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets(0,0,25,0);
        constraint.gridy=1;
        panel.add(pseudo, constraint);

        constraint.fill = GridBagConstraints.NONE;
        constraint.insets = new Insets(0,0,0,0);
        constraint.gridy=2;
        panel.add(valider, constraint);

        this.setContentPane(panel);
        this.pack();

        valider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                controleur.ajouterJoueur(pseudo.getText());
            }
        });
    }

    public void afficherMenuDepart(){
        menu = new JButton[]{
            new JButton("Démarrer la partie"),
            new JButton("Ajouter un joueur"),
            new JButton("Choisir la variante")
        };

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(0,0,20,0);

        panel.add(label, constraint);

        constraint.fill = GridBagConstraints.NONE;
        constraint.insets = new Insets(5,0,5,0);

        for(JButton btn : menu){
            constraint.gridx = 0;
            constraint.gridy++;
            btn.setPreferredSize(new Dimension(150, 25));
            panel.add(btn, constraint);
        }
        this.setContentPane(panel);
        this.pack();
        menu[0].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                controleur.demarrer();
            }
        });
        menu[1].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                controleur.ajouterIA();
            }
        });
        menu[2].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                controleur.choisirRegle();
            }
        });
    }

    public void afficherVariantesDepart(Regle[] variantes, Regle varianteSelected){
        label.setText("Veuillez choisir la variante");
        comboVariantes = new JComboBox<String>();
        for(int i=0; i<variantes.length;++i){
            comboVariantes.addItem(variantes[i].toString());

            if(variantes[i] == varianteSelected){
                comboVariantes.setSelectedIndex(i);
            }
        }

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        constraint = new GridBagConstraints();
        constraint.insets = new Insets(0,0,25,0);

        panel.add(label, constraint);

        constraint.fill = GridBagConstraints.NONE;
        constraint.insets = new Insets(0,0,25,0);
        constraint.gridy=1;
        comboVariantes.setPreferredSize(new Dimension(150, 25));
        panel.add(comboVariantes, constraint);

        constraint.gridy=2;
        constraint.insets = new Insets(0,0,0,0);
        panel.add(valider, constraint);

        this.setContentPane(panel);
        this.pack();
        valider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                controleur.choisirVariante(variantes[comboVariantes.getSelectedIndex()]);
            }
        });
    }

    public void afficherPlateau(LinkedHashSet<Joueur> joueurs, Carte defausse){
        ArrayList<JButton> cartesJoueurBtns = new ArrayList<JButton>();
        ArrayList<JLabel> mainsIaLbl = new ArrayList<JLabel>(); // Attention pas arraylist !!! et iterator !!

        JButton piocheBtn = this.createImageButton("back.png");
        JLabel defausseBtn = this.createImageJLabel(defausse);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;


        for(Joueur joueur : joueurs){
            if(joueur instanceof Ia){
                JLabel label = this.createImageJLabelWithText("back.png",
                        "<html><center>"+joueur+"<br />("+joueur.getMain().getNombreDeCartes()+" cartes)</center></html>",
                        JLabel.CENTER);
                mainsIaLbl.add(label);
            } else {
                GroupeDeCarte main = joueur.getMain();
                for(int i=0; i<main.getNombreDeCartes(); ++i){
                    JButton button = this.createImageButton(main.getCarte(i));
                    button.addActionListener(new CarteBtnActionListener(main.getCarte(i)));
                    cartesJoueurBtns.add(button);
                }
            }
        }
        constraint.insets = new Insets(0,0,0,0);
        constraint.gridx=0;
        constraint.gridy=0;
        //on affiche les mains des IA en ligne 1
        JPanel ligne1 = new JPanel();
        for(JLabel lbl : mainsIaLbl){
            ligne1.add(lbl);
        }
        panel.add(ligne1, constraint);

        //on affiche la defausse et la pioche en ligne 2
        JPanel ligne2 = new JPanel();
        constraint.insets = new Insets(50,0,0,0);
        constraint.gridy=1;
        ligne2.add(defausseBtn);
        ligne2.add(piocheBtn);
        panel.add(ligne2, constraint);

        //on affiche le texte d'information en ligne 3
        JPanel ligne3 = new JPanel();
        constraint.insets = new Insets(0,0,50,0);
        constraint.gridy=2;
        ligne3.add(this.label);
        panel.add(ligne3, constraint);

        //on affiche la main du joueur en ligne 4
        JPanel ligne4 = new JPanel();
        constraint.insets = new Insets(0,0,0,0);
        constraint.gridy=3;
        for(JButton btn : cartesJoueurBtns){
            ligne4.add(btn);
        }
        panel.add(ligne4, constraint);

        piocheBtn.addActionListener(new CarteBtnActionListener(null));
        this.setContentPane(panel);
        this.pack();
    }





    private JLabel createImageJLabelWithText(String imageFileName, String text, int position){
        Image img = null;
        try {
            img = ImageIO.read(new FileInputStream("res/"+imageFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel(text, new ImageIcon(img), position);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        return label;
    }


    private JLabel createImageJLabel(String imageFileName){
        JLabel label = new JLabel();
        try {
            Image img = ImageIO.read(new FileInputStream("res/"+imageFileName));
            label.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }

    private JLabel createImageJLabel(Carte carte){
        JLabel label = new JLabel();
        try {
            Image img = ImageIO.read(new FileInputStream("res/"+carte.getImageFileName()));
            label.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }

    private JButton createImageButton(String imageFileName){
        JButton button = new JButton();
        try {
            Image img = ImageIO.read(new FileInputStream("res/"+imageFileName));
            button.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }

    private JButton createImageButton(Carte carte){
        JButton button = new JButton();
        try {
            Image img = ImageIO.read(new FileInputStream("res/"+carte.getImageFileName()));
            button.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }






    private void afficherMainJoueur(){

    }

    private void afficherMainIas(){

    }

    private class CarteBtnActionListener implements ActionListener{
        private Carte carte;

        public CarteBtnActionListener(Carte carte){
            this.carte = carte;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            controleur.carteSelectionnee(carte);
        }
    };

    @Override
    public void update(Observable o, Object j) {
        if( o instanceof Joueur && j instanceof String && j == "ajouteJoueur"/* && j == null*/){
//            if(joueurs.isEmpty()){
//                joueurNonIA=((Joueur)o).toString();
//            }
//            joueurs.add((Joueur)o);
            this.afficherMenuDepart();
        }
        if( o instanceof Joueur && j instanceof String && j == "piocher"){
            this.afficherPlateau(this.controleur.getJoueurs(), this.controleur.getCarteDefausse());
        }
        if( o instanceof Carte && j instanceof String && j == "setDefausse"){
            this.afficherPlateau(this.controleur.getJoueurs(), this.controleur.getCarteDefausse());
        }
    }


    @Override
    public void piocherObligatoire(int nbCartes, Joueur QuiPioche) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sauterTour(Joueur quiVaEtreSauter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changerProchaineCouleur(Couleur[] couleurs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ilFautJouer(Couleur couleur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rejouer(Joueur quiRejoue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fairePiocherJoueur(Joueur[] quiVaPiocher) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changerSens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changerProchainMotif(Motif[] motifs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void jokerPrendApparence(Carte carte) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
