
package bayart.ayouaz.huit.americain.Model;

//import sun.awt.X11.XConstants;


public class Affichage {
    private final String[] MENU_PRINCIPAL = new String[] {
        	"Démarrer la partie",
        	"Ajouter un joueur",
        	"Quitter"
    };
    private String ligne;
    
    
    public Affichage(){
        ligne="";
        for(int i=0; i<15;++i)
            ligne+="*";
    }
    
    
    
    
    public static void erreurInput(){
        System.out.println("Mauvaise entrée !");
    }
    
    public void afficherMenu(){
        System.out.println(ligne);
        for(int i=0; i<MENU_PRINCIPAL.length;++i){
            System.out.println("["+i+"] : "+MENU_PRINCIPAL[i]);
        }
    }
    public void queVeuxJouerLeJoueur(){
        System.out.println("Que souhaitez vous jouer?");    
    }
    
    public void coupIllegal(){
        System.out.println("Coup non jouable. Quel coup souhaitez-vous jouer ?");
    }
    
    public void afficherOptionsJouables(GroupeDeCarte hand){
        //proposer piocher
        System.out.println("[0] : Piocher");
        //proposer les cartes
        System.out.println(hand);
    }
    
    public void afficherJouer(Carte c, String nomJoueur, int nbCartesRestantes){
        System.out.println("<"+nomJoueur+" a joué : "+c+">");
        if(nbCartesRestantes>1)
            System.out.println(" Il lui reste "+nbCartesRestantes+" cartes");
        else
            System.out.println(" Il lui reste "+nbCartesRestantes+" carte");
    }
    
    public void afficherDefausse(Carte defausse){
    	System.out.println("-> Défausse : "+defausse+" \n");
    }
    
    public void afficherPiocher(String nomJoueur, int nbCartesRestantes){
        System.out.println("<"+nomJoueur+" a pioché>");
        System.out.println(" Il lui reste "+nbCartesRestantes+" cartes");
    }
    
    public void annonceJoueurQuiJoue(String nomJoueur){
        System.out.println("--- À "+nomJoueur+" de jouer ---");
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
    public void rejouer(){
        System.out.println("Rejouer");
    }
    public void changerProchaineCouleur(){
        System.out.println("Quel couleur voulez vous jouer ensuite?");
        System.out.println("[0] : Trefle");
        System.out.println("[1] : Pic");
        System.out.println("[2] : Coeur");
        System.out.println("[3] : Carreau");
    }
    public void ilFautJouer(int id){
        if(id==0)
            System.out.println("Il faut jouer un Trefle");
        if(id==1)
            System.out.println("Il faut jouer un Pic");
        if(id==2)
            System.out.println("Il faut jouer un Coeur");
        if(id==3)
            System.out.println("Il faut jouer un Carreau");
        
    }
}