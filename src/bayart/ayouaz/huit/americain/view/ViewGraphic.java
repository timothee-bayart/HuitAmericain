package bayart.ayouaz.huit.americain.view;


import bayart.ayouaz.huit.americain.Model.Carte;
import bayart.ayouaz.huit.americain.Model.GroupeDeCarte;
import bayart.ayouaz.huit.americain.Model.Joueur;
import bayart.ayouaz.huit.americain.Model.Regle;
import bayart.ayouaz.huit.americain.controller.Main;
import bayart.ayouaz.huit.americain.controller.PartieGraphique;
import bayart.ayouaz.huit.americain.model.interfaces.Observable;
import bayart.ayouaz.huit.americain.model.interfaces.Observer;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ViewGraphic extends JFrame implements Observer {
    private ArrayList<JButton> menu;
    private JComboBox<String> ComboVariantes;
    private JLabel label;
    private JTextArea pseudo;
    private JPanel panel;
    private JButton valider;
    private GridBagConstraints constraint;
    private PartieGraphique controleur;
    private ArrayList<Joueur> joueurs;
    private String joueurNonIA;
    private ArrayList<JButton> cartesJoueur;
    private Carte defausse;
    private JLabel labelDefausse;
    
    public ViewGraphic(PartieGraphique p){
        joueurs = new ArrayList<Joueur>();
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
    
    public void afficherPlateau(){
        cartesJoueur = new ArrayList<JButton>();
        for(int i=0;i<joueurs.size();++i){
            if(joueurs.get(i).toString()==joueurNonIA){
                GroupeDeCarte m = joueurs.get(i).getMain();
                for(int j=0; j<m.getNombreDeCartes();++j){
                    cartesJoueur.add(new JButton(m.getCarte(j).toString()));
                }
            }
        }
        labelDefausse = new JLabel(defausse.toString());
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridx=0;
        constraint.gridy=0;
        panel.add(labelDefausse, constraint);
        constraint.gridy++;
        for(int i =0; i<cartesJoueur.size();++i){
            panel.add(cartesJoueur.get(i), constraint);
            constraint.gridx++;
        }
        this.setContentPane(panel);
        this.pack();
    }
    
    
    public void variante(Regle[] var){
        label.setText("Choisir la variante du jeu");
        ComboVariantes = new JComboBox<String>();
        for(int i=0; i<var.length;++i){
            ComboVariantes.addItem(var[i].toString());
        }
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridx=0;
        constraint.gridy=0;
        panel.add(label, constraint);
        constraint.gridy++;
        panel.add(ComboVariantes, constraint);
        constraint.gridy++;
        panel.add(valider, constraint);
        this.setContentPane(panel);
        this.pack();
        valider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                controleur.choisirVariante(var[ComboVariantes.getSelectedIndex()]);
            }
        });
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
                controleur.demarrer();
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
                controleur.choisirRegle();
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

    @Override
    public void update(Observable o, Object j) {
        if( o instanceof Joueur && j == null){
            if(joueurs.isEmpty()){
                joueurNonIA=((Joueur)o).toString();
            }
            joueurs.add((Joueur)o);
            this.afficherMenuDepart();
        }
        if( o instanceof Joueur && j instanceof String && j == "piocher"){
            this.afficherPlateau();
        }
        if( o instanceof Carte && j instanceof String && j == "setDefausse"){
            this.defausse = (Carte)o;
            this.afficherPlateau();
        }
    }
}
