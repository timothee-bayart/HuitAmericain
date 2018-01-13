package bayart.ayouaz.huit.americain.view;

import bayart.ayouaz.huit.americain.controller.PartieGraphique;
import static bayart.ayouaz.huit.americain.model.Affichage.MENU_PRINCIPAL;
import bayart.ayouaz.huit.americain.model.Carte;
import bayart.ayouaz.huit.americain.model.GroupeDeCarte;
import bayart.ayouaz.huit.americain.model.Ia;
import bayart.ayouaz.huit.americain.model.Input;
import bayart.ayouaz.huit.americain.model.Joueur;
import bayart.ayouaz.huit.americain.model.Regle;
import bayart.ayouaz.huit.americain.model.enums.Couleur;
import bayart.ayouaz.huit.americain.model.enums.Motif;

import java.util.LinkedHashSet;
import java.util.Observable;
import java.util.Observer;

public class ViewTerminal implements Observer , IHM{
    private PartieGraphique controleur;
    public static final String[] MENU_PRINCIPAL = new String[] {
        	"Démarrer la partie",
        	"Ajouter un joueur",
        	"Choisir la variante",
        	"Quitter"
    };
    
    @Override
    public void update(Observable o, Object j) {
        if( o instanceof Joueur && j instanceof String && j == "ajouteJoueur"){
            this.afficherMenuDepart();
        }
        if( o instanceof Joueur && j instanceof String && j == "piocher"){
            //this.afficherPlateau(this.controleur.getJoueurs(), this.controleur.getCarteDefausse());
        }
        if( o instanceof Carte && j instanceof String && j == "setDefausse"){
            //this.afficherPlateau(this.controleur.getJoueurs(), this.controleur.getCarteDefausse());
        }
        
    }

    public ViewTerminal(PartieGraphique p) {
        controleur = p;
        controleur.setFenetre(this);
    }

    @Override
    public void afficherPlateau(LinkedHashSet<Joueur> joueurs, Carte defausse) {
        System.out.println("defausse : "+defausse);
        int taille=0;
        GroupeDeCarte gc = null;
        for(Joueur joueur : joueurs){
            if(!(joueur instanceof Ia)){
                gc = joueur.getMain();
                taille=gc.getNombreDeCartes();
                for(int i=0; i<gc.getNombreDeCartes();++i){
                    System.out.println("["+i+"] "+gc.getCarte(i));
                }
            }
        }
        controleur.carteSelectionnee(gc.getCarte(Input.demanderEntier(0, taille)));
    }

    @Override
    public void afficherVariantesDepart(Regle[] variante, Regle varianteSelected) {
        System.out.println("Veuillez choisir la variante");
        for(int i=0; i<variante.length;++i){
            if(variante[i] == varianteSelected)
                System.out.println("["+i+"] "+variante[i] + " (actuel)");
            else
                System.out.println("["+i+"] "+variante[i]);
        }
            controleur.choisirVariante(variante[Input.demanderEntier(0, variante.length)]);
    }

    @Override
    public void afficherMessage(String message) {
    }

    @Override
    public void afficherPseudoDepart() {
        System.out.println("Veuillez saisir votre nom (alphabétique) de joueur");
        controleur.ajouterJoueur(Input.demanderString());
    }

    @Override
    public void afficherMenuDepart() {
        for(int i=0; i<MENU_PRINCIPAL.length;++i){
            System.out.println("["+i+"] : "+MENU_PRINCIPAL[i]);
        }
        switch(Input.demanderEntier(0, MENU_PRINCIPAL.length)){
            case 0:
                controleur.demarrer();
                break;
            case 1:
                System.out.println("Ajout d'un joueur");
                controleur.ajouterIA();
                break;
            case 2:
                controleur.choisirRegle();
                break;
            case 3:
                System.exit(0);
                break;
        }
        
    }

    @Override
    public void afficherPartieTerminee(Joueur[] joueurs) {
        System.out.println(joueurs[0] + " remporte la partie!");
    }

    @Override
    public void piocherObligatoire(int nbCartes, Joueur QuiPioche) {
        if(nbCartes > 1)
            System.out.println(QuiPioche.getNom() + " pioche " + nbCartes + " cartes.");
        else
            System.out.println(QuiPioche.getNom() + " pioche " + nbCartes + " carte.");
    }

    @Override
    public void sauterTour(Joueur quiVaEtreSauter) {
        System.out.println("On saute le tour de " + quiVaEtreSauter.getNom());
    }

    @Override
    public int changerProchaineCouleur(Couleur[] couleurs) {
        System.out.println("Quel couleur voulez vous jouer ensuite ?");
        for(int i=0; i<couleurs.length; i++){
            System.out.println("["+i+"] : " + couleurs[i]);
        }
        return Input.demanderEntier(0, couleurs.length);
    }

    @Override
    public void ilFautJouer(Couleur couleur) {
        System.out.println("-> Il faut jouer un " + couleur);
    }

    @Override
    public void ilFautJouer(Carte carte) {
        System.out.println("-> Il faut jouer un(e) " + carte.getMotif() + " ou un " + carte.getCouleur());
    }

    @Override
    public void rejouer(Joueur quiRejoue) {
        System.out.println(quiRejoue + " va rejouer");
    }

    @Override
    public int fairePiocherJoueur(Joueur[] quiVaPiocher) {
        System.out.println("Quel joueur souhaitez-vous faire piocher ?");
        for(int i=0; i<quiVaPiocher.length; i++){
            System.out.println("["+i+"] : " + quiVaPiocher[i]);
        }
        return Input.demanderEntier(0, quiVaPiocher.length);
    }

    @Override
    public void changerSens() {
        System.out.println("On change de sens");
    }

    @Override
    public int changerProchainMotif(Motif[] motifs) {
        System.out.println("Quel motif voulez vous jouer ensuite ?");
        for(int i=0; i<motifs.length; i++){
            System.out.println("["+i+"] : " + motifs[i]);
        }
        return Input.demanderEntier(0, motifs.length);
    }

    @Override
    public void jokerPrendApparence(Carte carte) {
        System.out.println("Le joker prend l'apparance d'un "+carte);
    }

    @Override
    public String getDernierMessage() {
        return null;
    }
}
