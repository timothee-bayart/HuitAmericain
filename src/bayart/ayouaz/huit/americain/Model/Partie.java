package bayart.ayouaz.huit.americain.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;


public class Partie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Partie partie = new Partie();
		partie.initParamsDuJeu();
		partie.demarrerJeu();
	}
	
	
	
	
    public static final Scanner CLAVIER = new Scanner(System.in);
    public static final String[] MENU_PRINCIPAL = new String[] {
        	"Démarrer la partie",
        	"Ajouter un joueur",
        	"Quitter"
        };

	private final static int NOMBRE_DE_CARTE_A_DISTRIBUER = 7;
	private final static int NOMBRE_DE_JOUEURS_MINIMUM = 2;
	
    public static Regle regle;
	
    private int nbManche;
    private int nbMancheTotal;
    private boolean sens;
    private GroupeDeCarte pioche;
    private Carte carteDefausse;
	private LinkedHashSet<Joueur> joueurs = new LinkedHashSet<Joueur>();
    private Joueur joueurQuiJoue;
    private Iterator<Joueur> joueursIt;
    
    private Joueur gagnant;
    private TreeSet<Joueur> perdants = new TreeSet<Joueur> ();
       
    
    public Partie() {
    }


    private int afficherMenu(String[] menu) {
    	
    	boolean exit = false;
    	
    	while(!exit) {

        	System.out.println("*****************************************");
        	for(int i=0; i<menu.length; i++) {
            	System.out.println("["+i+"] "+menu[i]);
        	}
        	
        	try {
            	int index = CLAVIER.nextInt();
            	
            	if(index>=0 && index<menu.length){
            		exit = true;
            		return index;
            	}
            	
        	} catch(InputMismatchException e) {
            	Partie.CLAVIER.next();
        	}
        	
    	}
    	
		return 0;
    }
    
    
    
    
    public void initParamsDuJeu() {

    	System.out.println("************* Huit Américain (Timothée BAYART & Idris AYOUAZ *************");
    	
    	//demander nom joueur
    	boolean joueurAjoute = false;
    	while(!joueurAjoute) {
        	System.out.println("Veuillez saisir votre nom (alphabétique) de joueur : ");
    		
    		try {
        		String nomJoueur = Partie.CLAVIER.nextLine();
            	
            	if(Joueur.isNomValide(nomJoueur)){
            		this.joueurs.add(new Joueur(nomJoueur));
            		joueurAjoute = true;
            	}
            	
        	} catch(InputMismatchException e) {
        		//on laisse l'user donner une autre valeure
            	Partie.CLAVIER.next();
        	}
    		
    	}
    	
    	
    	boolean partieParametree = false;
    	
    	while(!partieParametree) {
        	//ajouter les joueurs, choisir la variante
    		int choix  = afficherMenu(Partie.MENU_PRINCIPAL);
    		
    		switch(choix) {
    			case 0:
    				if(this.joueurs.size() >= Partie.NOMBRE_DE_JOUEURS_MINIMUM) {
    					partieParametree = true;
    				} else {
    					System.out.println("Veuillez ajouter au moins "+(Partie.NOMBRE_DE_JOUEURS_MINIMUM - this.joueurs.size())+" joueur(s) pour commencer.");
    				}
    				break;
    				
    			case 1:
    				int numeroJoueur = 1;
    			    Joueur nouveauJoueur;
    			    
    			    do {
    			    	nouveauJoueur = new Ia("Joueur"+(this.joueurs.size()+numeroJoueur));
    			    	numeroJoueur++;
    			    } while(!this.joueurs.add(nouveauJoueur));
    			    
    			    System.out.println("Vous êtes désormais "+this.joueurs.size()+" joueurs.");
    				break;
    				
    			case 2:
    				System.exit(0);
    				break;
    		}
    	}
    	
    	//choisir variante !!
    	this.regle = new Variante1();
    	
    	this.pioche = new GroupeDeCarte(1); //attention nombre de paquets
    	this.pioche.melanger();
    	
    	this.joueursIt = this.joueurs.iterator();
    }
    

    public void demarrerJeu() {
    	System.out.println("*****************************************");
    	System.out.println("La partie commence...");
    	this.distribuerCartes();
    	
    	do {
    		this.changerTour();
        	this.jouerCoup();
        	System.out.println("<"+this.joueurQuiJoue.getNom()+" a joué, il a "+this.joueurQuiJoue.getMain().getNombreDeCartes()+" cartes>\n");
        	
        	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	} while(!this.regle.aGagne(this.joueurQuiJoue.getMain()));

    	System.out.println("****************************************");
    	System.out.println(this.joueurQuiJoue.getNom() + " a gagné !");
    	System.out.println("****************************************");
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
    	
        Carte carteJouee;
    	
    	if(this.joueurQuiJoue instanceof Ia) {
    		carteJouee = this.joueurQuiJoue.jouer(this.carteDefausse);
        	
    	} else {
    		System.out.println("Quel coup souhaitez-vous jouer ?");
    		
        	boolean isCarteJouee = false;
    		do {
    			//proposer piocher
	    		System.out.println("[0] Piocher");
	    		//proposer les cartes
	    		System.out.println(this.joueurQuiJoue.getMain());
	    		
	    		carteJouee = this.joueurQuiJoue.jouer(this.carteDefausse);
	    		
	    		if(this.regle.isCoupJouable(carteJouee, this.carteDefausse)) {
	    			
	    			isCarteJouee = true;
	    		} else {
	    			System.out.println("Coup non jouable. Quel coup souhaitez-vous jouer ?");
	    		}
	    		
	    	} while(!isCarteJouee);
    	}
    	
    	if(carteJouee == null){ //piocher
    		this.joueurQuiJoue.piocher(this.pioche.retirerCarte());
    	} else {
        	if(this.carteDefausse!=null) {
        		this.pioche.ajouterCarte(this.carteDefausse);
        	}
        	this.joueurQuiJoue.getMain().retirerCarte(carteJouee);
        	this.carteDefausse = carteJouee;
    	}
    }

    public void finJeu() {
    }
    

    private void changerTour(){
    	if(!this.joueursIt.hasNext()) {
        	this.joueursIt = this.joueurs.iterator();
    	}
    	
    	this.joueurQuiJoue = joueursIt.next();
    }
    
    private void changerSens() {
    	this.sens = !this.sens;
    }
}