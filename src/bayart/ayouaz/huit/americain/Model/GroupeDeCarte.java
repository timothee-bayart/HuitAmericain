package bayart.ayouaz.huit.americain.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import bayart.ayouaz.huit.americain.Enum.Couleur;
import bayart.ayouaz.huit.americain.Enum.Motif;

public class GroupeDeCarte {
	
    
    public static final Carte[] paquet54Cartes = new Carte[] {
        new Carte(Couleur.CARREAU, Motif.AS), new Carte(Couleur.COEUR, Motif.AS), new Carte(Couleur.PIC, Motif.AS), new Carte(Couleur.TREFLE, Motif.AS),
        new Carte(Couleur.CARREAU, Motif.DEUX), new Carte(Couleur.COEUR, Motif.DEUX), new Carte(Couleur.PIC, Motif.DEUX), new Carte(Couleur.TREFLE, Motif.DEUX),
        new Carte(Couleur.CARREAU, Motif.TROIS), new Carte(Couleur.COEUR, Motif.TROIS), new Carte(Couleur.PIC, Motif.TROIS), new Carte(Couleur.TREFLE, Motif.TROIS),
        new Carte(Couleur.CARREAU, Motif.QUATRE), new Carte(Couleur.COEUR, Motif.QUATRE), new Carte(Couleur.PIC, Motif.QUATRE), new Carte(Couleur.TREFLE, Motif.QUATRE),
        new Carte(Couleur.CARREAU, Motif.CINQ), new Carte(Couleur.COEUR, Motif.CINQ), new Carte(Couleur.PIC, Motif.CINQ), new Carte(Couleur.TREFLE, Motif.CINQ),
        new Carte(Couleur.CARREAU, Motif.SIX), new Carte(Couleur.COEUR, Motif.SIX), new Carte(Couleur.PIC, Motif.SIX), new Carte(Couleur.TREFLE, Motif.SIX),
        new Carte(Couleur.CARREAU, Motif.SEPT), new Carte(Couleur.COEUR, Motif.SEPT), new Carte(Couleur.PIC, Motif.SEPT), new Carte(Couleur.TREFLE, Motif.SEPT),
        new Carte(Couleur.CARREAU, Motif.HUIT), new Carte(Couleur.COEUR, Motif.HUIT), new Carte(Couleur.PIC, Motif.HUIT), new Carte(Couleur.TREFLE, Motif.HUIT),
        new Carte(Couleur.CARREAU, Motif.NEUF), new Carte(Couleur.COEUR, Motif.NEUF), new Carte(Couleur.PIC, Motif.NEUF), new Carte(Couleur.TREFLE, Motif.NEUF),
        new Carte(Couleur.CARREAU, Motif.DIX), new Carte(Couleur.COEUR, Motif.DIX), new Carte(Couleur.PIC, Motif.DIX), new Carte(Couleur.TREFLE, Motif.DIX),
        new Carte(Couleur.CARREAU, Motif.VALET), new Carte(Couleur.COEUR, Motif.VALET), new Carte(Couleur.PIC, Motif.VALET), new Carte(Couleur.TREFLE, Motif.VALET),
        new Carte(Couleur.CARREAU, Motif.DAME), new Carte(Couleur.COEUR, Motif.DAME), new Carte(Couleur.PIC, Motif.DAME), new Carte(Couleur.TREFLE, Motif.DAME),
        new Carte(Couleur.CARREAU, Motif.ROI), new Carte(Couleur.COEUR, Motif.ROI), new Carte(Couleur.PIC, Motif.ROI), new Carte(Couleur.TREFLE, Motif.ROI),
        new Carte(Couleur.CARREAU, Motif.JOKER), new Carte(Couleur.COEUR, Motif.JOKER)
    };
	
    private ArrayList<Carte> cartes = new ArrayList<Carte> ();
    

    public GroupeDeCarte() {
    }
	
	
    public GroupeDeCarte(int nbPaquet) {
    	if(nbPaquet>0){
            for(int i=0; i<nbPaquet; i++) {
                cartes.addAll(Arrays.asList(paquet54Cartes));
            }
    	}
    }

    public GroupeDeCarte(int nbPaquet, int nbCarteParPaquet, boolean joker){
        if(nbPaquet>0 && (nbCarteParPaquet==32 || nbCarteParPaquet==52)){
            for(int i=0; i<nbPaquet;++i){
                if(nbCarteParPaquet == 32){
                    for(int j=7;j<14;++j){
                        for(int k=0;k<4;++k){
                            cartes.add(new Carte(j,k));
                        }
                    }
                }
                else{
                    for(int j=1;j<14;++j){
                        for(int k=0;k<4;++k){
                            cartes.add(new Carte(j,k));
                        }
                    }
                }
                if(joker){
                    cartes.add(new Carte(666,666));
                    cartes.add(new Carte(666,666));
                }
            }
        }
    }
    
    public void melanger(){
    	Collections.shuffle(this.cartes);
    }

    public void ajouterCarte(Carte carteAAjouter) {
    	this.cartes.add(carteAAjouter);
    }

    public Carte retirerCarte(Carte aRetirer) {
		this.cartes.remove(aRetirer);
    	return aRetirer;
    }
    
    public Carte retirerCarte() {
    	if(this.cartes.size()>0) {
        	return this.cartes.remove(0);
    	} else {
        	return null;
    	}
    }
    
    public Carte getCarte(int index) {
    	return this.cartes.get(index);
    }
	
        @Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		int index = 1;
		
		Iterator<Carte> cartesIt = this.cartes.iterator();
		
		while(cartesIt.hasNext()) {
			Carte carte = cartesIt.next();
			sb.append("["+index+"] : ");
			sb.append(carte);
			sb.append("\n");
			index++;
		}
		
		return sb.toString();
	}


	public int getNombreDeCartes() {
		return this.cartes.size();
	}
}





