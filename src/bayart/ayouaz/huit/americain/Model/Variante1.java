package bayart.ayouaz.huit.americain.model;

import bayart.ayouaz.huit.americain.controller.PartieGraphique;
import bayart.ayouaz.huit.americain.model.enums.Couleur;
import bayart.ayouaz.huit.americain.model.enums.Motif;
import bayart.ayouaz.huit.americain.model.interfaces.Strategie;

import static bayart.ayouaz.huit.americain.model.enums.Motif.*;
import static bayart.ayouaz.huit.americain.model.enums.Motif.HUIT;
/**
 * C'est une sous classe de Strategie
 * Cette classe permet de propser la variante standard du jeu. Avec les specificités de chaque cartes.
 * @see Strategie Pour plus d'infos sur les methodes
 */
public class Variante1 extends Regle {

    public Variante1(int nbPaquet){
        super.nouvelleCouleur = null;
        super.nomVariante = "version standard";
        super.nbCarteParPaquet = GroupeDeCarte.PAQUET_52_CARTES;
        super.avecJoker = true;
        super.nbPaquet = nbPaquet;
    }

	@Override
	public boolean isCoupJouable(Carte coupJoue, Carte carteDefausse) {
		if (
            coupJoue == null ||
            coupJoue.getMotif() == JOKER ||
            coupJoue.getMotif() == HUIT){

            return true;

        } else if(
                (coupJoue.getMotif() == carteDefausse.getMotif() || coupJoue.getCouleur() == carteDefausse.getCouleur()) ||
                (super.nouvelleCouleur != null && coupJoue.getCouleur() == super.nouvelleCouleur) ||
                (super.carteCreeeParJoker != null && (coupJoue.getMotif() == super.carteCreeeParJoker.getMotif() || coupJoue.getCouleur() == super.carteCreeeParJoker.getCouleur()))){

            if(super.nouvelleCouleur != null){
                return (coupJoue.getCouleur() == super.nouvelleCouleur);
            } else if(super.carteCreeeParJoker != null){
                return (coupJoue.getMotif() == super.carteCreeeParJoker.getMotif() || coupJoue.getCouleur() == super.carteCreeeParJoker.getCouleur());
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
    public void appliquerEffetCarteSpeciale(PartieGraphique partie) {

        if(partie.getDernierCoupJoue() != null){ //c'est la partie qui garde le dernier coup joué, car on pourrait appeler isCoupJouable sans joue le coup après

            super.nouvelleCouleur = null;
            super.carteCreeeParJoker = null;

            switch(partie.getDernierCoupJoue().getMotif()){
                case CINQ: // Donner une carte au joueur de son choix (5)
                    int indexJoueurPioche = 0;
                    Joueur[] joueursPossibles = new Joueur[partie.getNbJoueurs()-1];
                    for(Joueur joueur : partie.getJoueurs()){
                        if(joueur != partie.getJoueurQuiJoue()){
                            joueursPossibles[indexJoueurPioche] = joueur;
                            indexJoueurPioche++;
                        }
                    }

                    int choixJoueur;
                    if(partie.getJoueurQuiJoue() instanceof Ia){
                        choixJoueur = (int) (Math.random() *  (partie.getJoueurs().size()-1));
                    } else {
                        choixJoueur = partie.getFenetre().fairePiocherJoueur(joueursPossibles);
                    }

                    Joueur joueurQuiVaPiocher = joueursPossibles[choixJoueur];
                    partie.getFenetre().piocherObligatoire(1, joueurQuiVaPiocher);
                    joueurQuiVaPiocher.piocher(partie.retirerCartePioche());
                    break;

                case SEPT: // Saute le tour du joueur suivant
                    partie.getFenetre().sauterTour(partie.getProchainJoueurQuiVaJouer());
                    partie.changerTour();
                    break;

                case HUIT: // Les 8 permettent de changer de couleur à n’importe quel moment
                    int choixCouleur;
                    if(partie.getJoueurQuiJoue() instanceof Ia){
                        choixCouleur = (int) (Math.random() * 4);
                    } else {
                        choixCouleur = partie.getFenetre().changerProchaineCouleur(Couleur.values());
                    }

                    super.nouvelleCouleur = Couleur.values()[choixCouleur];
                    break;

                case DIX: // Oblige le joueur qui la pose à rejouer
                    partie.changerSens();
                    partie.changerTour();
                    partie.changerSens();
                    partie.getFenetre().rejouer(partie.getJoueurQuiJoue());
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

                case JOKER: // Permet de jouer sur n'importe quelle carte et on choisit la carte qu'elle prendra
                    super.carteCreeeParJoker = new Carte();

                    // on demande la couleur que le joueur veut

                    int choixCouleurJoker;
                    if(partie.getJoueurQuiJoue() instanceof Ia){
                        choixCouleurJoker = (int) (Math.random() * 4);
                    } else {
                        choixCouleurJoker = partie.getFenetre().changerProchaineCouleur(Couleur.values());
                    }
                    super.carteCreeeParJoker.setCouleur(Couleur.values()[choixCouleurJoker]);


                    // on demande le motif que le joueur veut
                    int nbCartesNormales = super.nbCarteParPaquet;
                    if(super.avecJoker) nbCartesNormales = nbCartesNormales - 2;
                    int nbMotifs = (super.nbCarteParPaquet/4);

                    Motif[] motifsPossibles = new Motif[nbMotifs];

                    int indexMotifJoker = 0;
                    for(Motif motif : Motif.values()) {
                        if(((super.nbCarteParPaquet == GroupeDeCarte.PAQUET_32_CARTES && motif.getNumero() >= 7) ||
                                super.nbCarteParPaquet == GroupeDeCarte.PAQUET_52_CARTES) &&
                                motif != Motif.JOKER){
                            motifsPossibles[indexMotifJoker] = motif;
                            indexMotifJoker++;
                        }
                    }


                    int choixMotifJoker;
                    if(partie.getJoueurQuiJoue() instanceof Ia){
                        choixMotifJoker = (int) (Math.random() * (nbMotifs+1));
                    } else {
//                        choixMotifJoker = Input.demanderEntier(0, nbMotifs);
                        choixMotifJoker = partie.getFenetre().changerProchainMotif(motifsPossibles);
                    }

                    super.carteCreeeParJoker.setMotif(motifsPossibles[choixMotifJoker]);

                    partie.getFenetre().jokerPrendApparence(super.carteCreeeParJoker);
                    break;
            }
        }
    }
}
