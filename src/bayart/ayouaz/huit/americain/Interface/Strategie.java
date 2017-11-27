package bayart.ayouaz.huit.americain.Interface;

import java.util.ArrayList;

import bayart.ayouaz.huit.americain.Model.Carte;
import bayart.ayouaz.huit.americain.Model.GroupeDeCarte;
import bayart.ayouaz.huit.americain.Model.Regle;

public interface Strategie {

	public Carte jouer(Carte carteDefausse, GroupeDeCarte main, Regle regle);

}
