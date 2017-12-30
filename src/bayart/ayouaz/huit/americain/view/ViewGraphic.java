package bayart.ayouaz.huit.americain.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewGraphic extends JFrame implements Observer {
    private ArrayList<JButton> menu;
    private JPanel panel;
    private GridBagConstraints constraint;
    @Override 
    public void update(Observable o, Object arg) {

    }
    
    public ViewGraphic(){
        this.setTitle("Huit Americain");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu = new ArrayList<JButton>();
        init();
    }
    
    private void init(){
        menu.add(new JButton("Démarrer la partie"));
       	menu.add(new JButton("Ajouter un joueur"));
       	menu.add(new JButton("Choisir la variante"));
       	menu.add(new JButton("Quitter"));
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
    }
}
