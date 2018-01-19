package bayart.ayouaz.huit.americain.model;

import bayart.ayouaz.huit.americain.controller.PartieGraphique;
import bayart.ayouaz.huit.americain.model.enums.Couleur;
import bayart.ayouaz.huit.americain.model.interfaces.Strategie;

import static bayart.ayouaz.huit.americain.model.enums.Motif.*;
import static bayart.ayouaz.huit.americain.model.enums.Motif.HUIT;
/**
 * C'est une sous classe de Strategie
 * Cette classe permet de propser la variante standard du jeu. Avec les specificités de chaque cartes.
 * @see Strategie Pour plus d'infos sur les methodes
 */
public class Variante2 extends Regle {

    public Variante2(int nbPaquet) {
        super.nouvelleCouleur = null;
        super.nomVariante = "version minimale";
        super.nbCarteParPaquet = GroupeDeCarte.PAQUET_52_CARTES;
        super.avecJoker = false;
        super.nbPaquet = nbPaquet;
    }

    @Override
    public boolean isCoupJouable(Carte coupJoue, Carte carteDefausse) {
        if (coupJoue == null
                || coupJoue.getMotif() == JOKER
                || coupJoue.getMotif() == HUIT) {

            return true;

        } else if (coupJoue.getMotif() == carteDefausse.getMotif() || coupJoue.getCouleur() == carteDefausse.getCouleur()
                || (super.nouvelleCouleur != null && coupJoue.getCouleur() == super.nouvelleCouleur)) {

            if (super.nouvelleCouleur != null) {
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
    public void appliquerEffetCarteSpeciale(PartieGraphique partie) {

        partie.getProchainJoueurQuiVaJouer().setPeutJouer(false);

        if (partie.getDernierCoupJoue() != null) { //c'est la partie qui garde le dernier coup joué, car on pourrait appeler isCoupJouable sans joue le coup après

            super.nouvelleCouleur = null;
            super.carteCreeeParJoker = null;

            switch (partie.getDernierCoupJoue().getMotif()) {

                case HUIT: // Les 8 permettent de changer de couleur à n’importe quel moment
                    //partie.getFenetre().changerProchaineCouleur(Couleur.values());

                    int choixCouleur;
                    if (partie.getJoueurQuiJoue() instanceof Ia) {
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
            }
        }
    }
}
