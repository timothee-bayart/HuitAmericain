package bayart.ayouaz.huit.americain.controller;

import bayart.ayouaz.huit.americain.model.*;
import bayart.ayouaz.huit.americain.Model.*;
import bayart.ayouaz.huit.americain.view.ViewGraphic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 *
 * Cette classe fait office de tronc centrale dans le jeu. C'est elle qui
 * appelle toute les bonnes fonctions des bonnes classes, dans le bon ordre.
 * Dans le design pattern MVC, c'est le controlleur
 */
public class PartieGraphique {

    private final static int NOMBRE_DE_CARTE_A_DISTRIBUER = 7;
    private final static int NOMBRE_DE_JOUEURS_MINIMUM = 2;

    private Regle regle;
//    private int nbManche;
//    private int nbMancheTotal;
    private GroupeDeCarte pioche;
    private Carte carteDefausse;
    private Carte dernierCoupJoue;
    private LinkedHashSet<Joueur> joueurs = new LinkedHashSet<Joueur>();
    private Joueur joueurQuiJoue;
    private Joueur prochainJoueurQuiVaJouer;
    private Iterator<Joueur> joueursIt;
    private Regle[] variantes;
    private ViewGraphic fen;
//    private Joueur gagnant;
//    private TreeSet<Joueur> perdants = new TreeSet<Joueur> ();

    public void setFenetre(ViewGraphic v) {
        fen = v;
    }

    public PartieGraphique() {
    }

