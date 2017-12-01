package bayart.ayouaz.huit.americain.Model;

import bayart.ayouaz.huit.americain.Enum.Couleur;
import bayart.ayouaz.huit.americain.Enum.Motif;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class GroupeDeCarte {
	
    
//    public static final Carte[] paquet54Cartes = new Carte[] {
//        new Carte(Couleur.CARREAU, Motif.AS), new Carte(Couleur.COEUR, Motif.AS), new Carte(Couleur.PIC, Motif.AS), new Carte(Couleur.TREFLE, Motif.AS),
//        new Carte(Couleur.CARREAU, Motif.DEUX), new Carte(Couleur.COEUR, Motif.DEUX), new Carte(Couleur.PIC, Motif.DEUX), new Carte(Couleur.TREFLE, Motif.DEUX),
//        new Carte(Couleur.CARREAU, Motif.TROIS), new Carte(Couleur.COEUR, Motif.TROIS), new Carte(Couleur.PIC, Motif.TROIS), new Carte(Couleur.TREFLE, Motif.TROIS),
//        new Carte(Couleur.CARREAU, Motif.QUATRE), new Carte(Couleur.COEUR, Motif.QUATRE), new Carte(Couleur.PIC, Motif.QUATRE), new Carte(Couleur.TREFLE, Motif.QUATRE),
//        new Carte(Couleur.CARREAU, Motif.CINQ), new Carte(Couleur.COEUR, Motif.CINQ), new Carte(Couleur.PIC, Motif.CINQ), new Carte(Couleur.TREFLE, Motif.CINQ),
//        new Carte(Couleur.CARREAU, Motif.SIX), new Carte(Couleur.COEUR, Motif.SIX), new Carte(Couleur.PIC, Motif.SIX), new Carte(Couleur.TREFLE, Motif.SIX),
//        new Carte(Couleur.CARREAU, Motif.SEPT), new Carte(Couleur.COEUR, Motif.SEPT), new Carte(Couleur.PIC, Motif.SEPT), new Carte(Couleur.TREFLE, Motif.SEPT),
//        new Carte(Couleur.CARREAU, Motif.HUIT), new Carte(Couleur.COEUR, Motif.HUIT), new Carte(Couleur.PIC, Motif.HUIT), new Carte(Couleur.TREFLE, Motif.HUIT),
//        new Carte(Couleur.CARREAU, Motif.NEUF), new Carte(Couleur.COEUR, Motif.NEUF), new Carte(Couleur.PIC, Motif.NEUF), new Carte(Couleur.TREFLE, Motif.NEUF),
//        new Carte(Couleur.CARREAU, Motif.DIX), new Carte(Couleur.COEUR, Motif.DIX), new Carte(Couleur.PIC, Motif.DIX), new Carte(Couleur.TREFLE, Motif.DIX),
//        new Carte(Couleur.CARREAU, Motif.VALET), new Carte(Couleur.COEUR, Motif.VALET), new Carte(Couleur.PIC, Motif.VALET), new Carte(Couleur.TREFLE, Motif.VALET),
//        new Carte(Couleur.CARREAU, Motif.DAME), new Carte(Couleur.COEUR, Motif.DAME), new Carte(Couleur.PIC, Motif.DAME), new Carte(Couleur.TREFLE, Motif.DAME),
//        new Carte(Couleur.CARREAU, Motif.ROI), new Carte(Couleur.COEUR, Motif.ROI), new Carte(Couleur.PIC, Motif.ROI), new Carte(Couleur.TREFLE, Motif.ROI),
//        new Carte(Couleur.CARREAU, Motif.JOKER), new Carte(Couleur.COEUR, Motif.JOKER)
//    };
    public static final int PAQUET_32_CARTES = 32;
    public static final int PAQUET_52_CARTES = 52;

    //list car on veut pouvoir prendre une carte a une certaine posistion, et queue car on veut aussi piocher
    private LinkedList<Carte> cartes = new LinkedList<Carte>();
    

    public GroupeDeCarte() {
    }


    public GroupeDeCarte(int nbPaquet, int nbCarteParPaquet, boolean joker){

        if(nbPaquet>0 && (nbCarteParPaquet==PAQUET_32_CARTES || nbCarteParPaquet==PAQUET_52_CARTES)){

            for(int i=0; i<nbPaquet; ++i){

                for(Couleur couleur : Couleur.values()) {
                    for(Motif motif : Motif.values()) {
                        if(motif != Motif.JOKER &&
                            ((nbCarteParPaquet == PAQUET_32_CARTES && motif.getNumero() >= 7) ||
                            nbCarteParPaquet == PAQUET_52_CARTES)){

                            cartes.add(new Carte(couleur, motif));
                        }
                    }
                }

                if(joker){
                    cartes.add(new Carte(null, Motif.JOKER));
                    cartes.add(new Carte(null, Motif.JOKER));
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
         return this.cartes.poll();
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

	public boolean estVide(){
        return this.cartes.isEmpty();
    }
}





