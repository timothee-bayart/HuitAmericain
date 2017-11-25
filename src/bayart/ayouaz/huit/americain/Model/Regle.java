package bayart.ayouaz.huit.americain.Model;

public abstract class Regle {
    protected int nbPaquets;

    protected Carte derniereCarteJoue;

    public abstract void initPartie();

    public abstract boolean isCoupJouable(Carte coupJoue, Carte carteDefausse);

    public abstract boolean aGagne(GroupeDeCarte paquetDuJoueurEnCours);
    
    public abstract int leProchainJoueurDevra();

}
