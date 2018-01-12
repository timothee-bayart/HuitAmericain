package bayart.ayouaz.huit.americain.model;

import bayart.ayouaz.huit.americain.controller.PartieGraphique;
import bayart.ayouaz.huit.americain.model.enums.Couleur;

import java.io.Serializable;

/**
 * 
 * Cette classe permet de definir à l'avance comment il faudra jouer.
 */
public abstract class Regle implements Serializable {

    protected int nbPaquet;
    protected int nbCarteParPaquet;
    protected boolean avecJoker;

    protected String nomVariante;
    protected Couleur nouvelleCouleur;
    protected Carte carteCreeeParJoker;
/**
 * 
 * @param nbJoueurs vérifie le nombre de joueur
 * Cette methode va générer la pioche en fonction du nombre de joueur et des regles annoncés.
 * @return retourne un groupe de carte qui sera la pioche
 */
    public GroupeDeCarte genererPioche(int nbJoueurs) {
        int maxJoueursParPioche;
        if(this.nbCarteParPaquet == GroupeDeCarte.PAQUET_52_CARTES){
            maxJoueursParPioche = 5;
        } else {
            maxJoueursParPioche = 2;
        }
        return new GroupeDeCarte((nbJoueurs / maxJoueursParPioche + 1) * this.nbPaquet, this.nbCarteParPaquet, this.avecJoker);
    }
    /**
     * 
     * @param coupJoue la carte que le joueur veut poser
     * @param carteDefausse la derniere carte jouée
     * @return vrai si le joueur peut jouer cette carte, faux sinon
     */
    public abstract boolean isCoupJouable(Carte coupJoue, Carte carteDefausse);
    /**
     * 
     * @param paquetDuJoueurEnCours
     * @return verifie que le paquet est vide ou non pour savoir s'il a gagné.
     */
    public abstract boolean isJoueurAGagne(GroupeDeCarte paquetDuJoueurEnCours);
    /**
     * 
     * @param p la partie en cours
     * Cette methode va modifier les propriétés de la partie (sens ...)
     * En fonction des dernieres cartes posés.
     */
    public abstract void appliquerEffetCarteSpeciale(PartieGraphique p);
    /**
     * Demande quelle sera la prochaine couleur si on joue un 8 (dans la majorité des variantes)
     * @return la couleur
     */
    public Couleur getNouvelleCouleur(){
        return this.nouvelleCouleur;
    }
    /**
     * Demande ce que le joueur veut que sont joker soit
     * @return la valeur du joker
     */
    public Carte getCarteCreeeParJoker() {
        return carteCreeeParJoker;
    }

    @Override
    public String toString(){
        return this.nomVariante;
    }
}
