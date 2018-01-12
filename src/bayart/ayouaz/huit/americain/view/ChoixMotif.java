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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ed
 */
public class ChoixMotif extends JDialog implements ActionListener{
    private ArrayList<JButton> motifs;
    private int retour;
    private JLabel quelMotif;
    
    public ChoixMotif(ViewGraphic owner){
        super(owner,true);
        motifs = new ArrayList<JButton>();
        quelMotif = new JLabel("Quelle motif voulez vous?");
        for(int i=1; i<=10;++i){
            motifs.add(new JButton(i + ""));
        }
        motifs.add(new JButton("valet"));
        motifs.add(new JButton("dame"));
        motifs.add(new JButton("roi"));
        init();
    }

    private void init(){
        JPanel panel =new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;
        cont.gridx=0;
        cont.gridy=0;
        panel.add(quelMotif,cont);
        cont.gridy++;
        for(int i=0; i<motifs.size();++i){
           panel.add(motifs.get(i), cont);
           cont.gridx++;
        }
        this.setContentPane(panel);
        this.pack();
    } 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i< motifs.size();++i){
            if(e.getSource() == motifs.get(i)){
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
