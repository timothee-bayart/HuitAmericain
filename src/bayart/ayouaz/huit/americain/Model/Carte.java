package bayart.ayouaz.huit.americain.model;


import bayart.ayouaz.huit.americain.model.enums.Couleur;
import bayart.ayouaz.huit.americain.model.enums.Motif;

import java.io.Serializable;
import java.util.Observable;

/**
 * 
 * Cette classe permet de modéliser une seule carte.
 * On spécifie la couleur et le motif (son nombre)
 */
public class Carte extends Observable implements Serializable {
	
    private Couleur couleur;
    private Motif motif;

    /**
     * Constructeur
     * @param couleur la couleur de la carte
     * @param motif le motif de la carte
     */
    public Carte(Couleur couleur, Motif motif){
    	this.couleur = couleur;
    	this.motif = motif;
    }

    /**
     * Constructeur par défault
     */
    public Carte() {
    }

    /**
     * C'est un setteur permetant de définir la couleur de la carte
     * @param couleur la couleur de la carte
     */
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     * C'est un setteur permetant définir le motif de la carte
     * @param motif le motif de la carte
     */
    public void setMotif(Motif motif) {
        this.motif = motif;
    }

    /**
     * C'est un setteur utilisé pour "dupliquer" une carte, utile dans le cadre de la défausse du jeu
     * @param c la carte dont on souhaite copier ses valeurs
     */
    public void setDefausse(Carte c){
        this.couleur = c.couleur;
        this.motif = c.motif;
        setChanged();
        notifyObservers("setDefausse");
    }

    /**
     * C'est un getter utilisé pour récupérer la couleur de la carte
     */
	public Couleur getCouleur() {
		return couleur;
	}

	/**
     * C'est un getter utilisé pour récupérer le motif de la carte
     */
	public Motif getMotif() {
		return motif;
	}


    @Override
    /**
     * Redéfinition de la méthode toString, qui retourne le motif et la couleur de la carte
     */
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

    /**
     * C'est un getter utilisé pour récupérer dynamiquement le nom du fichier image de la carte
     */
    public String getImageFileName(){
        if(this.motif == Motif.JOKER){
            return "joker.png";
        } else {
            return this.motif.getNumero()+"_"+this.couleur.getNumero()+".png";
        }
    }
}
