package bayart.ayouaz.huit.americain.Model;

import bayart.ayouaz.huit.americain.Enum.Couleur;
import bayart.ayouaz.huit.americain.Enum.Motif;

import static bayart.ayouaz.huit.americain.Enum.Motif.*;
import static bayart.ayouaz.huit.americain.Enum.Motif.HUIT;

public class Variante1 extends Regle {

    public String nomVariante;
    
    Variante1(){
        nouvelleCouleur = null;
    }
        
	@Override
	public GroupeDeCarte genererPioche() {
        return new GroupeDeCarte(1,52,true);
	}

	@Override
	public boolean isCoupJouable(Carte coupJoue, Carte carteDefausse) {
		if (
            coupJoue == null ||
            coupJoue.getMotif() == JOKER ||
            coupJoue.getMotif() == HUIT){

            return true;

        } else if(
                coupJoue.getMotif() == carteDefausse.getMotif() || coupJoue.getCouleur() == carteDefausse.getCouleur() ||
                (super.nouvelleCouleur != null && coupJoue.getCouleur() == super.nouvelleCouleur)){

            if(super.nouvelleCouleur != null){
                return (coupJoue.getCouleur() == super.nouvelleCouleur);
            } else {
                return true;
            }
        } else {
            return false;
        }
	}

	@Override
	public boolean isJoueurAGagne(GroupeDeCarte paquetDuJoueurEnCours) {
		return paquetDuJoueurEnCours.estVide();
	}

    @Override
    public void appliquerEffetCarteSpeciale(Partie partie) {

        if(partie.getDernierCoupJoue() != null){ //c'est la partie qui garde le dernier coup joué, car on pourrait appeler isCoupJouable sans joue le coup après

            super.nouvelleCouleur = null;

            switch(partie.getDernierCoupJoue().getMotif()){
                case SEPT: // Saute le tour du joueur suivant
                    partie.getFenetre().sauterTour(partie.getProchainJoueurQuiVaJouer());
                    partie.changerTour();
                    break;

                case HUIT: // Les 8 permettent de changer de couleur à n’importe quel moment
                    partie.getFenetre().changerProchaineCouleur();

                    int choix;
                    if(partie.getJoueurQuiJoue() instanceof Ia){
                        choix = (int) (Math.random() * 4);
                    } else {
                        choix = Input.demanderEntier(0, 3);
                    }

                    switch (choix){
                        case 0 :
                            super.nouvelleCouleur = Couleur.TREFLE;
                            partie.getFenetre().ilFautJouer(Couleur.TREFLE);
                            break;

                        case 1:
                            super.nouvelleCouleur = Couleur.PIC;
                            partie.getFenetre().ilFautJouer(Couleur.PIC);
                            break;

                        case 2:
                            super.nouvelleCouleur = Couleur.COEUR;
                            partie.getFenetre().ilFautJouer(Couleur.COEUR);
                            break;

                        case 3:
                            super.nouvelleCouleur = Couleur.CARREAU;
                            partie.getFenetre().ilFautJouer(Couleur.CARREAU);
                            break;
                    }
                    break;

                case DIX: // Oblige le joueur qui la pose à rejouer
                    partie.changerSens();
                    partie.changerTour();
                    partie.changerSens();
                    partie.getFenetre().rejouer();
                    break;

                case VALET: // Changer le sens de jeu
                    partie.changerSens();
                    partie.getFenetre().changerSens();
                    break;

                case AS: // Fait piocher 4 cartes au suivant
                    partie.getFenetre().piocherObligatoire(4, partie.getProchainJoueurQuiVaJouer());
                    for(int i = 0; i < 4 ; ++i){
                        partie.getProchainJoueurQuiVaJouer().piocher(partie.retirerCartePioche());
                    }
                    break;

                case JOKER: // Permet de jouer sur n'importe quelle carte et permet de stopper une attaque
                    break;
            }
        }
    }

}
