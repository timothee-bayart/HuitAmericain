package bayart.ayouaz.huit.americain.Model;

import java.util.HashMap;
import java.util.Map;

import bayart.ayouaz.huit.americain.Enum.Couleur;
import bayart.ayouaz.huit.americain.Enum.Motif;

public class Carte {
	
    private Couleur couleur;
    private Motif motif;
    
    public Carte(Couleur couleur, Motif motif){
    	this.couleur = couleur;
    	this.motif = motif;
    }

    
    /**
     * GETTERS
     */
	public Couleur getCouleur() {
		return couleur;
	}

	public Motif getMotif() {
		return motif;
	}

	/**
	 * SETTERS
	 */
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	public void setMotif(Motif motif) {
		this.motif = motif;
	}
	
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.motif);
		sb.append(" de ");
		sb.append(this.couleur);
		return sb.toString();
    } 

}
