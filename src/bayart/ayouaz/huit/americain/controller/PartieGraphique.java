package bayart.ayouaz.huit.americain.controller;

import bayart.ayouaz.huit.americain.model.enums.*;
import bayart.ayouaz.huit.americain.model.*;
import bayart.ayouaz.huit.americain.view.IHM;
import bayart.ayouaz.huit.americain.view.ViewTerminal;

import java.io.*;
import java.util.*;

/**
 *
 * Cette classe fait office de tronc centrale dans le jeu. C'est elle qui
 * appelle toute les bonnes fonctions des bonnes classes, dans le bon ordre.
 * Dans le design pattern MVC, elle fait aussi office de controlleur
 */
public class PartieGraphique implements Serializable {

    private final static int NOMBRE_DE_CARTE_A_DISTRIBUER = 7;
    private final static int NOMBRE_DE_JOUEURS_MINIMUM = 2;
    private final static String PARTIE_GRAPHIQUE_FILE_PATH = "res/partieGraphique.ser";

    private Regle regle;
    private GroupeDeCarte pioche;
    private Carte carteDefausse;
    private Carte dernierCoupJoue;
    private LinkedHashSet<Joueur> joueurs = new LinkedHashSet<Joueur>();
    private Joueur joueurQuiJoue;
    private Joueur prochainJoueurQuiVaJouer;
    private Joueur joueurReel;
    private transient Iterator<Joueur> joueursIt;
    private Regle[] variantes;
    private IHM fen;
    private boolean partieEnCours = false;

    public void setFenetre(IHM v) {
        fen = v;
        this.fen.afficherPseudoDepart();
    }
    /**
     * constructeur
     */
    public PartieGraphique() {
        this.variantes = new Regle[]{
            new Variante1(1),
            new Variante2(1)
        };

        regle = this.variantes[1];
    }

