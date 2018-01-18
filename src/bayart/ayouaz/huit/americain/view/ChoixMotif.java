/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.view;

import bayart.ayouaz.huit.americain.model.enums.Motif;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Cette classe est un popup designé pour s'afficher avec le mode graphique afin
 * de choisir le prochaine motif à jouer
 */
public class ChoixMotif extends JDialog{
    private JComboBox comboMotifs;
    private int retour;
    private JLabel quelMotif;
    private JButton valider;
    /**
     * Constructeur
     * @param owner necessaire pour dire que la fenetre est parent de ce popup 
     */
    public ChoixMotif(ViewGraphic owner){
        super(owner,true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        quelMotif = new JLabel("Quelle motif voulez vous?");
        comboMotifs = new JComboBox<String>();

        for(Motif motif : Motif.values()) {
            comboMotifs.addItem(motif.toString());
        }
        comboMotifs.setPreferredSize(new Dimension(150, 25));
        valider = new JButton("Valider");
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
        panel.add(quelMotif,cont);

        cont.gridy++;
        panel.add(comboMotifs, cont);

        cont.gridy++;

        valider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                retour = Motif.fromString((String) comboMotifs.getSelectedItem()).getNumero()-1;
                ChoixMotif.this.setVisible(false);
            }
        });

        panel.add(valider, cont);

        this.setContentPane(panel);
        this.pack();
    } 
    /**
     * Fonction appelé par la fenetre afin d'ouvrir la popup
     * @return la valeur du motif choisi
     */
    public int showDialog(){
        this.setVisible(true);
        return retour;
    }
}
