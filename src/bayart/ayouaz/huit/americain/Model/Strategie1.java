package bayart.ayouaz.huit.americain.model;

import bayart.ayouaz.huit.americain.model.interfaces.Strategie;

import java.io.Serializable;

/**
 * 
 * Cette classe permet aux IA d'avoir une stategie coherente à jouer.
 */
public class Strategie1 implements Strategie, Serializable {

	@Override
	public Carte jouer(Carte carteDefausse, GroupeDeCarte main, Regle regle) {
		
		int index = 0;
		Carte carte;
		
        do {
        	carte = main.getCarte(index);
        	index++;
            
        } while(index<main.getNombreDeCartes() && !regle.isCoupJouable(carte, carteDefausse));

        if(!regle.isCoupJouable(carte, carteDefausse)){
			return null;
		} else {
			return carte;
		}
	}

}
