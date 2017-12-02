package bayart.ayouaz.huit.americain.Model;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * 
 * Les joueurs sont ceux qui vont pouvoir interagir avec le jeu.
 */
public class Joueur {

    protected int score;
    protected String nom;
    protected boolean enJeu;
    protected GroupeDeCarte main = new GroupeDeCarte();
    
    public Joueur(String nom) {
    	this.nom = nom;
    }
    

    public void direCarte(){
    }

    public void direContreCarte() {
    }

    public void piocher(Carte pioche) {
    	this.main.ajouterCarte(pioche);
    }

    public Carte jouer(Carte carteDefausse) {
        Carte carte = null;

		int choix = Input.demanderEntier(0, this.main.getNombreDeCartes());

		if(choix != 0) {
			carte = this.main.getCarte(choix - 1);
		}
        
        return carte;
    }

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

	public String getNom() {
		return nom;
	}
	
	
	public static boolean isNomValide(String nom) {
	    return nom.trim().length()>0 && nom.matches("[A-zÀ-ÿ]+");
	}
	
    @Override
	public String toString() {
		return this.getNom();
	}
}
