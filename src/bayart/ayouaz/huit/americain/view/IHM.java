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
 * 
 * Interface dans lequel toutes les fonctions d'affichage sont indiqués
 */
public interface IHM {
    /**
     * Cette fonction affiche les coups jouable par le joueur et le plateau.
     * Ainsi que quelques indications de jeu.
     * @param joueurs Liste des joueurs présents dans la partie.
     * @param defausse Carte sur la pile de la défausse
     */
    public void afficherPlateau(LinkedHashSet<Joueur> joueurs, Carte defausse);
    /**
     * Cette fonction permet d'afficher les variantes jouables par le joueur
     * afin de choisir.
     * @param variantes Liste des variantes
     * @param varianteSelected La variante actuellement selectionnée
     */
    public void afficherVariantesDepart(Regle[] variantes, Regle varianteSelected);
    /**
     * Cette methode principalement utilisée dans la fenetre graphique permet 
     * d'afficher un message sur le plateau en cours de jeu. Elle est appelée
     * par la methode afficherPlateau
     * @param message le message à afficher
     */
    public void afficherMessage(String message);
    /**
     * Affiche au début du jeu un message demandant au joueur de choisir un pseudo
     */
    public void afficherPseudoDepart();
    /**
     * Cette methode affiche le menu dans lequel le joueur va pouvoir choisir de
     * lancer la partie, d'ajouter des adversaires, de changer les regles, ou de
     * charger une partie.
     */
    public void afficherMenuDepart();
    /**
     * Message affiché à la fin de la partie.
     * @param joueurs liste des joueurs.
     */
    public void afficherPartieTerminee(Joueur[] joueurs);
/**
 * Affiche un message qui annonce qu'un joueur doit piocher
 * @param nbCartes nombre de cartes à piocher
 * @param QuiPioche joueur qui pioche
 */
    public void piocherObligatoire(int nbCartes, Joueur QuiPioche);//fait
    /**
     * Affiche un texte qui annonce qu'un joueur doit sauter son tour
     * @param quiVaEtreSauter joueur qui saute son tour
     */
    public void sauterTour(Joueur quiVaEtreSauter);
    /**
     * Affiche des instructions pour demander au joueur de choisir la prochaine
     * couleur à joueur
     * @param couleurs liste des couleurs existantes
     * @return l'id de la couleur choisie
     */
    public int changerProchaineCouleur(Couleur[] couleurs);
    /**
     * Affiche la couleur qu'il faut jouer
     * @param couleur la couleur à jouer
     */
    public void ilFautJouer(Couleur couleur);
    /**
     * Affiche la carte qu'il faut jouer
     * @param carte la carte à jouer
     */
    public void ilFautJouer(Carte carte);
    /**
     * Affiche qu'un joueur peut rejouer
     * @param quiRejoue le joueur qui rejoue
     */
    public void rejouer(Joueur quiRejoue);
    /**
     * Demande au joueur de choisir un autre joueur pour piocher une carte
     * @param quiVaPiocher Liste des joueur qui peuvent piocher
     * @return l'id du joueur qui pioche
     */
    public int fairePiocherJoueur(Joueur [] quiVaPiocher);
    /**
     * Indique que le jeu va changer de sens
     */
    public void changerSens();
    /**
     * Affiche la liste des motifs que peut choisir le joueur
     * @param motifs listes de motifs a choisir
     * @return l'id du motif choisis
     */
    public int changerProchainMotif(Motif[] motifs);
    /**
     * Choisis l'apparence que le joker va prendre
     * @param carte la carte correspondant à l'apparence qu'a pris le jocker
     */
    public void jokerPrendApparence(Carte carte);
    /**
     * getter pour le dernier message afficher
     * @return le string du dernier message
     */
    public String getDernierMessage();
}
