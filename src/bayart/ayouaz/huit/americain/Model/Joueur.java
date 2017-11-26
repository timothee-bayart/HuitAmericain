package bayart.ayouaz.huit.americain.Model;

import java.util.InputMismatchException;
import java.util.Scanner;

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
        boolean isCoupJoue = false;
        
        do {
        	try {
        		int index = Partie.CLAVIER.nextInt();
	            
	            if(index>=0 && index<=this.main.getNombreDeCartes()) {
	            	isCoupJoue = true;
	            	if(index == 0) {
		            	carte = null;
		            } else {
			        	carte = this.main.getCarte(index - 1);
		            }
	            } else {
                                Affichage.erreurInput();
	            }
        	} catch(InputMismatchException e) {
                        Affichage.erreurInput();
        		Partie.CLAVIER.next();
        	}
            
        } while(!isCoupJoue);
        
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
	    return nom.trim().length()>0 && nom.matches("[A-z�-�]+");
	}
	
    @Override
	public String toString() {
		return this.getNom();
	}
}
