package bayart.ayouaz.huit.americain.view;

import bayart.ayouaz.huit.americain.controller.PartieGraphique;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ViewGraphic extends JFrame implements Observer {
    private ArrayList<JButton> menu;
    private JLabel label;
    private JTextArea pseudo;
    private JPanel panel;
    private JButton valider;
    private GridBagConstraints constraint;
    private PartieGraphique controleur;
    @Override 
    public void update(Observable o, Object arg) {
        
    }
    
    public ViewGraphic(PartieGraphique p){
        controleur=p;
        controleur.setFenetre(this);
        this.setTitle("Huit Americain");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu = new ArrayList<JButton>();
        label = new JLabel();
        valider = new JButton("Valider");
        pseudo = new JTextArea();
        afficherPseudoDepart();
    }
    public void afficherPseudoDepart(){
        pseudo.setText("");
        label.setText("Votre pseudo");
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        
        constraint.gridx=0;
        constraint.gridy=0;
        panel.add(label, constraint);
        constraint.gridy=1;
        panel.add(pseudo, constraint);
        constraint.gridx=1;
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
        menu.clear();
        menu.add(new JButton("Démarrer la partie"));
       	menu.add(new JButton("Ajouter un joueur"));
       	menu.add(new JButton("Choisir la variante"));
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        
        constraint.gridx=0;
        for(int i=0;i<menu.size();++i){
            constraint.gridy=i;
            panel.add(menu.get(i), constraint);
        }
        this.setContentPane(panel);
        this.pack();
        menu.get(0).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                
            }
        });
        menu.get(1).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                controleur.changerFenetre(0);
            }
        });
        menu.get(2).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                controleur.changerFenetre(1);
            }
        });
    }
    public void afficherAjoutJoueur(){
        pseudo.setText("");
        label.setText("Nom du nouveau Joueur");
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        
        constraint.gridx=0;
        constraint.gridy=0;
        panel.add(label, constraint);
        constraint.gridy=1;
        panel.add(pseudo, constraint);
        constraint.gridx=1;
        panel.add(valider, constraint);
        this.setContentPane(panel);
        this.pack();
    }
}
