/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author ed
 */
public class ChoixCouleur extends JDialog implements ActionListener{
    private JButton carre, coeur, pic, trefle;
    private int retour;
    public ChoixCouleur(ViewGraphic owner){
        super(owner,true);
        carre = new JButton("Carreau");
        coeur = new JButton("Coeur");
        pic = new JButton("Pique");
        trefle = new JButton("Trefle");
        carre.addActionListener(this);
        coeur.addActionListener(this);
        pic.addActionListener(this);
        trefle.addActionListener(this);
        init();
    }
    
    private void init(){
        JPanel panel =new JPanel();
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == carre){
            retour = 1;
            this.setVisible(false);
        }
        if(e.getSource() == trefle){
            retour = 3;
            this.setVisible(false);
        }
        if(e.getSource() == coeur){
            retour = 0;
            this.setVisible(false);
        }
        if(e.getSource() == pic){
            retour = 2;
            this.setVisible(false);
        }
    }
    public int showDialog(){
        this.setVisible(true);
        return retour;
    }
}
