package bayart.ayouaz.huit.americain.model;


import bayart.ayouaz.huit.americain.model.enums.Couleur;
import bayart.ayouaz.huit.americain.model.enums.Motif;

import java.util.Observable;

/**
 * 
 * Cette classe permet de modéliser une seule carte.
 * On spécifie la couleur et le motif (son nombre)
 */
public class Carte extends Observable {
	
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
        setChanged();
        notifyObservers("setDefausse");
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


    public String getImageFileName(){
        if(this.motif == Motif.JOKER){
            return "joker.png";
        } else {
            return this.motif.getNumero()+"_"+this.couleur.getNumero()+".png";
        }
    }
}
