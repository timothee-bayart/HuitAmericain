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
import javax.swing.*;

/**
 *
 * @author ed
 */
public class ChoixJoueurQuiPioche extends JDialog implements ActionListener{
    private ArrayList<JButton> joueurs;
    private JLabel quiPioche;
    private int retour;
    public ChoixJoueurQuiPioche(ViewGraphic owner, Joueur[]j){
        super(owner, true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        quiPioche = new JLabel("Qui doit piocher?");
        for(int i=0; i<j.length;++i){
            joueurs.add(new JButton(j[i].toString()));
            joueurs.get(i).addActionListener(this);
        }
        init();
    }
    
    private void init(){
        JPanel panel =new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;
        cont.gridx=0;
        cont.gridy=0;
        panel.add(quiPioche,cont);
        cont.gridy++;
        for(int i=0; i<joueurs.size();++i){
           panel.add(joueurs.get(i), cont);
           cont.gridx++;
        }
        this.setContentPane(panel);
        this.pack();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<joueurs.size();++i){
            if(e.getSource() == joueurs.get(i)){
                retour = i;
                this.setVisible(false);
            }
        }
    }
    public int showDialog(){
        this.setVisible(true);
        return retour;
    }
}