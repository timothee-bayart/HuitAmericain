package bayart.ayouaz.huit.americain.Model;

import java.util.List;

public class Joueur {

    protected int score;
    protected String nom;
    protected boolean enJeu;
    protected GroupeDeCarte paquetDeCarte;
    
    

    public void direCarte() {
    }

    public void direContreCarte() {
    }

    public void piocher(Carte pioche) {
    }

    public Carte jouer(Carte carteDefausse) {
    	return null;
    }

    public GroupeDeCarte getPaquet() {
    	return this.paquetDeCarte;
    }

}
