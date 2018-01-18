/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.view;

import bayart.ayouaz.huit.americain.model.enums.Couleur;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * Cette classe est un popup designé pour s'afficher avec le mode graphique afin
 * de choisir la prochaine couleur à jouer
 */
public class ChoixCouleur extends JDialog implements ActionListener{
    private JButton carre, coeur, pic, trefle;
    private int retour;
    /**
     * Constructeur
     * @param owner necessaire pour dire que la fenetre est parent de ce popup 
     */
    public ChoixCouleur(ViewGraphic owner){
        super(owner,true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        carre = new JButton(Couleur.CARREAU.toString());
        coeur = new JButton(Couleur.COEUR.toString());
        pic = new JButton(Couleur.PIC.toString());
        trefle = new JButton(Couleur.TREFLE.toString());

        carre.addActionListener(this);
        coeur.addActionListener(this);
        pic.addActionListener(this);
        trefle.addActionListener(this);
        init();
    }
    
    /**
     * Cette fonction place les boutons et autres labels graphiques
     */
    private void init(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;
        cont.gridx=0;
        cont.gridy=0;
        panel.add(carre,cont);
        cont.gridx++;
        panel.add(coeur,cont);
        cont.gridy++;
        panel.add(trefle,cont);
        cont.gridx--;
        panel.add(pic,cont);
        this.setContentPane(panel);
        this.pack();
    }
    /**
     * Fonction qui gere les cliques sur les boutons
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == carre){
            retour = Couleur.CARREAU.getNumero();
            this.setVisible(false);
        }
        if(e.getSource() == trefle){
            retour = Couleur.TREFLE.getNumero();
            this.setVisible(false);
        }
        if(e.getSource() == coeur){
            retour = Couleur.COEUR.getNumero();
            this.setVisible(false);
        }
        if(e.getSource() == pic){
            retour = Couleur.PIC.getNumero();
            this.setVisible(false);
        }
    }
    /**
     * Fonction appelé par la fenetre afin d'ouvrir la popup
     * @return la valeur de la couleur choisie
     */
    public int showDialog(){
        this.setVisible(true);
        return retour;
    }
}
