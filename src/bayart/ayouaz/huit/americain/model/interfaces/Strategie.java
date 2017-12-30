package bayart.ayouaz.huit.americain.model.interfaces;

import bayart.ayouaz.huit.americain.model.Carte;
import bayart.ayouaz.huit.americain.model.GroupeDeCarte;
import bayart.ayouaz.huit.americain.model.Regle;

public interface Strategie {

	public Carte jouer(Carte carteDefausse, GroupeDeCarte main, Regle regle);

}
