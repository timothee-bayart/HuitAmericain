
package bayart.ayouaz.huit.americain.Model;

//import sun.awt.X11.XConstants;


import bayart.ayouaz.huit.americain.Enum.Couleur;
import bayart.ayouaz.huit.americain.Enum.Motif;
import com.sun.xml.internal.ws.util.StringUtils;

import java.util.LinkedHashSet;
/**
 * Cette classe permet de faire toute les interactions avec le terminal de type affichage.
 * Dans le modele MVC, c'est la Vue.
 */
public class Affichage {

    public static final String[] MENU_PRINCIPAL = new String[] {
        	"Démarrer la partie",
        	"Ajouter un joueur",
        	"Choisir la variante",
        	"Quitter"
    };
    private String ligne;
    
    
    public Affichage(){
        ligne="";
        for(int i=0; i<15;++i)
            ligne+="*";
    }


    public void afficherMenuPrincipal(){
        System.out.println(ligne);
        for(int i=0; i<MENU_PRINCIPAL.length;++i){
            System.out.println("["+i+"] : "+MENU_PRINCIPAL[i]);
        }
    }

    public void choisirVariante(Regle[] variantes, Regle varianteSelectionnee){
        System.out.println(ligne);
        System.out.println("Veuillez choisir la variante :");
        for(int i=0; i<variantes.length;++i){
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            sb.append(i);
            sb.append("] : ");
            sb.append(StringUtils.capitalize(variantes[i].toString()));

            if(variantes[i].equals(varianteSelectionnee)){
                sb.append(" ✘");
            }

            System.out.println(sb.toString());
        }
    }

    public void afficherVarianteSelectionee(Regle variante){
        System.out.println("Vous allez jouer avec la " + variante);
    }

    public void queVeuxJouerLeJoueur(){
        System.out.println("Que souhaitez vous jouer?");    
    }
    
    public void coupIllegal(){
        System.out.println("Coup non jouable. Quel coup souhaitez-vous jouer ?");
    }
    
    public void afficherOptionsJouables(GroupeDeCarte main, boolean piocheEstVide){
        //proposer sauter son tour
        if(piocheEstVide){
            System.out.println("[0] : Sauter son tour");
        }
        else{//proposer piocher
            System.out.println("[0] : Piocher");
        }
        //proposer les cartes
        System.out.println(main);
    }
    
    public void afficherJouer(Carte c, String nomJoueur, int nbCartesRestantes){
        System.out.println("<"+nomJoueur+" a joué : "+c+">");
        if(nbCartesRestantes>1)
            System.out.println("Il lui reste "+nbCartesRestantes+" cartes");
        else
            System.out.println("Il lui reste "+nbCartesRestantes+" carte");
    }
    
    public void afficherDefausse(Carte defausse){
    	System.out.println("-> Défausse : "+defausse);
    }
    
    public void afficherPiocher(String nomJoueur, int nbCartesRestantes){
        System.out.println("<"+nomJoueur+" a pioché>");
        System.out.println(" Il lui reste "+nbCartesRestantes+" cartes");
    }
    
    public void afficherSauter(String nomJoueur, int nbCartesRestantes){
        System.out.println("<"+nomJoueur+" a sauté son tour, la pioche est vide>");
        System.out.println(" Il lui reste "+nbCartesRestantes+" cartes");
    }
    
    public void annonceJoueurQuiJoue(String nomJoueur){
        System.out.println("\n--- À "+nomJoueur+" de jouer ---");
    }
    
    public void debutPartie(){
        System.out.println(ligne);
        System.out.println("La partie commence.");
    }
    
    public void victoire(String nomJoueur){
        String out="";
        out=ligne+"\nVictoire du joueur : " + nomJoueur+"\n"+ligne;
        System.out.println(out);
    }
    
    public void intro(){
        System.out.println("************* Huit Américain (Timothée BAYART & Idris AYOUAZ *************");
    }
    
    public void demanderNomJoueur(){
        System.out.println("Veuillez saisir votre nom (alphabétique) de joueur : ");
    }
    
    public void ajouterJoueurs(int joueurManquant){
        System.out.println("Veuillez ajouter au moins "+ joueurManquant +" joueur(s) pour commencer.");
    }
    public void joueurEnPlus(int nbJoueurs){
        System.out.println("Vous êtes désormais "+nbJoueurs+" joueurs.");
    }
    
    public void piocherObligatoire(int nbCartes, Joueur nom){
        if(nbCartes > 1)
            System.out.println(nom.getNom() + " pioche " + nbCartes + " cartes.");
        else
            System.out.println(nom.getNom() + " pioche " + nbCartes + " carte.");
    }
    public void sauterTour(Joueur nom){
        System.out.println("On saute le tour de " + nom.getNom());
    }
    public void changerSens(){
        System.out.println("On change de sens");
    }

    public void rejouer(Joueur joueur){
        //bug le joueur va rejouer mauvais
        System.out.println(joueur + " va rejouer");
    }

    public void changerProchaineCouleur(Couleur[] couleurs){
        System.out.println("Quel couleur voulez vous jouer ensuite ?");
        for(int i=0; i<couleurs.length; i++){
            System.out.println("["+i+"] : " + couleurs[i]);
        }
    }

    public void changerProchainMotif(Motif[] motifs){
        System.out.println("Quel motif voulez vous jouer ensuite ?");
        for(int i=0; i<motifs.length; i++){
            System.out.println("["+i+"] : " + motifs[i]);
        }
    }

    public void jokerPrendApparence(Carte carte){
        System.out.println("Le joker prend l'apparance d'un "+carte);
    }


    public void fairePiocherJoueur(Joueur[] joueurs){
        System.out.println("Quel joueur souhaitez-vous faire piocher ?");
        for(int i=0; i<joueurs.length; i++){
            System.out.println("["+i+"] : " + joueurs[i]);
        }
    }

    public void ilFautJouer(Carte carte){
        System.out.println("-> Il faut jouer un(e) " + carte.getMotif() + " ou un " + carte.getCouleur());
    }

    public void ilFautJouer(Couleur couleur){
        System.out.println("-> Il faut jouer un " + couleur);
    }
}