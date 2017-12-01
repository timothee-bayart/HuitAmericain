package bayart.ayouaz.huit.americain.Model;

import bayart.ayouaz.huit.americain.Enum.Couleur;

public abstract class Regle {

    protected int nbPaquet;
    protected int nbCarteParPaquet;
    protected boolean avecJoker;

    protected String nomVariante;
    protected Couleur nouvelleCouleur;
    protected Carte carteCreeeParJoker;

    public GroupeDeCarte genererPioche() {
        return new GroupeDeCarte(this.nbPaquet, this.nbCarteParPaquet, this.avecJoker);
    }

    public abstract boolean isCoupJouable(Carte coupJoue, Carte carteDefausse);

    public abstract boolean isJoueurAGagne(GroupeDeCarte paquetDuJoueurEnCours);
    
    public abstract void appliquerEffetCarteSpeciale(Partie p);

    public Couleur getNouvelleCouleur(){
        return this.nouvelleCouleur;
    }

    public Carte getCarteCreeeParJoker() {
        return carteCreeeParJoker;
    }
}
