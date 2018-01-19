package bayart.ayouaz.huit.americain.model;

import java.io.Serializable;
import java.util.Observable;

/**
 * 
 * Les joueurs sont ceux qui vont pouvoir interagir avec le jeu.
 */
public class Joueur extends Observable implements Serializable {

    protected int score;
    protected String nom;
    protected boolean enJeu;
    protected GroupeDeCarte main = new GroupeDeCarte();
    private boolean peutJouer;
    /**
     * Constructeur
     * @param nom c'est le nom du joueur
     */
    public Joueur(String nom) {
    	this.nom = nom;
    	this.peutJouer = false;
        setChanged();
    }
    /**
     * innutilisé
     */
    public void direCarte(){
    }
    /**
     * innutilisé
     */
    public void direContreCarte() {
    }
    /**
     * permet au joueur de piocher
     * @param pioche la carte que le joueur a pioché
     */
    public void piocher(Carte pioche) {
    	this.main.ajouterCarte(pioche);
        setChanged();
        this.notifyObservers("piocher");
    }
    /**
     * Permet au joueur de jouer
     * @param carteDefausse c'est la carte au dessus de la defausse.
     * @return la carte que le joueur veut jouer
     */
    public Carte jouer(Carte carteDefausse) {
        Carte carte = null;

		int choix = Input.demanderEntier(0, this.main.getNombreDeCartes());

		if(choix != 0) {
			carte = this.main.getCarte(choix - 1);
		}
        
        return carte;
    }
    /**
     * Getteur permettant d'obtenir toutes les cartes du joueur
     * @return la main du joueur
     */
    public GroupeDeCarte getMain() {
    	return this.main;
    }
    
    @Override
    public boolean equals(Object object) {
    	if(object instanceof Joueur) {
    		return ((Joueur) object).getNom().equals(this.nom);
    	} else {
    		return false;
    	}
    }
        /**
         * Getteur permettant d'obtenir le nom du joueur.
         * @return le nom du joueur
         */
	public String getNom() {
		return nom;
	}
	
	/**
         * Cette methode vérifie si le nom que le joueur veut utiliser est valide
         * @param nom le nom que le joueur veut utiliser
         * @return un boolean indiquant si c'est valide ou non.
         */
	public static boolean isNomValide(String nom) {
	    return nom.trim().length()>0 && nom.matches("[A-zÀ-ÿ]+");
	}
	
    @Override
	public String toString() {
		return this.getNom();
	}

        /**
         * getter pour savoir si le joueur peut jouer
         * @return un boolean indiquant si il peut jouer ou non
         */
    public boolean getPeutJouer() {
        return this.peutJouer;
    }
    /**
     * Setter permettant d'indiquer au joueur d'il peut jouer
     * @param peutJouer L'état a changer pour la variable peutJouer
     */
    public void setPeutJouer(boolean peutJouer) {
        this.peutJouer = peutJouer;
    }
}
