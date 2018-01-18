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
/**
 * Cette classe represente la vue graphique avec toutes les fonctions necessaire
 * à l'afficage
 * @see IHM
 */
public class ViewGraphic extends JFrame implements Observer, IHM {
    private JButton[] menu;
    private JComboBox<String> comboVariantes;
    private JLabel label;
    private JTextArea pseudo;
    private JPanel panel;
    private GridBagConstraints constraint;
    private PartieGraphique controleur;
    
    public ViewGraphic(PartieGraphique p){
        controleur=p;
        label = new JLabel();
        pseudo = new JTextArea();
        pseudo.getDocument().putProperty("filterNewlines", Boolean.TRUE);

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

    @Override
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
        JButton valider = new JButton("Valider");
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

    @Override
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

        if(this.controleur.isPartieSauvegardee()){
            JButton btn = new JButton("Charger la sauvegarde");
            constraint.gridx = 0;
            constraint.gridy++;
            btn.setPreferredSize(new Dimension(150, 25));
            panel.add(btn, constraint);

            btn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0){
                    controleur.chargerPartieSauvegardee();
                }
            });

        }
    }

    @Override
    public void afficherPartieTerminee(Joueur[] joueurs) {
        this.setJMenuBar(null);
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(0,0,0,0);

        String message = "<html><center>";
        message += joueurs[0]+" REMPORTE LA PARTIE !<br />";
        message += "-------------------------------------------------------------<br />";

        for(int i=1; i<joueurs.length; i++){
            message += joueurs[i]+" est "+(i+1)+"ème<br />";
        }
        message += "</center></html>";
        afficherMessage(message);
        panel.add(label, constraint);
        this.setContentPane(panel);
        this.pack();
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
        JButton valider = new JButton("Valider");
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
        JMenuBar menuBar = new JMenuBar();
        JMenuItem item1 = new JMenuItem("Sauvegarder & quitter");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = new JOptionPane("Sauvegarde effectuée.\nReprenez votre partie plus tard !\n\nA bientôt...");
                pane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = pane.createDialog(null, "Partie sauvegardée");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
                controleur.sauvegarderPartie();
                controleur.quitterJeu();
            }
        });
        menuBar.add(item1);
        this.setJMenuBar(menuBar);


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
        if( o instanceof Joueur && j instanceof String && j == "ajouteJoueur"){
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
        String consigne = "<html><center>";
        consigne += "-> "+QuiPioche + " pioche " + nbCartes + "carte(s).";
        consigne += "</center></html>";
        this.afficherMessage(consigne);
    }

    @Override
    public void sauterTour(Joueur quiVaEtreSauter) {
        String consigne = "<html><center>";
        consigne += "-> " + quiVaEtreSauter + " saute son tour";
        consigne += "</center></html>";
        this.afficherMessage(consigne);
    }

    @Override
    public int changerProchaineCouleur(Couleur[] couleurs) {
        ChoixCouleur cc = new ChoixCouleur(this);
        return cc.showDialog();
    }

    @Override
    public void ilFautJouer(Couleur couleur) {
        String consigne = "<html><center>";
        consigne += getDernierMessage()+"<br />-> Il faut jouer un " + couleur;
        consigne += "</center></html>";
        this.afficherMessage(consigne);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ilFautJouer(Carte carte) {
        String consigne = "<html><center>";
        consigne += getDernierMessage()+"<br />-> Il faut jouer un(e) " + carte.getMotif() + " ou un " + carte.getCouleur();
        consigne += "</center></html>";
        this.afficherMessage(consigne);
        System.out.println("Il faut jouer un(e) " + carte.getMotif() + " ou un " + carte.getCouleur());
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rejouer(Joueur quiRejoue) {
        String consigne = "<html><center>";
        consigne += quiRejoue + " va rejouer";
        consigne += "</center></html>";
        this.afficherMessage(consigne);
    }

    @Override
    public int fairePiocherJoueur(Joueur[] quiVaPiocher) {
        ChoixJoueurQuiPioche cjqp = new ChoixJoueurQuiPioche(this, quiVaPiocher);
        return cjqp.showDialog();
    }

    @Override
    public void changerSens() {
        String consigne = "<html><center>";
        consigne += "Le sens est inversé";
        consigne += "</center></html>";
        this.afficherMessage(consigne);
    }

    @Override
    public int changerProchainMotif(Motif[] motifs) {
        ChoixMotif cm = new ChoixMotif(this);
        return cm.showDialog();
    }

    @Override
    public void jokerPrendApparence(Carte carte) {
        String consigne = "<html><center>";
        consigne += "Le joker prends l'apparence de la carte : " + carte.toString();
        consigne += "</center></html>";
        this.afficherMessage(consigne);
    }

    @Override
    public String getDernierMessage() {
        return this.label.getText();
    }

}
