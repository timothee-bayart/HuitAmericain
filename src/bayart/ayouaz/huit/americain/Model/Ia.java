package bayart.ayouaz.huit.americain.model;

import bayart.ayouaz.huit.americain.model.interfaces.Strategie;
/**
 * 
 * Cette classe permet aux IA d'avoir une strategie
 */
public class Ia extends Joueur{

	private Strategie strategie;
	private Regle regle;
	
	/**
         * Constructeur
         * @param nom le nom de l'IA
         * @param regle la regle que devra respecter l'IA
         */
	public Ia(String nom, Regle regle) {
		super(nom);
		this.regle = regle;
		this.strategie = new Strategie1();
		setChanged();
	}
	/**
         * Constructeur alternatif
         * @param nom le nom de l'IA
         * @param strategie la strategie que doit utiliser l'IA
         */
	public Ia(String nom, Strategie strategie) {
		super(nom);
	}
	
	/**
         * La methode qui sera appelé pour demander à l'IA de jouer
         * @param carteDefausse La carte de la défausse
         * @return la carte que veut jouer l'IA
         */
    public Carte jouer(Carte carteDefausse) {
        return strategie.jouer(carteDefausse, this.main, this.regle);
    }
    /**
     * C'est un setteur permetant de choisir une regle
     * @param regle la regle choisie
     */
    public void setRegle(Regle regle){
		this.regle = regle;
	}
}

