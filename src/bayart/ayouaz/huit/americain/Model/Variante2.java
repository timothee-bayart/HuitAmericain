//package bayart.ayouaz.huit.americain.Model;
//
//import bayart.ayouaz.huit.americain.Enum.Couleur;
//import bayart.ayouaz.huit.americain.Enum.Motif;
//
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//import static bayart.ayouaz.huit.americain.Enum.Motif.DEUX;
//
//public class Variante2 extends Regle {
//    public String nomVariante;
//
//        Variante2(){
//            derniereCarteJoue=null;
//        }
//
//	@Override
//	public GroupeDeCarte genererPioche() {
//        return new GroupeDeCarte(1,52,true);
//	}
//
//	@Override
//	public boolean isCoupJouable(Carte coupJoue, Carte carteDefausse) {
//		if (derniereCarteJoue==null ||
//                        coupJoue == null ||
//                        coupJoue.getMotif()==Motif.JOKER ||
//                        coupJoue.getMotif()==Motif.HUIT ||
//                        coupJoue.getMotif()==derniereCarteJoue.getMotif() ||
//                        coupJoue.getCouleur() == derniereCarteJoue.getCouleur()){
//                        derniereCarteJoue=coupJoue;
//                        return true;
//                }
//                else{
//                    return false;
//                }
//	}
//
//	@Override
//	public boolean isJoueurAGagne(GroupeDeCarte paquetDuJoueurEnCours) {
//		return paquetDuJoueurEnCours.getNombreDeCartes()==0;
//	}
//
//    @Override
//    public void appliquerEffetCarteSpeciale(Partie p) {
//        if(derniereCarteJoue==null)
//            return;
//
//        switch(derniereCarteJoue.getMotif()){ //
//            // TODO documenter les regles pour la variante !
//            case DEUX: // Les 2 font font piocher + 2 cartes au joueur suivant
//                p.getFenetre().piocherObligatoire(2, p.getJoueurQuiJoue());
//                for(int i = 0; i < 2 ; ++i){
//                    p.getJoueurQuiJoue().piocher(p.retirerCartePioche());
//                }
//                break;
//
//            case SEPT:
//                p.getFenetre().sauterTour(p.getJoueurQuiJoue());
//                p.changerTour();
//                break;
//
//            case HUIT: // Les 8 permettent de changer de couleur à n’importe quel moment TODO FAUX !! le joueur courant choisit la couleur
//                int sortie=-1;
//                Scanner scan = new Scanner(System.in);
//                do{
//                    try{
//                        p.getFenetre().changerProchaineCouleur();
//                    sortie = scan.nextInt();
//                    if(sortie>=0 && sortie<4){
//                        if(sortie==0){
//                            derniereCarteJoue = new Carte(Couleur.TREFLE, Motif.TROIS);
//                            p.getFenetre().ilFautJouer(Couleur.TREFLE);
//                            return;
//                        }
//                        if(sortie==1){
//                            derniereCarteJoue = new Carte(Couleur.PIC, Motif.TROIS);
//                            p.getFenetre().ilFautJouer(Couleur.PIC);
//                            return;
//                        }
//                        if(sortie==2){
//                            derniereCarteJoue = new Carte(Couleur.COEUR, Motif.TROIS);
//                            p.getFenetre().ilFautJouer(Couleur.COEUR);
//                            return;
//                        }
//                        if(sortie==3){
//                            derniereCarteJoue = new Carte(Couleur.CARREAU, Motif.TROIS);
//                            p.getFenetre().ilFautJouer(Couleur.CARREAU);
//                            return;
//                        }
//                    }
//                    }catch(InputMismatchException e) {
//                        scan.next();
//                    }
//                }while(true);
//                break;
//
//            case DIX:
//                p.getFenetre().rejouer();
//                p.changerSens();
//                p.changerTour();
//                p.changerSens();
//                break;
//
//            case VALET: // Les Valets font sauter le tour du joueur suivant
//                p.getFenetre().changerSens();
//                p.changerSens();
//                p.changerTour();
//                p.changerTour();
//                break;
//
//            case AS: // Les As font changer le sens du jeu
//                p.getFenetre().piocherObligatoire(4, p.getJoueurQuiJoue());
//                for(int i = 0; i < 4 ; ++i){
//                    p.getJoueurQuiJoue().piocher(p.retirerCartePioche());
//                }
//                break;
//
//            case JOKER: // Les Jokers font piocher + 4 cartes au joueur suivant
//                //decider prochain joueur ou echanger son jeu
//                break;
//        }
//    }
//
//}
