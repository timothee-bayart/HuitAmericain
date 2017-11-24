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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Partie partie = new Partie();
		partie.initParamsDuJeu();
		partie.demarrerJeu();
	}
	
	
	
	
    public static final Scanner CLAVIER = new Scanner(System.in);
    public static final String[] MENU_PRINCIPAL = new String[] {
        	"D�marrer la partie",
        	"Ajouter un joueur",
        	"Quitter"
        };

	private final static int NOMBRE_DE_CARTE_A_DISTRIBUER = 7;
	private final static int NOMBRE_DE_JOUEURS_MINIMUM = 2;
	
    public static Regle regle;
	
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

    	System.out.println("************* Huit Am�ricain (Timoth�e BAYART & Idris AYOUAZ *************");
    	
    	//demander nom joueur
    	boolean joueurAjoute = false;
    	while(!joueurAjoute) {
        	System.out.println("Veuillez saisir votre nom (alphab�tique) de joueur : ");
    		
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
    			    
    			    System.out.println("Vous �tes d�sormais "+this.joueurs.size()+" joueurs.");
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
        	
        	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	} while(!this.regle.aGagne(this.joueurQuiJoue.getMain()));

    	System.out.println("****************************************");
    	System.out.println(this.joueurQuiJoue.getNom() + " a gagn� !");
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
    	System.out.println("--- � "+this.joueurQuiJoue+" de jouer ---");
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
    		
    		//this.changerSens(); //test
    	}
    	
    	if(carteJouee == null){ //piocher
    		this.joueurQuiJoue.piocher(this.pioche.retirerCarte());
        	System.out.println("<"+this.joueurQuiJoue.getNom()+" a pioch�, il a "+this.joueurQuiJoue.getMain().getNombreDeCartes()+" cartes>");
    	} else {
        	System.out.println("<"+this.joueurQuiJoue.getNom()+" a jou� "+carteJouee+", il a "+this.joueurQuiJoue.getMain().getNombreDeCartes()+" cartes>");
        	
        	if(this.carteDefausse!=null) {
        		this.pioche.ajouterCarte(this.carteDefausse);
        	}
        	this.joueurQuiJoue.getMain().retirerCarte(carteJouee);
        	this.carteDefausse = carteJouee;
    	}
    	
    	System.out.println("-> D�fausse : "+this.carteDefausse+" \n");
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
    	//this.sens = !this.sens;
    	ArrayList<Joueur> list = new ArrayList<Joueur>(this.joueurs); //creer une liste � partir d'un set
    	Collections.reverse(list); //inverser l'ordre des �l�ments dans la liste
    	 
        this.joueurs = new LinkedHashSet<Joueur>(list); //transformer la liste invers�e en set
    	
    	Iterator<Joueur> iterator = this.joueurs.iterator();
    	
    	//Il faut mettre � jour l'it�rateur des joueurs pour que le prochain joueur a jouer respecte le nouvel ordre du set
    	Joueur joueur;
    	do {
    		joueur = iterator.next();
    		if(joueur == this.joueurQuiJoue) {
    			this.joueursIt = iterator;
    		}
    	} while(iterator.hasNext() && joueur != this.joueurQuiJoue);
    }
}