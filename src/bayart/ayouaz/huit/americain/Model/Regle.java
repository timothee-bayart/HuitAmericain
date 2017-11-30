package bayart.ayouaz.huit.americain.Model;

import bayart.ayouaz.huit.americain.Enum.Couleur;

public abstract class Regle {

    protected Couleur nouvelleCouleur;

    public abstract GroupeDeCarte genererPioche();

    public abstract boolean isCoupJouable(Carte coupJoue, Carte carteDefausse);

    public abstract boolean isJoueurAGagne(GroupeDeCarte paquetDuJoueurEnCours);
    
    public abstract void appliquerEffetCarteSpeciale(Partie p);

}