    /**
     * Cette methode va initialiser le jeur. Demander le nom des joueurs, la
     * variante, et va générer la pioche.
     * @param nom le nom du joueur à ajouter
     */
    public void ajouterJoueur(String nom) {
        if (Joueur.isNomValide(nom)) {
            Joueur j = new Joueur(nom);
            if (this.joueurs.isEmpty()) {
                joueurReel = j;
            }
            this.joueurs.add(j);
            if(fen instanceof Observer)
                j.addObserver((Observer)fen);          //ajouter l'observateur sur joueur

            j.notifyObservers("ajouteJoueur");
            this.fen.afficherMessage(
                    "<html><center>************* Huit Américain (Timothée BAYART & Idris AYOUAZ *************<br />"+
                            this.joueurs.size()+ " joueur(s) prêts<br />"+
                            "Jeu en "+this.regle+"<center></html>"
            );
        } else {
            this.fen.afficherMessage("Veuillez saisir votre nom (alphabétique) de joueur");
            if(this.fen instanceof ViewTerminal)
                this.fen.afficherPseudoDepart();
        }
    }
    /**
     * Cette methode permet d'ajouter une intelligence artificielle dans la partie.
     */
    public void ajouterIA(){
        int numeroJoueur = 1;
        Joueur nouveauJoueur;
        do {
            nouveauJoueur = new Ia("Joueur"+(this.getNbJoueurs()+numeroJoueur), this.regle);
            numeroJoueur++;
        } while(!this.joueurs.add(nouveauJoueur));

        if(fen instanceof Observer)
            nouveauJoueur.addObserver((Observer)fen);          //ajouter l'observateur sur joueur
            nouveauJoueur.notifyObservers("ajouteJoueur");

            this.fen.afficherMessage(
                "<html><center>************* Huit Américain (Timothée BAYART & Idris AYOUAZ *************<br />"+
                this.joueurs.size()+ " joueur(s) prêts<br />"+
                "Jeu en "+this.regle+"<center></html>"
            );
    }
    /**
     * Cette methode permet d'appeler la fenetre pour lui dire d'afficher la liste des regles.
     */
    public void choisirRegle() {
        fen.afficherVariantesDepart(this.variantes, this.regle);
    }
    /**
     * Cette methode va changer la regle du jeu courante.
     * @param regle c'est la nouvelle regle qui sera utilisée
     */
    public void choisirVariante(Regle regle) {
        this.regle = regle;

        //si déjà des Ia de créées, mettre a jour la regle !
        for(Joueur joueur : this.joueurs){
            if(joueur instanceof Ia){
                ((Ia) joueur).setRegle(this.regle);
            }
        }

        fen.afficherMenuDepart();
        this.fen.afficherMessage(
                "<html><center>************* Huit Américain (Timothée BAYART & Idris AYOUAZ *************<br />"+
                        this.joueurs.size()+ " joueur(s) prêts<br />"+
                        "Jeu en "+this.regle+"<center></html>"
        );
    }
    /**
     * Cette methode permet de distribuer toutes les cartes à tous les joueurs. 
     * Et d'en mettre une sur la défausse.
     */
    public void distribuerCartes() {
        for (int i = 0; i < NOMBRE_DE_CARTE_A_DISTRIBUER; i++) {
            this.joueursIt = this.joueurs.iterator();
            while (joueursIt.hasNext()) {
                joueursIt.next().getMain().ajouterCarte(this.pioche.retirerCarte());
            }
        }
        this.carteDefausse = new Carte();
        if(fen instanceof Observer)
            this.carteDefausse.addObserver((Observer)fen);
        do {
            this.carteDefausse.setDefausse(this.pioche.retirerCarte());
            if (this.carteDefausse.getMotif() == Motif.JOKER) {
                this.pioche.ajouterCarte(this.carteDefausse);
                this.carteDefausse = null;
            }
        } while (this.carteDefausse == null);
    }
    /**
     * Cette methode va initialiser la partie. Elle vérifie avant tout si le bon
     * nombre de joueur est présent et si une regle a bien été choisie
     */
    public void demarrer() {
        if (regle != null && joueurs.size() >= PartieGraphique.NOMBRE_DE_JOUEURS_MINIMUM) {
            this.partieEnCours = true;
            this.pioche = this.regle.genererPioche(this.joueurs.size());
            this.pioche.melanger();
            this.joueursIt = this.joueurs.iterator();
            this.distribuerCartes();
            ThreadIA tia = new ThreadIA(this);
            tia.start();
            changerTour();
            if(fen instanceof ViewTerminal)
                fen.afficherPlateau(joueurs, carteDefausse);
        } else {
            this.fen.afficherMessage("Veuillez ajouter au moins "+ (NOMBRE_DE_JOUEURS_MINIMUM - this.getNbJoueurs()) +" joueur(s) pour commencer");
            System.out.println("Veuillez ajouter au moins "+ (NOMBRE_DE_JOUEURS_MINIMUM - this.getNbJoueurs()) +" joueur(s) pour commencer");

            if(fen instanceof ViewTerminal)
                fen.afficherMenuDepart();
        }
    }
    /**
     * methode du controleur apres que l'interface ait reçu un clique de bouton
     * pour choisir une carte.
     * @param carte c'est la carte que veut jouer le joueur.
     */
    public void carteSelectionnee(Carte carte){
        if(!(joueurQuiJoue instanceof Ia)){
            this.jouerCarte(carte);
        }else{
            fen.afficherPlateau(joueurs, carteDefausse);
        }
    }
    /**
     * Cette methode permet de tester si le joueur peut jouer le coup qu'il demande.
     * Elle vérifie si le joueur veut piocher. Et enfin, elle applique les effets
     * des cartes si necessaire.
     * @param carte La carte que le joueur veut jouer. Null si le joueur veut piocher.
     */
    public void jouerCarte(Carte carte) {
        if(carte == null){ //piocher
            this.dernierCoupJoue = null;
            joueurQuiJoue.piocher(pioche.retirerCarte());

            this.regle.appliquerEffetCarteSpeciale(this);
            changerTour();
            this.joueurQuiJoue.setPeutJouer(true);

        } else if(regle.isCoupJouable(carte, carteDefausse)){ //on a posé une carte
            this.dernierCoupJoue = carte;
            this.pioche.ajouterCarte(this.carteDefausse);
            this.carteDefausse.setDefausse(joueurQuiJoue.getMain().retirerCarte(carte));

            if (regle.isJoueurAGagne(joueurQuiJoue.getMain())) {
                this.partieEnCours = false;
                this.fen.afficherPartieTerminee(this.getClassement());
            } else {
                this.regle.appliquerEffetCarteSpeciale(this);
                changerTour();
                this.joueurQuiJoue.setPeutJouer(true);
            }
        }
        if(fen instanceof ViewTerminal)
            fen.afficherPlateau(joueurs, carteDefausse);
    }

