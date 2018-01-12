/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayart.ayouaz.huit.americain.view;

import bayart.ayouaz.huit.americain.model.Carte;
import bayart.ayouaz.huit.americain.model.Joueur;
import bayart.ayouaz.huit.americain.model.Regle;
import bayart.ayouaz.huit.americain.model.enums.Couleur;
import bayart.ayouaz.huit.americain.model.enums.Motif;

import java.util.LinkedHashSet;

/**
 *
 * @author ed
 */
public interface IHM {
    public void afficherPlateau(LinkedHashSet<Joueur> joueurs, Carte defausse);
    public void afficherVariantesDepart(Regle[] variantes, Regle varianteSelected);
    public void afficherMessage(String message);
    public void afficherPseudoDepart();
    public void afficherMenuDepart();
    public void piocherObligatoire(int nbCartes, Joueur QuiPioche);
    public void sauterTour(Joueur quiVaEtreSauter);
    public void changerProchaineCouleur(Couleur[] couleurs);
    public void ilFautJouer(Couleur couleur);
    public void ilFautJouer(Carte carte);
    public void rejouer(Joueur quiRejoue);
    public void fairePiocherJoueur(Joueur [] quiVaPiocher);
    public void changerSens();
    public void changerProchainMotif(Motif[] motifs);
    public void jokerPrendApparence(Carte carte);
}
