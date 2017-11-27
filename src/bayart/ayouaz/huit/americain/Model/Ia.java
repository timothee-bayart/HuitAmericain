package bayart.ayouaz.huit.americain.Model;

import bayart.ayouaz.huit.americain.Interface.Strategie;

public class Ia extends Joueur{

	private Strategie strategie;
	private Regle regle;
	
	
	public Ia(String nom, Regle regle) {
		super(nom);
		this.regle = regle;
		this.strategie = new Strategie1();
	}
	
	public Ia(String nom, Strategie strategie) {
		super(nom);
	}
	
	
    public Carte jouer(Carte carteDefausse) {
        return strategie.jouer(carteDefausse, this.main, this.regle);
    }
}