    /**
     * Cette methode permet d'obtenir le joueur qui jouera au prochain tour
     * @return le prochain joueur.
     */
    private Joueur getProchainJoueur() {
        if (!this.joueursIt.hasNext()) {
            this.joueursIt = this.joueurs.iterator();
        }
        return joueursIt.next();
    }
    /**
     * Cette methode change le tour du jeu et passe au joueur suivant.
     */
    public synchronized void changerTour() {
        if (this.prochainJoueurQuiVaJouer != null) {
            this.joueurQuiJoue = this.prochainJoueurQuiVaJouer;
        } else {
            this.joueurQuiJoue = this.getProchainJoueur();
        }

        this.fen.afficherMessage("A "+this.joueurQuiJoue+" de jouer !");

        if (this.regle.getNouvelleCouleur() != null) {
            this.fen.ilFautJouer(this.regle.getNouvelleCouleur());
        } else if (this.regle.getCarteCreeeParJoker() != null) {
            this.fen.ilFautJouer(this.regle.getCarteCreeeParJoker());
        }

        this.prochainJoueurQuiVaJouer = this.getProchainJoueur();
    }
    /**
     * Cette methode permet d'obtenir la derniere carte jouée
     * @return la derniere carte jouée
     */
    public Carte getDernierCoupJoue() {
        return this.dernierCoupJoue;
    }
    /**
     * Cette methode est un getter pour l'objet de type IHM de la classe
     * @return l'objet IHM de la classe
     */
    public IHM getFenetre(){
        return fen;
    }
    /**
     * Cette methode permet d'obtenir le joueur qui doit jouer
     * @return le joueur qui joue
     */
    public Joueur getJoueurQuiJoue() {
        return this.joueurQuiJoue;
    }
    
