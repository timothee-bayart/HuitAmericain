
package bayart.ayouaz.huit.americain.Model;


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
    
    public void afficherJoué(Carte c, String nomJoueur, int nbCartesRestantes){
        System.out.println("<"+nomJoueur+" a joué : "+c+">");
        if(nbCartesRestantes>1)
            System.out.println(" Il lui reste "+nbCartesRestantes+" cartes");
        else
            System.out.println(" Il lui reste "+nbCartesRestantes+" carte");
    }
    
    public void afficherPioché(String nomJoueur, int nbCartesRestantes){
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
}
