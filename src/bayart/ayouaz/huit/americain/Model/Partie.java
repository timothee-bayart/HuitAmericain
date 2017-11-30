package bayart.ayouaz.huit.americain.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;


public class Partie {
	
    public static final Scanner CLAVIER = new Scanner(System.in);

    private final static int NOMBRE_DE_CARTE_A_DISTRIBUER = 7;
    private final static int NOMBRE_DE_JOUEURS_MINIMUM = 2;
    
    public Regle regle;
    private Affichage fenetre;
    private int nbManche;
    private int nbMancheTotal;
    //private boolean sens = true;
    private GroupeDeCarte pioche;
    private Carte carteDefausse;
    private LinkedHashSet <Joueur> joueurs = new LinkedHashSet<Joueur>();
    private Joueur joueurQuiJoue;
    private Iterator<Joueur> joueursIt;
    
    private Joueur gagnant;
    private TreeSet<Joueur> perdants = new TreeSet<Joueur> ();
       
    public Carte retirerCartePioche(){
        return pioche.retirerCarte();
    }
    
    public Joueur getJoueurQuiJoue(){
        return joueurQuiJoue;
    }
    
    public Partie() {
        fenetre = new Affichage();
    }


    private int afficherMenu() {

    	while(true) {
            fenetre.afficherMenu();
            return Input.demanderEntier(0, 3);
    	}
    }
    
    
    
    
    public void initParamsDuJeu() {

    	fenetre.intro();
    	
    	//demander nom joueur
    	boolean joueurAjoute = false;
    	while(!joueurAjoute) {
            fenetre.demanderNomJoueur();

            //try {
            String nomJoueur = Input.demanderString();

            if(Joueur.isNomValide(nomJoueur)){
                this.joueurs.add(new Joueur(nomJoueur));
                joueurAjoute = true;
            }

            /*} catch(InputMismatchException e) {
                    //on laisse l'user donner une autre valeure
            Partie.CLAVIER.next();
            }*/
    		
    	}

    	
    	//choisir variante !!
    	this.regle = new Variante1();
    	
    	boolean partieParametree = false;
    	
    	while(!partieParametree) {
        	//ajouter les joueurs, choisir la variante
    		int choix = afficherMenu();
    		
    		switch(choix) {
    			case 0:
    				if(this.joueurs.size() >= Partie.NOMBRE_DE_JOUEURS_MINIMUM) {
    					partieParametree = true;
    				} else {
                                    fenetre.ajouterJoueurs(Partie.NOMBRE_DE_JOUEURS_MINIMUM - this.joueurs.size());
    				}
    				break;
    				
    			case 1:
    				int numeroJoueur = 1;
    			    Joueur nouveauJoueur;
    			    
    			    do {
    			    	nouveauJoueur = new Ia("Joueur"+(this.joueurs.size()+numeroJoueur), this.regle);
    			    	numeroJoueur++;
    			    } while(!this.joueurs.add(nouveauJoueur));
    			    fenetre.joueurEnPlus(this.joueurs.size());
    				break;
    				
    			case 2:
    				System.exit(0);
    				break;
    		}
    	}
    	
        pioche = regle.initPartie(); //probleme de nombre de paquets reglé
    	//this.pioche = new GroupeDeCarte(1); //attention nombre de paquets
    	this.pioche.melanger();
    	
    	this.joueursIt = this.joueurs.iterator();
    }
    

    public void infligerMalus(){
        regle.leProchainJoueurDevra(this);
    }
    
    public void demarrerJeu() {
    	fenetre.debutPartie();
    	this.distribuerCartes();
    	
    	do {
    		this.changerTour();
                this.infligerMalus();
        	this.jouerCoup();
        	
        	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	} while(!this.regle.aGagne(this.joueurQuiJoue.getMain()));

    	fenetre.victoire(this.joueurQuiJoue.getNom());
    }

    
    public void distribuerCartes() {
		for(int i=0; i<NOMBRE_DE_CARTE_A_DISTRIBUER; i++) {
	    	this.joueursIt = this.joueurs.iterator();
		    while (joueursIt.hasNext()) {
		    	joueursIt.next().getMain().ajouterCarte(this.pioche.retirerCarte());
		    }
		}
    }


    public void jouerCoup() {
    	fenetre.annonceJoueurQuiJoue(this.joueurQuiJoue.getNom());
        Carte carteJouee;
    	
    	if(this.joueurQuiJoue instanceof Ia) {
    		carteJouee = this.joueurQuiJoue.jouer(this.carteDefausse);
        	
    	} else {
    		fenetre.queVeuxJouerLeJoueur();
    		
        	boolean isCarteJouee = false;
    		do {
                    fenetre.afficherOptionsJouables(this.joueurQuiJoue.getMain());
	    		
	    		carteJouee = this.joueurQuiJoue.jouer(this.carteDefausse);
	    		
	    		if(this.regle.isCoupJouable(carteJouee, this.carteDefausse)) {
	    			
	    			isCarteJouee = true;
	    		} else {
	    			fenetre.coupIllegal();
	    		}
	    		
	    	} while(!isCarteJouee);
    	}
    	
    	if(carteJouee == null){ //piocher
    		this.joueurQuiJoue.piocher(this.pioche.retirerCarte());
                fenetre.afficherPiocher(this.joueurQuiJoue.getNom(), this.joueurQuiJoue.getMain().getNombreDeCartes());
    	} else {
        	fenetre.afficherJouer(carteJouee, this.joueurQuiJoue.getNom(), this.joueurQuiJoue.getMain().getNombreDeCartes());
        	if(this.carteDefausse!=null) {
        		this.pioche.ajouterCarte(this.carteDefausse);
        	}
        	this.joueurQuiJoue.getMain().retirerCarte(carteJouee);
        	this.carteDefausse = carteJouee;
    	}
    	
    	fenetre.afficherDefausse(this.carteDefausse);
    }

    public void finJeu() {
    }
    

    public void changerTour(){
        if(!this.joueursIt.hasNext()) {
                this.joueursIt = this.joueurs.iterator();
    	}
    	this.joueurQuiJoue = joueursIt.next();
    }
    
    public void changerSens() {
    	//this.sens = !this.sens;
    	ArrayList<Joueur> list = new ArrayList<Joueur>(this.joueurs); //creer une liste à partir d'un set
    	Collections.reverse(list); //inverser l'ordre des éléments dans la liste
    	 
        this.joueurs = new LinkedHashSet<Joueur>(list); //transformer la liste inversée en set
    	
    	Iterator<Joueur> iterator = this.joueurs.iterator();
    	
    	//Il faut mettre à jour l'itérateur des joueurs pour que le prochain joueur a jouer respecte le nouvel ordre du set
    	Joueur joueur;
    	do {
    		joueur = iterator.next();
    		if(joueur == this.joueurQuiJoue) {
    			this.joueursIt = iterator;
    		}
    	} while(iterator.hasNext() && joueur != this.joueurQuiJoue);
    }
}