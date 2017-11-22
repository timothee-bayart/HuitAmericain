package Model;

import java.util.ArrayList;

public class Partie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    private int nbManche;
    private int nbMancheTotal;
    private boolean sens;
    private ArrayList<GroupeDeCarte> paquetDeCarte = new ArrayList<GroupeDeCarte> ();
    private ArrayList<Joueur> joueur = new ArrayList<Joueur> ();
    private Regle regle;
    private Joueur joueurQuiJoue;
    private Joueur gagnant;
    private ArrayList<Joueur> perdant = new ArrayList<Joueur> ();

    public void distribuerCartes() {
    }

    public void demarrerJeu() {
    }

    private void InitParamsDuJeu() {
    }

    public void jouerCoup() {
    }

    private void changerTour() {
    }

    public void finJeu() {
    }
}