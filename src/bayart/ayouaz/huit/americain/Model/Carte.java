package bayart.ayouaz.huit.americain.Model;

import java.util.HashMap;
import java.util.Map;

import bayart.ayouaz.huit.americain.Enum.Couleur;
import bayart.ayouaz.huit.americain.Enum.Motif;
/**
 * 
 * Cette classe permet de modéliser une seule carte.
 * On spécifie la couleur et le motif (son nombre)
 */
public class Carte {
	
    private Couleur couleur;
    private Motif motif;

    public Carte(){}
    
    public Carte(Couleur couleur, Motif motif){
    	this.couleur = couleur;
    	this.motif = motif;
    }

//    public Carte(int motif, int couleur){ //TODO : On peut garder les enum !! il faut ajouter un nombre dans le constructeur des enums DONC a changer
//        if(motif == 1){
//            this.motif=Motif.AS;
//        }
//        if(motif == 2){
//            this.motif=Motif.DEUX;
//        }
//        if(motif == 3){
//            this.motif=Motif.TROIS;
//        }
//        if(motif == 4){
//            this.motif=Motif.QUATRE;
//        }
//        if(motif == 5){
//            this.motif=Motif.CINQ;
//        }
//        if(motif == 6){
//            this.motif=Motif.SIX;
//        }
//        if(motif == 7){
//            this.motif=Motif.SEPT;
//        }
//        if(motif == 8){
//            this.motif=Motif.HUIT;
//        }
//        if(motif == 9){
//            this.motif=Motif.NEUF;
//        }
//        if(motif == 10){
//            this.motif=Motif.DIX;
//        }
//        if(motif == 11){
//            this.motif=Motif.VALET;
//        }
//        if(motif == 12){
//            this.motif=Motif.DAME;
//        }
//        if(motif == 13){
//            this.motif=Motif.ROI;
//        }
//
//
//        if(couleur == 0){
//            this.couleur = Couleur.CARREAU;
//        }
//        if(couleur == 1){
//            this.couleur = Couleur.COEUR;
//        }
//        if(couleur == 2){
//            this.couleur = Couleur.PIC;
//        }
//        if(couleur == 3){
//            this.couleur = Couleur.TREFLE;
//        }
//        if(couleur==666 || motif==666){
//            this.motif = Motif.JOKER;
//            this.couleur = null;
//        }
//    }


    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public void setMotif(Motif motif) {
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
