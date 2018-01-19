package bayart.ayouaz.huit.americain.model.interfaces;

import bayart.ayouaz.huit.americain.model.Carte;
import bayart.ayouaz.huit.americain.model.GroupeDeCarte;
import bayart.ayouaz.huit.americain.model.Regle;
/**
 *
 * Cette classe permet de propser la variante standard du jeu. Avec les specificités de chaque cartes.
 */
public interface Strategie {
        /**
         * Cette methode est le coeur de la classe. C'est celle qui définit l'action 
         * que l'IA va faire pour jouer.
         * @param carteDefausse La carte sur la défausse
         * @param main Les cartes dans la main de l'IA
         * @param regle La regle du jeu en cours
         * @return la carte que veut jouer l'IA
         */
	public Carte jouer(Carte carteDefausse, GroupeDeCarte main, Regle regle);

}
