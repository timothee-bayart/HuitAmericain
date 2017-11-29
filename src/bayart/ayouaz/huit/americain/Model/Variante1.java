package bayart.ayouaz.huit.americain.Model;

import bayart.ayouaz.huit.americain.Enum.Couleur;
import bayart.ayouaz.huit.americain.Enum.Motif;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Variante1 extends Regle {
    public String nomVariante;
    
        Variante1(){
            derniereCarteJoue=null;
        }
        
	@Override
	public GroupeDeCarte initPartie() {
		GroupeDeCarte cartes = new GroupeDeCarte(1,52,true);
                return cartes;
	}

	@Override
	public boolean isCoupJouable(Carte coupJoue, Carte carteDefausse) {
		if (derniereCarteJoue==null ||
                        coupJoue == null ||
                        coupJoue.getMotif()==Motif.JOKER ||
                        coupJoue.getMotif()==Motif.HUIT ||
                        coupJoue.getMotif()==derniereCarteJoue.getMotif() ||
                        coupJoue.getCouleur() == derniereCarteJoue.getCouleur()){
                        derniereCarteJoue=coupJoue;
                        return true;
                }
                else{
                    return false;
                }
	}

	@Override
	public boolean aGagne(GroupeDeCarte paquetDuJoueurEnCours) {
		return paquetDuJoueurEnCours.getNombreDeCartes()==0;
	}

    @Override
    public void leProchainJoueurDevra(Partie p) {
        Affichage fenetre = new Affichage();
        if(derniereCarteJoue==null)
            return;
        switch(derniereCarteJoue.getMotif()){
            case DEUX:
                fenetre.piocherObligatoire(2, p.getJoueurQuiJoue());
                for(int i = 0; i < 2 ; ++i){
                    p.getJoueurQuiJoue().piocher(p.retirerCartePioche());
                }
                return;
            case SEPT:
                fenetre.sauterTour(p.getJoueurQuiJoue());
                p.changerTour();
                return;
            case HUIT:
                int sortie=-1;
                Scanner scan = new Scanner(System.in);
                do{
                    try{
                    fenetre.changerProchaineCouleur();
                    sortie = scan.nextInt();
                    if(sortie>=0 && sortie<4){
                        if(sortie==0){
                            derniereCarteJoue = new Carte(Couleur.TREFLE, Motif.TROIS);
                            fenetre.ilFautJouer(0);
                            return;
                        }
                        if(sortie==1){
                            derniereCarteJoue = new Carte(Couleur.PIC, Motif.TROIS);
                            fenetre.ilFautJouer(1);
                            return;
                        }
                        if(sortie==2){
                            derniereCarteJoue = new Carte(Couleur.COEUR, Motif.TROIS);
                            fenetre.ilFautJouer(2);
                            return;
                        }
                        if(sortie==3){
                            derniereCarteJoue = new Carte(Couleur.CARREAU, Motif.TROIS);
                            fenetre.ilFautJouer(3);
                            return;
                        }
                    }
                    }catch(InputMismatchException e) {
                            scan.next();
                    }
                }while(true);
            case DIX:
                fenetre.rejouer();
                p.changerSens();
                p.changerTour();
                p.changerSens();
                return;
            case VALET:
                fenetre.changerSens();
                p.changerSens();
                p.changerTour();
                p.changerTour();
                return;
            case AS:
                fenetre.piocherObligatoire(4, p.getJoueurQuiJoue());
                for(int i = 0; i < 4 ; ++i){
                    p.getJoueurQuiJoue().piocher(p.retirerCartePioche());
                }
            case JOKER:
                //decider prochain joueur ou echanger son jeu
        }
    }

}
