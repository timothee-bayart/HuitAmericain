/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.view;

import bayart.ayouaz.huit.americain.Model.Carte;
import bayart.ayouaz.huit.americain.Model.Joueur;
import bayart.ayouaz.huit.americain.Model.Regle;
import bayart.ayouaz.huit.americain.model.enums.Couleur;
import bayart.ayouaz.huit.americain.model.enums.Motif;

/**
 *
 * @author ed
 */
public interface IHM {
    public void afficherPlateau();
    public void variante(Regle[] var);
    public void afficherPseudoDepart();
    public void afficherMenuDepart();
    public void afficherAjoutJoueur();
    public void piocherObligatoire(int nbCartes, Joueur QuiPioche);
    public void sauterTour(Joueur quiVaEtreSauter);
    public void changerProchaineCouleur(Couleur[] couleurs);
    public void ilFautJouer(Couleur couleur);
    public void rejouer(Joueur quiRejoue);
    public void fairePiocherJoueur(Joueur [] quiVaPiocher);
    public void changerSens();
    public void changerProchainMotif(Motif[] motifs);
    public void jokerPrendApparence(Carte carte);
}
