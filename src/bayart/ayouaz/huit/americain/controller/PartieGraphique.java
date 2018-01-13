package bayart.ayouaz.huit.americain.controller;

import bayart.ayouaz.huit.americain.model.enums.*;
import bayart.ayouaz.huit.americain.model.*;
import bayart.ayouaz.huit.americain.view.IHM;
import bayart.ayouaz.huit.americain.view.ViewGraphic;
import bayart.ayouaz.huit.americain.view.ViewTerminal;

import java.io.*;
import java.util.*;

/**
 *
 * Cette classe fait office de tronc centrale dans le jeu. C'est elle qui
 * appelle toute les bonnes fonctions des bonnes classes, dans le bon ordre.
 * Dans le design pattern MVC, c'est le controlleur
 */
public class PartieGraphique implements Serializable {

    private final static int NOMBRE_DE_CARTE_A_DISTRIBUER = 7;
    private final static int NOMBRE_DE_JOUEURS_MINIMUM = 2;
    private final static String PARTIE_GRAPHIQUE_FILE_PATH = "res/partieGraphique.ser";

    private Regle regle;
//    private int nbManche;
//    private int nbMancheTotal;
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
//    private Joueur gagnant;
//    private TreeSet<Joueur> perdants = new TreeSet<Joueur> ();

    public void setFenetre(IHM v) {
        fen = v;
        this.fen.afficherPseudoDepart();
    }

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
        }
    }
    
    public void ajouterIA(){
        int numeroJoueur = 1;
        Joueur nouveauJoueur;
        do {
            nouveauJoueur = new Ia("Joueur"+(this.getNbJoueurs()+numeroJoueur), this.regle);
            numeroJoueur++;
        } while(!this.joueurs.add(nouveauJoueur));

//        this.joueurs.add(nouveauJoueur);
        if(fen instanceof Observer)
            nouveauJoueur.addObserver((Observer)fen);          //ajouter l'observateur sur joueur
            nouveauJoueur.notifyObservers("ajouteJoueur");

            this.fen.afficherMessage(
                "<html><center>************* Huit Américain (Timothée BAYART & Idris AYOUAZ *************<br />"+
                this.joueurs.size()+ " joueur(s) prêts<br />"+
                "Jeu en "+this.regle+"<center></html>"
            );
        if(fen instanceof ViewTerminal)
            fen.afficherMenuDepart();
    }

    public void choisirRegle() {
        fen.afficherVariantesDepart(this.variantes, this.regle);
    }

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
        if(fen instanceof ViewTerminal)
            fen.afficherMenuDepart();
    }

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
        } else {
            this.fen.afficherMessage("Veuillez ajouter au moins "+ (NOMBRE_DE_JOUEURS_MINIMUM - this.getNbJoueurs()) +" joueur(s) pour commencer");
            System.out.println("Veuillez ajouter au moins "+ (NOMBRE_DE_JOUEURS_MINIMUM - this.getNbJoueurs()) +" joueur(s) pour commencer");
        }
        if(fen instanceof ViewTerminal)
            fen.afficherPlateau(joueurs, carteDefausse);
    }

    public void carteSelectionnee(Carte carte){
        if(!(joueurQuiJoue instanceof Ia)){
            this.jouerCarte(carte);
        }else{
            fen.afficherPlateau(joueurs, carteDefausse);
        }
    }

    public void jouerCarte(Carte carte) {
        System.out.println(joueurQuiJoue);
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
            System.out.println(this.pioche.getNombreDeCartes());

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

    private Joueur getProchainJoueur() {
        if (!this.joueursIt.hasNext()) {
            this.joueursIt = this.joueurs.iterator();
        }
        return joueursIt.next();
    }

    public synchronized void changerTour() {
        System.out.println("zae");
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

    public Carte getDernierCoupJoue() {
        return this.dernierCoupJoue;
    }
    
    public IHM getFenetre(){
        return fen;
    }
    
    public Joueur getJoueurQuiJoue() {
        return this.joueurQuiJoue;
    }
    

    public LinkedHashSet<Joueur> getJoueurs() {
        return this.joueurs;
    }

    public Carte getCarteDefausse(){
        return this.carteDefausse;
    }

    public int getNbJoueurs() {
        return this.joueurs.size();
    }

    public Carte retirerCartePioche() {
        return this.pioche.retirerCarte();
    }
    
    public Joueur getProchainJoueurQuiVaJouer() {
        return this.prochainJoueurQuiVaJouer;
    }
    
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

    public boolean isPartieSauvegardee(){
        File file = new File(PARTIE_GRAPHIQUE_FILE_PATH);
        return (file.exists() && !file.isDirectory());
    }

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

    public void quitterJeu() {
        System.exit(0);
    }

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

    public boolean isPartieEnCours() {
        return partieEnCours;
    }
}
