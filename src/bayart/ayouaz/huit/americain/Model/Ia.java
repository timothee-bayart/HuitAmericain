package bayart.ayouaz.huit.americain.Model;

import bayart.ayouaz.huit.americain.Interface.Strategie;

public class Ia extends Joueur{

	private Strategie strategie;
	
	
	public Ia(String nom) {
		super(nom);
		this.strategie = new Strategie1();
	}
	
	public Ia(String nom, Strategie strategie) {
		super(nom);
	}
	
	
    public Carte jouer(Carte carteDefausse) {
        return strategie.jouer(carteDefausse, this.main);
    }
}

