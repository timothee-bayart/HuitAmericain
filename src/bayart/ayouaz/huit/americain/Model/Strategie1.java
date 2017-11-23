package bayart.ayouaz.huit.americain.Model;

import java.util.ArrayList;

import bayart.ayouaz.huit.americain.Interface.Strategie;

public class Strategie1 implements Strategie {

	@Override
	public Carte jouer(Carte carteDefausse, GroupeDeCarte main) {
		
		int index = 0;
		Carte carte;
		
        do {
        	carte = main.getCarte(index);
        	index++;
            
        } while(index<main.getNombreDeCartes() && !Partie.regle.isCoupJouable(carte, carteDefausse));
        
        return carte;
	}

}
