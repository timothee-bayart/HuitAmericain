/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.view;

import bayart.ayouaz.huit.americain.model.Joueur;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

/**
 * Cette classe est un popup designé pour s'afficher avec le mode graphique afin
 * de choisir le joueur qui doit jouer
 */
public class ChoixJoueurQuiPioche extends JDialog implements ActionListener{
    private ArrayList<JButton> joueursJBt; //boutons avec le nom des joueursJBt, ArrayList car on a besoin d'une liste ordonnée
    private JLabel quiPioche;
    private int retour;
    /**
     * * Constructeur
     * @param owner necessaire pour dire que la fenetre est parent de ce popup 
     * @param j c'est le tableau de joueursJBt. Pour les afficher et choisir qui tirer
     *
     */
    public ChoixJoueurQuiPioche(ViewGraphic owner, Joueur[] j){
        super(owner, true);
        joueursJBt = new ArrayList<>();

        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        quiPioche = new JLabel("Qui doit piocher?");
        for(int i=0; i<j.length;++i){
            JButton boutton = new JButton(j[i].toString());
            boutton.addActionListener(this);
            joueursJBt.add(boutton);
        }
        init();
    }
    
    
    /**
     *  Cette fonction place les boutons et autres labels graphiques
     */
    private void init(){
        JPanel panel =new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;
        cont.gridx=0;
        cont.gridy=0;
        panel.add(quiPioche,cont);
        cont.gridy++;

        Iterator<JButton> it = joueursJBt.iterator();
        while(it.hasNext()){
           panel.add(it.next(), cont);
           cont.gridx++;
        }
        this.setContentPane(panel);
        this.pack();
    }
/**
 * Methode qui gere les actions claviers
 */
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = 0;
        Iterator<JButton> it = joueursJBt.iterator();
        while(it.hasNext()) {
            if(e.getSource() == it.next()){
                retour = index;
                this.setVisible(false);
            }
            index++;
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