    /**
     * Permet d'obtenir un tableau contenant tous les joueurs.
     * @return le tableau de joueurs
     */
    public LinkedHashSet<Joueur> getJoueurs() {
        return this.joueurs;
    }
    /**
     * Permet d'obtenir la carte sur le dessus de la defausse
     * @return la carte de la défausse
     */
    public Carte getCarteDefausse(){
        return this.carteDefausse;
    }
    /**
     * Permet d'obtenir le nombre de joueur actuellement présents dans le jeu.
     * @return le nombre de joueurs.
     */
    public int getNbJoueurs() {
        return this.joueurs.size();
    }
    /**
     * Cette methode permet de prendre une carte de la pioche
     * @return la carte au sommet de la pioche.
     */
    public Carte retirerCartePioche() {
        return this.pioche.retirerCarte();
    }
    /**
     * Cet getter permet d'obtenir le joueur qui jouera au prochain tour.
     * @return Le prochain joueur
     */
    public Joueur getProchainJoueurQuiVaJouer() {
        return this.prochainJoueurQuiVaJouer;
    }
    /**
     * Cette methode permet de changer le sens du jeu.
     */
    public void changerSens() {
        ArrayList<Joueur> list = new ArrayList<Joueur>(this.joueurs); //creer une liste à partir d'un set
        Collections.reverse(list); //inverser l'ordre des éléments dans la liste

        this.joueurs = new LinkedHashSet<Joueur>(list); //transformer la liste inversée en set

        Iterator<Joueur> iterator = this.joueurs.iterator();

        //Il faut mettre à jour l'itérateur des joueurs pour que le prochain joueur a jouer respecte le nouvel ordre du set
        Joueur joueur;
        do {
            joueur = iterator.next();
            if (joueur == this.joueurQuiJoue) {
                this.joueursIt = iterator;
                this.prochainJoueurQuiVaJouer = null; // le prochain joueur n'est plus bon !
            }
        } while (iterator.hasNext() && joueur != this.joueurQuiJoue);
    }
    /**
     * Cette methode permet de verifier si la partie est sauvegardée
     * @return un boolean indiquant si la partie est sauvegardée ou non.
     */
    public boolean isPartieSauvegardee(){
        File file = new File(PARTIE_GRAPHIQUE_FILE_PATH);
        return (file.exists() && !file.isDirectory());
    }
    /**
     * Cette methode permet de charger un partie sauvegardée.
     */
    public void chargerPartieSauvegardee(){
        if(this.isPartieSauvegardee()){
            try{
                FileInputStream fis = new FileInputStream(PARTIE_GRAPHIQUE_FILE_PATH);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream input = new ObjectInputStream (bis);
                PartieGraphique partieGraphique = (PartieGraphique) input.readObject();

                this.partieEnCours = partieGraphique.partieEnCours;
                this.regle = partieGraphique.regle;
                this.pioche = partieGraphique.pioche;
                this.carteDefausse = partieGraphique.carteDefausse;
                this.dernierCoupJoue = partieGraphique.dernierCoupJoue;
                this.joueurQuiJoue = partieGraphique.joueurQuiJoue;
                this.prochainJoueurQuiVaJouer = partieGraphique.prochainJoueurQuiVaJouer;
                this.joueurReel = partieGraphique.joueurReel;

                this.joueurs = partieGraphique.joueurs;
                this.variantes = partieGraphique.variantes;

                //Il faut mettre à jour l'itérateur
                this.joueursIt = this.joueurs.iterator();
                Joueur joueur;
                do {
                    joueur = this.joueursIt.next();
                } while (joueur != this.joueurQuiJoue);

                for(Joueur j : this.joueurs){
                    j.addObserver((Observer)this.fen);
                }
                this.carteDefausse.addObserver((Observer)this.fen);


                this.fen.afficherPlateau(this.joueurs, this.carteDefausse);
                this.fen.afficherMessage(partieGraphique.getFenetre().getDernierMessage());
                ThreadIA tia = new ThreadIA(this);
                tia.start();


            } catch (IOException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Cette methode permet de sauvegarder une partie
     */
    public void sauvegarderPartie() {

        if(!this.isPartieSauvegardee()){
            try {
                new File(PARTIE_GRAPHIQUE_FILE_PATH).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(PARTIE_GRAPHIQUE_FILE_PATH);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    /**
     * Cette methode permet de quitter le jeu en tuant le processus
     */
    public void quitterJeu() {
        System.exit(0);
    }
    /**
     * Cette methode permet d'obtenir le classement apres la fin de la partie
     * @return les joueurs dans l'ordre du meilleur au pire.
     */
    public Joueur[] getClassement(){
        Joueur[] classement = this.joueurs.toArray(new Joueur[this.joueurs.size()]);
        //on fait un tri par insertion pour trier les jours en fonction du nombre de carte en main
        int n = classement.length-1;

        for (int i=2; i<=n; i++){
            Joueur joueur = classement[i];
            int indexValeur = i;

            while(classement[indexValeur-1].getMain().getNombreDeCartes() > joueur.getMain().getNombreDeCartes()){
                classement[indexValeur] = classement[indexValeur-1];
                indexValeur--;
            }
            classement[indexValeur] = joueur ;
        }

        return classement;
    }
    /**
     * Cette methode vérifie si la partie est en cours ou si elle est finie.
     * @return un boolean indiquant l'état de la partie.
     */
    public boolean isPartieEnCours() {
        return partieEnCours;
    }
}
