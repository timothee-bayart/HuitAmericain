package bayart.ayouaz.huit.americain.Model;


import bayart.ayouaz.huit.americain.model.enums.Couleur;
import bayart.ayouaz.huit.americain.model.enums.Motif;
import bayart.ayouaz.huit.americain.model.interfaces.Observable;
/**
 * 
 * Cette classe permet de modéliser une seule carte.
 * On spécifie la couleur et le motif (son nombre)
 */
public class Carte extends Observable{
	
    private Couleur couleur;
    private Motif motif;

    public Carte(){}
    
    public Carte(Couleur couleur, Motif motif){
    	this.couleur = couleur;
    	this.motif = motif;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public void setMotif(Motif motif) {
        this.motif = motif;
    }
    
    public void setDefausse(Carte c){
        this.couleur = c.couleur;
        this.motif = c.motif;
        notifyObserver("setDefausse");
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

	
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if(this.motif != null){
            sb.append(this.motif);
        }
        if(this.couleur != null){
            if(this.motif != null){
                sb.append(" de ");
            }
            sb.append(this.couleur);
        }
        return sb.toString();
    } 

}
