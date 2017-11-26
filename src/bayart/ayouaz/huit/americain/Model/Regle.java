package bayart.ayouaz.huit.americain.Model;

public abstract class Regle {
    protected int nbPaquets;

    protected Carte derniereCarteJoue;

    public abstract void initPartie();

    public abstract boolean isCoupJouable(Carte coupJoue, Carte carteDefausse);

    public abstract boolean aGagne(GroupeDeCarte paquetDuJoueurEnCours);
    
    /**
 * 0 rien
 * -1 inverser sens du jeu
 * -2 passer tour
 * -3 rejouer
 * -4 décider de la prochaine couleur
 * -5 designe le prochain joueur ou echange ses cartes avec lui
 *  valeurs positives : nombre de carte a piocher pour l'adversaire
 * @return la valeur code qui donne les malus infligeable à l'adversaire 
 */
    public abstract void leProchainJoueurDevra(Partie p);

}