    /**
     * Cette methode va initialiser le jeur. Demander le nom des joueurs, la
     * variante, et va générer la pioche.
     */
    public void ajouterJoueur(String nom) {
        if (Joueur.isNomValide(nom)) {
            this.joueurs.add(new Joueur(nom));
            fen.afficherMenuDepart();
        }
    }
    public void changerFenetre(int i){
        switch(i){
            case 0:
                fen.afficherAjoutJoueur();
                break;
        }
    }
//
//    public void initParamsDuJeu() {
//        //demander nom joueur
//        boolean joueurAjoute = false;
////    	boolean joueurAjoute = true;
//        while (!joueurAjoute) {
//
//            if (Joueur.isNomValide(nomJoueur)) {
//                this.joueurs.add(new Joueur(nomJoueur));
//                joueurAjoute = true;
//            }
//        }
//        // variante par default
//        this.regle = this.variantes[0];
//
//        boolean partieParametree = false;
//
//        while (!partieParametree) {
//            //ajouter les joueurs, choisir la variante
//            int choix = this.demanderActionMenuPrincipal();
//
//            switch (choix) {
//                case 0: //démmarer
//                    if (this.getNbJoueurs() >= PartieGraphique.NOMBRE_DE_JOUEURS_MINIMUM) {
//                        partieParametree = true;
//                    } else {
//                        this.fenetre.ajouterJoueurs(PartieGraphique.NOMBRE_DE_JOUEURS_MINIMUM - this.getNbJoueurs());
//                    }
//                    break;
//
//                case 1: //ajouter joueur
//                    int numeroJoueur = 1;
//                    Joueur nouveauJoueur;
//
//                    do {
//                        nouveauJoueur = new Ia("Joueur" + (this.getNbJoueurs() + numeroJoueur), this.regle);
//                        numeroJoueur++;
//                    } while (!this.joueurs.add(nouveauJoueur));
//                    this.fenetre.joueurEnPlus(this.getNbJoueurs());
//                    break;
//
//                case 2: // choisir variante
//                    this.fenetre.choisirVariante(this.variantes, this.regle);
//                    this.regle = this.variantes[Input.demanderEntier(0, this.variantes.length - 1)];
//                    this.fenetre.afficherVarianteSelectionee(this.regle);
//                    break;
//
//                case 3:
//                    System.exit(0);
//                    break;
//            }
//        }
//
//        this.pioche = this.regle.genererPioche(this.joueurs.size());
//        this.pioche.melanger();
//        this.joueursIt = this.joueurs.iterator();
//    }
//
//    /**
//     * Affiche le menu et demande à l'utilisateur de choisir
//     *
//     * @return le choix du joueur.
//     */
//    private int demanderActionMenuPrincipal() {
//        this.fenetre.afficherMenuPrincipal();
//        return Input.demanderEntier(0, Affichage.MENU_PRINCIPAL.length);
//    }
//
//    /**
//     * Cette methode va demarrer le jeu.
//     */
//    public void demarrerJeu() {
//        this.fenetre.debutPartie();
//        this.distribuerCartes();
//
//        do {
//            /*
//    		 on applique avant le tour suivant car si on le fait a la fin du tour, on risque de changer de joueur qui joue
//    		 et donc de continuer le jeu meme si on a gagné
//             */
//            this.regle.appliquerEffetCarteSpeciale(this);
//            this.changerTour();
//            this.jouerCoup();
//
//            try {
//                Thread.sleep(1500);
////				Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } while (!this.regle.isJoueurAGagne(this.joueurQuiJoue.getMain()));
//
//        fenetre.victoire(this.joueurQuiJoue.getNom());
//    }
//
//    public void distribuerCartes() {
//        for (int i = 0; i < NOMBRE_DE_CARTE_A_DISTRIBUER; i++) {
//            this.joueursIt = this.joueurs.iterator();
//            while (joueursIt.hasNext()) {
//                joueursIt.next().getMain().ajouterCarte(this.pioche.retirerCarte());
//            }
//        }
//
//        do {
//            this.carteDefausse = this.pioche.retirerCarte();
//            if (this.carteDefausse.getMotif() == Motif.JOKER) {
//                this.pioche.ajouterCarte(this.carteDefausse);
//                this.carteDefausse = null;
//            }
//        } while (this.carteDefausse.getMotif() == null);
//    }
//
//    /**
//     * permet de passer au joueur suivant
//     */
//    public void changerTour() {
//        if (this.prochainJoueurQuiVaJouer != null) {
//            this.joueurQuiJoue = this.prochainJoueurQuiVaJouer;
//        } else {
//            this.joueurQuiJoue = this.getProchainJoueur();
//        }
//        this.prochainJoueurQuiVaJouer = this.getProchainJoueur();
//    }
//
//    /**
//     *
//     * @return le prochain joueur
//     */
//    private Joueur getProchainJoueur() {
//        if (!this.joueursIt.hasNext()) {
//            this.joueursIt = this.joueurs.iterator();
//        }
//        return joueursIt.next();
//    }
//
//    /**
//     * permet de changer le sens du jeu
//     */
//    public void changerSens() {
//        ArrayList<Joueur> list = new ArrayList<Joueur>(this.joueurs); //creer une liste à partir d'un set
//        Collections.reverse(list); //inverser l'ordre des éléments dans la liste
//
//        this.joueurs = new LinkedHashSet<Joueur>(list); //transformer la liste inversée en set
//
//        Iterator<Joueur> iterator = this.joueurs.iterator();
//
//        //Il faut mettre à jour l'itérateur des joueurs pour que le prochain joueur a jouer respecte le nouvel ordre du set
//        Joueur joueur;
//        do {
//            joueur = iterator.next();
//            if (joueur == this.joueurQuiJoue) {
//                this.joueursIt = iterator;
//                this.prochainJoueurQuiVaJouer = null; // le prochain joueur n'est plus bon !
//            }
//        } while (iterator.hasNext() && joueur != this.joueurQuiJoue);
//
//        System.out.println(this.joueurs);
//        System.out.println(this.joueurQuiJoue);
//        System.out.println(this.getProchainJoueurQuiVaJouer());
//    }
//
//    /**
//     * demande au joueur de jouer un coup
//     */
//    public void jouerCoup() {
//        this.fenetre.annonceJoueurQuiJoue(this.joueurQuiJoue.getNom());
//        this.afficherConsigneCoupAJouer();
//        Carte carteJouee;
//        // System.out.println(this.joueurQuiJoue.getMain());
//
//        if (this.joueurQuiJoue instanceof Ia) {
//            carteJouee = this.joueurQuiJoue.jouer(this.carteDefausse);
//
//        } else {
//            this.fenetre.queVeuxJouerLeJoueur();
//
//            boolean isCarteJouee = false;
//            do {
//                this.fenetre.afficherOptionsJouables(this.joueurQuiJoue.getMain(), this.pioche.estVide());
//
//                carteJouee = this.joueurQuiJoue.jouer(this.carteDefausse);
//
//                if (this.regle.isCoupJouable(carteJouee, this.carteDefausse)) {
//                    isCarteJouee = true;
//                } else {
//                    this.fenetre.coupIllegal();
//                }
//
//            } while (!isCarteJouee);
//        }
//
//        this.dernierCoupJoue = carteJouee;
//
//        if (carteJouee == null) { //piocher
//            if (this.pioche.estVide()) { // on saute le tour
//                this.fenetre.afficherSauter(this.joueurQuiJoue.getNom(), this.joueurQuiJoue.getMain().getNombreDeCartes());
//            } else {
//                this.joueurQuiJoue.piocher(this.pioche.retirerCarte());
//                this.fenetre.afficherPiocher(this.joueurQuiJoue.getNom(), this.joueurQuiJoue.getMain().getNombreDeCartes());
//            }
//        } else {
//            if (this.carteDefausse != null) {
//                this.pioche.ajouterCarte(this.carteDefausse);
//            }
//            this.joueurQuiJoue.getMain().retirerCarte(carteJouee);
//            this.carteDefausse = carteJouee;
//            this.fenetre.afficherJouer(carteJouee, this.joueurQuiJoue.getNom(), this.joueurQuiJoue.getMain().getNombreDeCartes());
//        }
//    }
//
//    /**
//     * Dit au joueur ce qui va se passer à cause des cartes speciales
//     */
//    private void afficherConsigneCoupAJouer() {
//        if (this.regle.getNouvelleCouleur() != null) {
//            this.fenetre.ilFautJouer(this.regle.getNouvelleCouleur());
//        } else if (this.regle.getCarteCreeeParJoker() != null) {
//            this.fenetre.ilFautJouer(this.regle.getCarteCreeeParJoker());
//        } else {
//            this.fenetre.afficherDefausse(this.carteDefausse);
//        }
//    }
//
//    public void finJeu() {
//    }
//
//    public Affichage getFenetre() {
//        return this.fenetre;
//    }
//
//    public Carte getDernierCoupJoue() {
//        return this.dernierCoupJoue;
//    }
//
//    public Carte retirerCartePioche() {
//        return this.pioche.retirerCarte();
//    }
//
//    public Joueur getJoueurQuiJoue() {
//        return this.joueurQuiJoue;
//    }
//
//    public Joueur getProchainJoueurQuiVaJouer() {
//        return this.prochainJoueurQuiVaJouer;
//    }
//
//    public LinkedHashSet<Joueur> getJoueurs() {
//        return this.joueurs;
//    }
//
//    public int getNbJoueurs() {
//        return this.joueurs.size();
//    }
}
