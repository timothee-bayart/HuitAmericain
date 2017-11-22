package Model;

public abstract class Regle {
    protected int nbPaquets;

    protected Carte derniereCarteJoue;

    public abstract void initPartie();

    public abstract boolean isCoupJouable(Carte coupJoue, Carte carteDefausse);

    public abstract int conditionVictoire(GroupeDeCarte paquetDuJoueurEnCours);

}
